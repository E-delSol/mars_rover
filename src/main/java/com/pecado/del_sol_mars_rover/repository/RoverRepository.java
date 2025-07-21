package com.pecado.del_sol_mars_rover.repository;

import com.pecado.del_sol_mars_rover.entities.Rover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoverRepository extends JpaRepository<Rover, Long> {
}
