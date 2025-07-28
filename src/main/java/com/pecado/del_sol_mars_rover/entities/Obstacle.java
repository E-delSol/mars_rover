package com.pecado.del_sol_mars_rover.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "obstacle")
public class Obstacle {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "obstacle_id")
    private Long id;
    @Column(name = "x_coordinate", nullable = false)
    private Integer x;
    @Column(name = "y_coordinate", nullable = false)
    private Integer y;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id", nullable = false)
    private Map map;

}
