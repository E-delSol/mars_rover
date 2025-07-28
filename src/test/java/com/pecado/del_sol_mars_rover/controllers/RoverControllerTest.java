package com.pecado.del_sol_mars_rover.controllers;

import com.pecado.del_sol_mars_rover.dto.RoverDto;
import com.pecado.del_sol_mars_rover.entities.Direction;
import com.pecado.del_sol_mars_rover.services.RoverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoverControllerTest {

    @Mock
    private RoverService service;
    @InjectMocks
    private RoverController controller;

    @Test
    void whenCreateRover_callService() {
        // Given
        RoverDto roverDto = new RoverDto(5, 5, Direction.N, 1L);
        // When
        controller.createRover(roverDto);
        // Then
        verify(service, times(1)).createRover(roverDto);
    }

    @Test
    void whenSendCommands_callService() {
        // Given

        // When
        controller.sendCommands(1L, "ffrfflff");
        // Then
        verify(service, times(1)).sendCommands(1L, "ffrfflff");
    }

    @Test
    void whenGetRover_callService() {
        // Given
        Long roverId = 1L;
        // When
        controller.getRover(roverId);
        // Then
        verify(service, times(1)).getRover(1L);
    }

    @Test
    void whenGetAllRovers_callService() {
        // Given

        // When
        controller.getAllRovers();
        // Then
        verify(service, times(1)).getAllRovers();
    }

    @Test
    void whenDeleteRover_callService() {
        // Given
        Long roverId = 1L;
        // When
        controller.deleteRover(roverId);
        // Then
        verify(service, times(1)).deleteRover(roverId);
    }
}
