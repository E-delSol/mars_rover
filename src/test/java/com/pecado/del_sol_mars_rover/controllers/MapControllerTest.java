package com.pecado.del_sol_mars_rover.controllers;

import com.pecado.del_sol_mars_rover.dto.MapDto;
import com.pecado.del_sol_mars_rover.services.MapService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class MapControllerTest {

    @Mock
    private MapService service;
    @InjectMocks
    private MapController controller;

    @Test
    void whenCreateMap_callService() {
        // Given
        MapDto mapDto = new MapDto("Test Map", 10, 10);
        // When
        controller.createMap(mapDto);
        // Then
        verify(service, times(1)).createMap(mapDto);
    }

    @Test
    void whenGetMap_callService() {
        // Given
        Long mapId = 1L;
        // When
        controller.getMap(mapId);
        // Then
        verify(service, times(1)).getMap(mapId);
    }

    @Test
    void whenGetAllMaps_callService() {
        // Given

        // When
        controller.getAllMaps();
        // Then
        verify(service, times(1)).getAllMaps();
    }

    @Test
    void whenDeleteMap_callService() {
        // Given
        Long mapId = 1L;
        // When
        controller.deleteMap(mapId);
        // Then
        verify(service, times(1)).deleteMap(mapId);
    }

}
