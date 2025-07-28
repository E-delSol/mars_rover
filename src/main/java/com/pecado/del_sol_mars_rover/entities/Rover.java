package com.pecado.del_sol_mars_rover.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rover")
public class Rover {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "rover_id")
    private Long id;
    @Column(name = "x_coordinate", nullable = false)
    private Integer x;
    @Column(name = "y_coordinate", nullable = false)
    private Integer y;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id", nullable = false)
    private Map map;
    @Enumerated(EnumType.STRING)
    @Column(name = "direction", nullable = false)
    private Direction direction;

    public void moveForward() {
        switch (direction) {
            case N:
                y++;
                break;
            case S:
                y--;
                break;
            case E:
                x++;
                break;
            case W:
                x--;
                break;
        }
    }

    public void moveBackward() {
        switch (direction) {
            case N:
                y--;
                break;
            case S:
                y++;
                break;
            case E:
                x--;
                break;
            case W:
                x++;
                break;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case N:
                direction = Direction.W;
                break;
            case S:
                direction = Direction.E;
                break;
            case E:
                direction = Direction.N;
                break;
            case W:
                direction = Direction.S;
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case N:
                direction = Direction.E;
                break;
            case S:
                direction = Direction.W;
                break;
            case E:
                direction = Direction.S;
                break;
            case W:
                direction = Direction.N;
                break;
        }
    }
}
