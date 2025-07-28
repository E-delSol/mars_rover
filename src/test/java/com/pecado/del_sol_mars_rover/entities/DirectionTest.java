package com.pecado.del_sol_mars_rover.entities;

import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void testTurnLeft() {
        // Test turning left from each direction
        assert Direction.N.turnLeft() == Direction.W;
        assert Direction.E.turnLeft() == Direction.N;
        assert Direction.S.turnLeft() == Direction.E;
        assert Direction.W.turnLeft() == Direction.S;
    }

    @Test
    public void testTurnRight() {
        // Test turning right from each direction
        assert Direction.N.turnRight() == Direction.E;
        assert Direction.E.turnRight() == Direction.S;
        assert Direction.S.turnRight() == Direction.W;
        assert Direction.W.turnRight() == Direction.N;
    }

}
