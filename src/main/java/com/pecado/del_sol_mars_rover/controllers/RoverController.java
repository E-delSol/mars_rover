package com.pecado.del_sol_mars_rover.controllers;

import com.pecado.del_sol_mars_rover.dto.RoverDto;
import com.pecado.del_sol_mars_rover.dto.RoverResponseDto;
import com.pecado.del_sol_mars_rover.services.RoverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller manages Rover resources.
 * You can create, get, delete and send commands to rovers.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/rover")
public class RoverController {

    @Autowired
    private final RoverService service;

    @Operation(summary = "Create a new rover", description = "Create a rover and place it in a map.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rover created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping
    public void createRover(@RequestBody RoverDto roverDto) {
        System.out.println("Mensaje createRover ---> " + roverDto);
        service.createRover(roverDto);
    }

    @Operation(summary = "Send commands to a rover", description = "Send a list of commands to move the rover (f, b, l, r).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commands sent successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid command or rover not found")
    })
    @PostMapping("/{id}/commands")
    public void sendCommands(@PathVariable Long id, @RequestBody String commands) {
        System.out.println("Mensaje sendCommands ---> " + commands);

        service.sendCommands(id, commands);

        for (char command : commands.toCharArray()) {
            System.out.println("Processing command: " + command);
        }
    }

    @Operation(summary = "Get one rover by ID", description = "Get the rover with the selected ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rover found"),
            @ApiResponse(responseCode = "404", description = "Rover not found")
    })
    @GetMapping("/{id}")
    public RoverResponseDto getRover(@PathVariable Long id) {
        System.out.println("Mensaje getRover ---> " + id);
        RoverResponseDto rover = service.getRover(id);
        System.out.println("Rover details: " + rover);
        return rover;
    }

    @Operation(summary = "Get all rovers", description = "Returns a list with all rovers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rovers found")
    })
    @GetMapping()
    public List<RoverResponseDto> getAllRovers() {
        System.out.println("Mensaje getAllRovers ---> called");
        return service.getAllRovers();
    }

    @Operation(summary = "Delete a rover", description = "Delete a rover with the selected ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rover deleted"),
            @ApiResponse(responseCode = "404", description = "Rover not found")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteRover(@PathVariable Long id) {
        System.out.println("Mensaje deleteRover ---> " + id);
        service.deleteRover(id);
    }

}
