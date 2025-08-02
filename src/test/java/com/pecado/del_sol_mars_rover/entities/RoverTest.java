package com.pecado.del_sol_mars_rover.entities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTest {

    // This test class contains parameterized tests for the Rover entity.
    @ParameterizedTest
    @CsvSource({
            "N, f, 0, 1",
            "N, b, 0, -1",
            "E, f, 1, 0",
            "E, b, -1, 0",
            "S, f, 0, -1",
            "S, b, 0, 1",
            "W, f, -1, 0",
            "W, b, 1, 0"
    })
    void moveRover(Direction direction, char command, int expectedX, int expectedY) {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(direction);

        // When
        if (command == 'f') {
            rover.moveForward();
        } else {
            rover.moveBackward();
        }

        // Then
        assertEquals(expectedX, rover.getX());
        assertEquals(expectedY, rover.getY());
    }

    // This test class contains parameterized tests for the Rover entity.
    @ParameterizedTest
    @CsvSource({
            "N, L, W",
            "E, L, N",
            "S, L, E",
            "W, L, S",
            "N, R, E",
            "E, R, S",
            "S, R, W",
            "W, R, N"
    })
    void turnRover(Direction startDirection, char turn, Direction expectedDirection) {
        // Given
        Rover rover = new Rover();
        rover.setDirection(startDirection);

        // When
        if (turn == 'L') {
            rover.turnLeft();
        } else {
            rover.turnRight();
        }

        // Then
        assertEquals(expectedDirection, rover.getDirection());
    }

}
