package com.pecado.del_sol_mars_rover.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "map")
public class Map {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "width", nullable = false)
    private Integer width;
    @Column(name = "height", nullable = false)
    private Integer height;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "map_id")
    @JsonIgnore
    private List<Rover> roverList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "map_id")
    @JsonIgnore
    private List<Obstacle> obstacleList = new ArrayList<>();

    public Integer wrapX(Integer x) {
        return (x % width + width) % width;
    }
    public Integer wrapY(Integer y) {
        return (y % height + height) % height;
    }
}
