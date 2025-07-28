package com.pecado.del_sol_mars_rover.controllers;

import com.pecado.del_sol_mars_rover.dto.ObstacleDto;
import com.pecado.del_sol_mars_rover.services.ObstacleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ObstacleControllerTest {

    @Mock
    private ObstacleService service;
    @InjectMocks
    private ObstacleController controller;

    @Test
    void whenCreateObstacle_callService() {
        // Given
        ObstacleDto obstacleDto = new ObstacleDto(5, 5, 1L);
        // When
        controller.createObstacle(obstacleDto);
        // Then
        verify(service, times(1)).createObstacle(obstacleDto);

    }

    @Test
    void whenGetObstaclesByMap_callService() {
        // Given
        Long mapId = 1L;
        // When
        controller.getObstaclesByMap(mapId);
        // Then
        verify(service, times(1)).getObstaclesByMap(mapId);
    }

    @Test
    void whenDeleteObstacle_callService() {
        // Given
        Long id = 1L;
        // When
        controller.deleteObstacle(id);
        // Then
        verify(service, times(1)).deleteObstacle(id);
    }
}