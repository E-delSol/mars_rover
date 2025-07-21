package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.entities.Obstacle;
import com.pecado.del_sol_mars_rover.repository.ObstacleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObstacleService {

    private final ObstacleRepository obstacleRepository;

    public ObstacleService(ObstacleRepository obstacleRepository) {
        this.obstacleRepository = obstacleRepository;
    }

    public List<Obstacle> getAllObstacles() {
        return obstacleRepository.findAll();
    }

}
