package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.entities.Obstacle;
import com.pecado.del_sol_mars_rover.repository.ObstacleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObstacleServiceTest {

    @Mock
    private ObstacleRepository obstacleRepository;

    @InjectMocks
    private ObstacleService obstacleService;

    @Test
    void testGetAllObstaclesReturnsList() {
        List<Obstacle> mockedObstacles = List.of(
            new Obstacle(1L, 10, 10),
            new Obstacle(2L, 20, 20)
        );

        when(obstacleRepository.findAll()).thenReturn(mockedObstacles);

        List<Obstacle> result = obstacleService.getAllObstacles();

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getX());
        assertEquals(10, result.get(0).getY());
    }

}
