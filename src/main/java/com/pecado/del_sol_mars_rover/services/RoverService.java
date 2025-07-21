package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.entities.Rover;
import com.pecado.del_sol_mars_rover.repository.RoverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoverService {
    private final RoverRepository roverRepository;
    private final int GRID_SIZE = 100; // Size of the planet

    private List<int[]> obstacles = List.of(
            new int[]{10, 10},
            new int[]{20, 20},
            new int[]{30, 30},
            new int[]{40, 40}
    );

    public RoverService(RoverRepository roverRepository) {
        this.roverRepository = roverRepository;
    }

    public Rover execcuteCommands(Long roverId, String commands) {
        Rover rover = roverRepository.findById(roverId)
                .orElseThrow(() -> new IllegalArgumentException("Rover not found with ID: " + roverId));

        for (char command : commands.toCharArray()) {
            if (command == 'f' || command == 'b') {
                move(rover, command);
            } else if (command == 'l') {
                rover.setDirection(rover.getDirection().turnLeft());
            } else if (command == 'r') {
                rover.setDirection(rover.getDirection().turnRight());
            } else {
                throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
        roverRepository.save(rover);
        return rover;
    }

    private void move(Rover rover, char command) {
        int dx = 0, dy = 0;
        switch (rover.getDirection()){
            case N -> dy = 1;
            case S -> dy = -1;
            case E -> dx = 1;
            case W -> dx = -1;
        }
        if (command == 'b') {
            dx *= -1;
            dy *= -1;
        }

        int newX = (rover.getX() + dx + GRID_SIZE) % GRID_SIZE;
        int newY = (rover.getY() + dy + GRID_SIZE) % GRID_SIZE;

        if (isObstacle(newX, newY)) {
            throw new IllegalArgumentException("Obstacle detected at position: (" + newX + ", " + newY + ")");
        }

        rover.setX(newX);
        rover.setY(newY);
    }

    private boolean isObstacle(int x, int y) {
        return obstacles.stream()
                .anyMatch(obstacle -> obstacle[0] == x && obstacle[1] == y);
    }

}
