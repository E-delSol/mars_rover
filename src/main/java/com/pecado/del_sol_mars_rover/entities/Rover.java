package com.pecado.del_sol_mars_rover.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rover {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private int x;
    private int y;
    @Enumerated(EnumType.ORDINAL)
    private Direction direction;

}
