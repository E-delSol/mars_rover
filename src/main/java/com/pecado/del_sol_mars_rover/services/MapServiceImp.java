package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.MapDto;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImp implements MapService {

    @Autowired
    private MapRepository mapRepository;

    @Override
    public void createMap(MapDto mapDto) {
        if (mapDto.getWidth() <= 0 || mapDto.getHeight() <= 0) {
            throw new IllegalArgumentException("Map dimensions must be greater than zero.");
        }

        Map map = mapDto.toEntity();
        mapRepository.save(map);
    }

    @Override
    public Map getMap(Long id) {
        return mapRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Map not found with ID: " + id));
    }

    @Override
    public List<Map> getAllMaps() {
        return mapRepository.findAll();
    }

    @Override
    public void deleteMap(Long id) {
        if (!mapRepository.existsById(id)){
            throw new IllegalArgumentException("Map not found with ID: " + id);
        }
        mapRepository.deleteById(id);
    }
}
