package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.MapDto;
import com.pecado.del_sol_mars_rover.entities.Map;

import java.util.List;

public interface MapService{
    void createMap(MapDto mapDto);
    Map getMap(Long id);
    List<Map> getAllMaps();
    void deleteMap(Long id);
}
