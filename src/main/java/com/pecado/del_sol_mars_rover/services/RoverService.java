package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.RoverDto;
import com.pecado.del_sol_mars_rover.dto.RoverResponseDto;

import java.util.List;

public interface RoverService {
    void createRover(RoverDto roverDto);
    void sendCommands(Long id, String commands);
    RoverResponseDto getRover(Long id);
    List<RoverResponseDto> getAllRovers();
    void deleteRover(Long id);
}
