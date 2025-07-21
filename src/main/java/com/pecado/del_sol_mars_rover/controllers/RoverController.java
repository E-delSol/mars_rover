package com.pecado.del_sol_mars_rover.controllers;

import com.pecado.del_sol_mars_rover.entities.Rover;
import com.pecado.del_sol_mars_rover.services.RoverService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rovers")
public class RoverController {
    private final RoverService roverService;

    public RoverController(RoverService roverService) {
        this.roverService = roverService;
    }

    @PostMapping("/{id}/commands")
    public Rover sendCommands(@PathVariable Long id, @RequestBody String commands) {
        return roverService.execcuteCommands(id, commands);
    }
}
