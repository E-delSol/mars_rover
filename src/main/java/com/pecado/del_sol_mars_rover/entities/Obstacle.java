package com.pecado.del_sol_mars_rover.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Obstacle {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private int x;
    private int y;

    public Obstacle(Long id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

}
