package com.pecado.del_sol_mars_rover.controllers;

import com.pecado.del_sol_mars_rover.dto.ObstacleDto;
import com.pecado.del_sol_mars_rover.dto.ObstacleResponseDto;
import com.pecado.del_sol_mars_rover.services.ObstacleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller manages obstacles in a map.
 * You can create, get, and delete obstacles.
 */
@RestController
@RequestMapping("/api/obstacle")
@AllArgsConstructor
public class ObstacleController {

    @Autowired
    private final ObstacleService service;

    @Operation(summary = "Create a new obstacle", description = "Add a new obstacle in the map at a specific position.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obstacle created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid obstacle data")
    })
    @PostMapping()
    public void createObstacle(@RequestBody ObstacleDto obstacleDto) {
        System.out.println("Mensaje createObstacle ---> " + obstacleDto);
        service.createObstacle(obstacleDto);
    }

    @Operation(summary = "Get all obstacles in a map", description = "Get a list of all obstacles inside a map using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of obstacles returned"),
            @ApiResponse(responseCode = "404", description = "Map not found or has no obstacles")
    })
    @GetMapping("/{mapId}")
    public List<ObstacleResponseDto> getObstaclesByMap(@PathVariable Long mapId) {
        System.out.println("Mensaje getAllObstacles ---> called");
        return service.getObstaclesByMap(mapId);
    }

    @Operation(summary = "Delete an obstacle", description = "Delete one obstacle using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Obstacle deleted"),
            @ApiResponse(responseCode = "404", description = "Obstacle not found")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteObstacle(@PathVariable Long id) {
        System.out.println("Mensaje deleteObstacle ---> " + id);
        service.deleteObstacle(id);
    }
}
