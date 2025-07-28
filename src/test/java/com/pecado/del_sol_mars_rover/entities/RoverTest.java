package com.pecado.del_sol_mars_rover.entities;

import org.junit.jupiter.api.Test;

public class RoverTest {
    // Test cases for forward movement
    @Test
    void moveForward_whenFacingNorth_incrementsY() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.N);

        // When
        rover.moveForward();

        // Then
        assert rover.getX() == 0;
        assert rover.getY() == 1;
    }

    @Test
    void moveForward_whenFacingEast_incrementsX() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.E);

        // When
        rover.moveForward();

        // Then
        assert rover.getX() == 1;
        assert rover.getY() == 0;
    }

    @Test
    void moveForward_whenFacingSouth_decrementsY() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.S);

        // When
        rover.moveForward();

        // Then
        assert rover.getX() == 0;
        assert rover.getY() == -1;
    }

    @Test
    void moveForward_whenFacingWest_decrementsX() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.W);

        // When
        rover.moveForward();

        // Then
        assert rover.getX() == -1;
        assert rover.getY() == 0;
    }

    // Test cases for backward movement
    @Test
    void moveBackward_whenFacingNorth_decrementsY() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.N);

        // When
        rover.moveBackward();

        // Then
        assert rover.getX() == 0;
        assert rover.getY() == -1;
    }

    @Test
    void moveBackward_whenFacingEast_decrementsX() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.E);

        // When
        rover.moveBackward();

        // Then
        assert rover.getX() == -1;
        assert rover.getY() == 0;
    }

    @Test
    void moveBackward_whenFacingSouth_incrementsY() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.S);

        // When
        rover.moveBackward();

        // Then
        assert rover.getX() == 0;
        assert rover.getY() == 1;
    }

    @Test
    void moveBackward_whenFacingWest_incrementsX() {
        // Given
        Rover rover = new Rover();
        rover.setX(0);
        rover.setY(0);
        rover.setDirection(Direction.W);

        // When
        rover.moveBackward();

        // Then
        assert rover.getX() == 1;
        assert rover.getY() == 0;
    }

    // Test cases for turning left
    @Test
    void turnLeft_whenFacingNorth_changesDirectionToWest() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.N);

        // When
        rover.turnLeft();

        // Then
        assert rover.getDirection() == Direction.W;
    }

    @Test
    void turnLeft_whenFacingEast_changesDirectionToNorth() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.E);

        // When
        rover.turnLeft();

        // Then
        assert rover.getDirection() == Direction.N;
    }

    @Test
    void turnLeft_whenFacingSouth_changesDirectionToEast() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.S);

        // When
        rover.turnLeft();

        // Then
        assert rover.getDirection() == Direction.E;
    }

    @Test
    void turnLeft_whenFacingWest_changesDirectionToSouth() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.W);

        // When
        rover.turnLeft();

        // Then
        assert rover.getDirection() == Direction.S;
    }

    // Test cases for turning right
    @Test
    void turnRight_whenFacingNorth_changesDirectionToEast() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.N);

        // When
        rover.turnRight();

        // Then
        assert rover.getDirection() == Direction.E;
    }

    @Test
    void turnRight_whenFacingEast_changesDirectionToSouth() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.E);

        // When
        rover.turnRight();

        // Then
        assert rover.getDirection() == Direction.S;
    }

    @Test
    void turnRight_whenFacingSouth_changesDirectionToWest() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.S);

        // When
        rover.turnRight();

        // Then
        assert rover.getDirection() == Direction.W;
    }

    @Test
    void turnRight_whenFacingWest_changesDirectionToNorth() {
        // Given
        Rover rover = new Rover();
        rover.setDirection(Direction.W);

        // When
        rover.turnRight();

        // Then
        assert rover.getDirection() == Direction.N;
    }


}
