package com.pecado.del_sol_mars_rover.controllers;

import com.pecado.del_sol_mars_rover.dto.MapDto;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.services.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to manage maps.
 * You can create, get, list, and delete maps.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/map")
public class MapController {

    @Autowired
    private final MapService Service;

    @Operation(summary = "Create a new map", description = "Create a new map with name, width, and height.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid map data")
    })
    @PostMapping()
    public void createMap(@RequestBody MapDto mapDto) {
        System.out.println("Messaje createMap --->");
        Service.createMap(mapDto);
    }

    @Operation(summary = "Get a map by ID", description = "Get the details of one map using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Map found"),
            @ApiResponse(responseCode = "404", description = "Map not found")
    })
    @GetMapping("/{id}")
    public Map getMap(@PathVariable Long id) {
        System.out.println("Messaje getMap ---> ID: " + id);
        return Service.getMap(id);
    }

    @Operation(summary = "Get all maps", description = "Get a list of all maps.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of maps returned")
    })
    @GetMapping()
    public List<Map> getAllMaps() {
        System.out.println("Messaje getAllMaps ---> called");
        return Service.getAllMaps();
    }

    @Operation(summary = "Delete a map", description = "Delete a map using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Map deleted"),
            @ApiResponse(responseCode = "404", description = "Map not found")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteMap(@PathVariable Long id) {
        System.out.println("Messaje deleteMap ---> ID: " + id);
        Service.deleteMap(id);
    }

}
