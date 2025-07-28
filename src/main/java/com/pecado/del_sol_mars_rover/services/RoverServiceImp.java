package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.RoverDto;
import com.pecado.del_sol_mars_rover.dto.RoverResponseDto;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.entities.Rover;
import com.pecado.del_sol_mars_rover.repository.MapRepository;
import com.pecado.del_sol_mars_rover.repository.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RoverServiceImp implements RoverService {

    private RoverRepository repository;
    private MapRepository mapRepository;
    private ObstacleService obstacleService;

    @Autowired
    public RoverServiceImp(RoverRepository repository, MapRepository mapRepository, ObstacleService obstacleService) {
        this.repository = repository;
        this.mapRepository = mapRepository;
        this.obstacleService = obstacleService;
    }

    @Override
    public void createRover(RoverDto roverDto) {
        Map map = mapRepository.findById(roverDto.getMapId())
                .orElseThrow(() -> new IllegalArgumentException("Map not found with ID: " + roverDto.getMapId()));
        Rover rover = roverDto.toEntity(map);

        repository.save(rover);
    }

    @Override
    public RoverResponseDto getRover(Long id) {
        return repository.findByIdResponseDto(id)
                .orElseThrow(() -> new IllegalArgumentException("Rover not found with ID: " + id));
    }

    @Override
    public void sendCommands(Long id, String commands) {
        Rover rover = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rover not found with ID: " + id));
        Map map = rover.getMap();

        if (rover.getDirection() == null) {
            throw new IllegalArgumentException("Rover direction is not set.");
        }

        for (char command : commands.toCharArray()) {
            if (command == 'f' || command == 'b') {
                int[] nextPosition = getNextPosition(rover, command);
                int nextX = nextPosition[0];
                int nextY = nextPosition[1];
                // Check for obstacles at the next position
                if (obstacleService.isObstacleAtPosition(map.getId(), nextX, nextY)) {
                    break; // Stop processing commands if an obstacle is encountered
                }

                // if no obstacle, update rover position
                if (command == 'f') {
                    rover.moveForward();
                } else {
                    rover.moveBackward();
                }

                // Apply wrapping logic after moving
                rover.setX(map.wrapX(rover.getX()));
                rover.setY(map.wrapY(rover.getY()));

            } else if (command == 'l') {
                rover.turnLeft();
            } else if (command == 'r') {
                rover.turnRight();
            } else {
                throw new IllegalArgumentException("Invalid command: " + command);
            }
        }

        repository.save(rover);
    }

    private int[] getNextPosition(Rover rover, char command) {
        int nextX = rover.getX();
        int nextY = rover.getY();
        Map map = rover.getMap();

        switch (rover.getDirection()) {
            case N:
                nextY += (command == 'f') ? 1 : -1;
                break;
            case S:
                nextY -= (command == 'f') ? 1 : -1;
                break;
            case E:
                nextX += (command == 'f') ? 1 : -1;
                break;
            case W:
                nextX -= (command == 'f') ? 1 : -1;
                break;
        }
        return new int[]{map.wrapX(nextX), map.wrapY(nextY)};
    }

    @Override
    public List<RoverResponseDto> getAllRovers() {
        return repository.findAllResponseDto();
    }

    @Override
    public void deleteRover(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Rover not found with ID: " + id);
        }
        repository.deleteById(id);
    }

}
