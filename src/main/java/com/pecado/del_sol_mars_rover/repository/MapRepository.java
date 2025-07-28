package com.pecado.del_sol_mars_rover.repository;

import com.pecado.del_sol_mars_rover.entities.Map;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<Map, Long> {
}
