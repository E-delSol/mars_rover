package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.ObstacleDto;
import com.pecado.del_sol_mars_rover.dto.ObstacleResponseDto;

import java.util.List;

public interface ObstacleService {
    void createObstacle(ObstacleDto obstacleDto);
    List<ObstacleResponseDto> getObstaclesByMap(Long mapId);
    void deleteObstacle(Long id);
    boolean isObstacleAtPosition(Long mapId, Integer x, Integer y);
}
