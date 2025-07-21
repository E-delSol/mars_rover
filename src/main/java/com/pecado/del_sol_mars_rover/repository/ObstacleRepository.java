package com.pecado.del_sol_mars_rover.repository;

import com.pecado.del_sol_mars_rover.entities.Obstacle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
}
