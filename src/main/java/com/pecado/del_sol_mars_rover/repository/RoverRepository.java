package com.pecado.del_sol_mars_rover.repository;

import com.pecado.del_sol_mars_rover.dto.RoverResponseDto;
import com.pecado.del_sol_mars_rover.entities.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoverRepository extends JpaRepository<Rover, Long> {
    @Query("SELECT new com.pecado.del_sol_mars_rover.dto.RoverResponseDto(r.id, r.x, r.y, r.direction, r.map.Id) FROM Rover r")
    List<RoverResponseDto> findAllResponseDto();

    @Query("SELECT new com.pecado.del_sol_mars_rover.dto.RoverResponseDto(r.id, r.x, r.y, r.direction, r.map.id) " +
            "FROM Rover r WHERE r.id = :id")
    Optional<RoverResponseDto> findByIdResponseDto(Long id);
}