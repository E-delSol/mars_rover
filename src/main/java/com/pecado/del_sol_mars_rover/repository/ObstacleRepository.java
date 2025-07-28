package com.pecado.del_sol_mars_rover.repository;

import com.pecado.del_sol_mars_rover.dto.ObstacleResponseDto;
import com.pecado.del_sol_mars_rover.entities.Obstacle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
    List<ObstacleResponseDto> findByMapId(Long mapId);
}
