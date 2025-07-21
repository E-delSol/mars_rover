package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.entities.Direction;
import com.pecado.del_sol_mars_rover.entities.Rover;
import com.pecado.del_sol_mars_rover.repository.RoverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.pecado.del_sol_mars_rover.entities.Direction.N;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoverServiceTest {
    private RoverRepository roverRepository;
    private RoverService roverService;
    private Rover rover;

    @BeforeEach
    void setUp() {
        roverRepository = mock(RoverRepository.class);
        roverService = new RoverService(roverRepository);

        // Initialize a test rover
        rover = new Rover();
        rover.setId(1L);
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(N);

        when(roverRepository.findById(1L)).thenReturn(Optional.of(rover));
    }

    @Test
    void testMoveForward() {
        roverService.execcuteCommands(1L, "f");
        assertEquals(0, rover.getX());
        assertEquals(1, rover.getY());
    }

    @Test
    void testTurnRightAndMove() {
        roverService.execcuteCommands(1L, "rf");
        assertEquals(1, rover.getX());
        assertEquals(0, rover.getY());
        assertEquals(Direction.E, rover.getDirection());
    }

    @Test
    void testObstacleDetected() {
        rover.setX(10);
        rover.setY(9);
        rover.setDirection(N);
        Exception ex = assertThrows(RuntimeException.class,
                () -> roverService.execcuteCommands(1L, "f"));
        assertTrue(ex.getMessage().contains("Obstacle detected"));
    }

}
