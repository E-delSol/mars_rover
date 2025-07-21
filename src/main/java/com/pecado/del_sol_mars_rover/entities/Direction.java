package com.pecado.del_sol_mars_rover.entities;

public enum Direction {
    N, E, S, W;
    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }
    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }
}
