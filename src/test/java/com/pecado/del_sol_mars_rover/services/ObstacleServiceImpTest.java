package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.ObstacleDto;
import com.pecado.del_sol_mars_rover.dto.ObstacleResponseDto;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.repository.MapRepository;
import com.pecado.del_sol_mars_rover.repository.ObstacleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ObstacleServiceImpTest {

    @Mock
    private MapRepository mapRepository;
    @Mock
    private ObstacleRepository obstacleRepository;
    @InjectMocks
    private ObstacleServiceImp obstacleService;

    @Test
    void givenValidData_whenCreateObstacle_thenSavesObstacle() {
        // Given
        ObstacleDto obstacleDto = new ObstacleDto(5, 5, 1L);
        Map mockMap = new Map();
        mockMap.setId(1L);
        when(mapRepository.findById(obstacleDto.getMapId())).thenReturn(Optional.of(mockMap));

        // When
        obstacleService.createObstacle(obstacleDto);

        // Then
        verify(obstacleRepository).save(argThat(o ->
                Objects.equals(o.getX(), obstacleDto.getX()) &&
                        Objects.equals(o.getY(), obstacleDto.getY()) &&
                o.getMap().equals(mockMap)
        ));
    }

    @Test
    void givenNonExistingMap_whenCreatedObstacle_thenThrowsException() {
        // Given
        ObstacleDto obstacleDto = new ObstacleDto(5, 5, 99L);
        when(mapRepository.findById(obstacleDto.getMapId())).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> obstacleService.createObstacle(obstacleDto));

        // Then
        assertEquals("Map not found with ID: 99", exception.getMessage());
    }

    @Test
    void givenMapWithObstacles_whenGetObstacleByMap_thenReturnOstacles() {
        // Given
        Map map = new Map();
        map.setId(1L);
        ObstacleResponseDto obstacle1 = new ObstacleResponseDto(1L,5, 5, 1L);
        ObstacleResponseDto obstacle2 = new ObstacleResponseDto(2L,10, 10, 1L);
        when(obstacleRepository.findByMapId(map.getId())).thenReturn(List.of(obstacle1, obstacle2));
        // When
        List<ObstacleResponseDto> result = obstacleService.getObstaclesByMap(map.getId());

        // Then
        assertEquals(2, result.size());
        assertEquals(obstacle1, result.get(0));
        assertEquals(obstacle2, result.get(1));
    }

    @Test
    void givenObstacleId_whenDeleteObstacle_thenDeletesObstacle() {
        // Given
        Long obstacleId = 1L;
        when(obstacleRepository.existsById(obstacleId)).thenReturn(true);
        // When
        obstacleService.deleteObstacle(obstacleId);

        // Then
        verify(obstacleRepository).deleteById(obstacleId);
    }

    @Test
    void givenNonExistingObstacleId_whenDeleteObstacle_thenThrowsException() {
        // Given
        Long obstacleId = 99L;
        when(obstacleRepository.existsById(obstacleId)).thenReturn(false);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> obstacleService.deleteObstacle(obstacleId));

        // Then
        assertEquals("Obstacle not found with ID: " + obstacleId, exception.getMessage());
    }

    @Test
    void givenObstacleAtPosition_whenCheckIsObstacleAt_thenReturnTrue() {
        // Given
        Long mapId = 1L;
        Integer x = 5;
        Integer y = 5;
        ObstacleResponseDto obstacle = new ObstacleResponseDto(1L, x, y, mapId);

        when(obstacleRepository.findByMapId(mapId)).thenReturn(List.of(obstacle));

        // When
        boolean result = obstacleService.isObstacleAtPosition(mapId, x, y);

        // Then
        assertTrue(result);
    }

    @Test
    void givenNoObstacleAtPosition_whenCheckIsObstacleAt_thenReturnFalse() {
        // Given
        Long mapId = 1L;
        Integer x = 5;
        Integer y = 5;
        Integer positionX = 10;
        Integer positionY = 10;
        ObstacleResponseDto obstacle = new ObstacleResponseDto(1L, x, y, mapId);

        when(obstacleRepository.findByMapId(mapId)).thenReturn(List.of(obstacle));

        // When
        boolean result = obstacleService.isObstacleAtPosition(mapId, positionX, positionY);

        // Then
        assertFalse(result);
    }
}
