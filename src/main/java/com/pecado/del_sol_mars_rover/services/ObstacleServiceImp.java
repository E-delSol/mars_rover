package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.ObstacleDto;
import com.pecado.del_sol_mars_rover.dto.ObstacleResponseDto;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.entities.Obstacle;
import com.pecado.del_sol_mars_rover.repository.MapRepository;
import com.pecado.del_sol_mars_rover.repository.ObstacleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObstacleServiceImp implements ObstacleService {

    private ObstacleRepository repository;
    private MapRepository mapRepository;

    @Autowired
    public ObstacleServiceImp(ObstacleRepository repository, MapRepository mapRepository) {
        this.repository = repository;
        this.mapRepository = mapRepository;
    }

    @Override
    public void createObstacle(ObstacleDto obstacleDto) {
        Map map = mapRepository.findById(obstacleDto.getMapId())
                .orElseThrow(() -> new IllegalArgumentException("Map not found with ID: " + obstacleDto.getMapId()));
        Obstacle obstacle = obstacleDto.toEntity(map);

        repository.save(obstacle);
    }

    @Override
    public List<ObstacleResponseDto> getObstaclesByMap(Long mapId) {
        return repository.findByMapId(mapId);
    }

    @Override
    public void deleteObstacle(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Obstacle not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public boolean isObstacleAtPosition(Long mapId, Integer x, Integer y) {
        return repository.findByMapId(mapId).stream()
                .anyMatch(ob -> ob.getX().equals(x) && ob.getY().equals(y));
    }

}
