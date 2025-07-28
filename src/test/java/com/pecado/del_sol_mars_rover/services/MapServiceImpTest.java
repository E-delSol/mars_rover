package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.MapDto;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.repository.MapRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MapServiceImpTest {

    @Mock
    private MapRepository mapRepository;
    @InjectMocks
    private MapServiceImp mapService;

    @Test
    void givenValidData_whenCreateMap_thenSaveMap() {
        // Given
        MapDto mapDto = new MapDto("Test Map", 10, 10);

        // When
        mapService.createMap(mapDto);

        // Then
        verify(mapRepository).save(argThat(m ->
                m.getName().equals(mapDto.getName()) &&
                        Objects.equals(m.getWidth(), mapDto.getWidth()) &&
                        Objects.equals(m.getHeight(), mapDto.getHeight())
        ));

    }

    @Test
    void givenInvalidData_whenCreateMap_thenThrowException() {
        // Given
        MapDto mapDto = new MapDto("Test Map", -1, 10);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mapService.createMap(mapDto);
        });

        // Then
        assertEquals("Map dimensions must be greater than zero.", exception.getMessage());
    }

    @Test
    void givenInvalidWidth_whenCreateMap_thenThrowException() {
        // Given
        MapDto mapDto = new MapDto("Test Map", 0, 10);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapService.createMap(mapDto));

        // Then
        assertEquals("Map dimensions must be greater than zero.", exception.getMessage());
    }

    @Test
    void givenInvalidHeight_whenCreateMap_thenThrowException() {
        // Given
        MapDto mapDto = new MapDto("Test Map", 10, 0);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapService.createMap(mapDto));

        // Then
        assertEquals("Map dimensions must be greater than zero.", exception.getMessage());
    }

    @Test
    void givenValidId_whenGetMap_thenReturnMap() {
        // Given
        Long mapId = 1L;
        Map mockMap = new Map();
        mockMap.setId(mapId);
        when(mapRepository.findById(mapId)).thenReturn(java.util.Optional.of(mockMap));

        // When
        Map result = mapService.getMap(mapId);

        // Then
        assertEquals(mockMap, result);
    }

    @Test
    void givenNonExistingId_whenGetMap_thenThrowException() {
        // Given
        Long mapId = 99L;
        when(mapRepository.findById(mapId)).thenReturn(java.util.Optional.empty());

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapService.getMap(mapId));

        // Then
        assertEquals("Map not found with ID: " + mapId, exception.getMessage());
    }

    @Test
    void whenGetAllMaps_thenReturnAllMaps() {
        // Given
        Map map1 = new Map();
        map1.setId(1L);
        Map map2 = new Map();
        map2.setId(2L);
        when(mapRepository.findAll()).thenReturn(List.of(map1, map2));

        // When
        List<Map> result = mapService.getAllMaps();

        // Then
        assertEquals(2, result.size());
        assertEquals(map1, result.get(0));
        assertEquals(map2, result.get(1));
    }

    @Test
    void givenValidId_whenDeleteMap_thenDeletesMap() {
        // Given
        Long mapId = 1L;
        when(mapRepository.existsById(mapId)).thenReturn(true);

        // When
        mapService.deleteMap(mapId);

        // Then
        verify(mapRepository).deleteById(mapId);
    }

    @Test
    void givenNonExistingId_whenDeleteMap_thenThrowsException() {
        // Given
        Long mapId = 99L;
        when(mapRepository.existsById(mapId)).thenReturn(false);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapService.deleteMap(mapId));

        // Then
        assertEquals("Map not found with ID: " + mapId, exception.getMessage());
    }

}
