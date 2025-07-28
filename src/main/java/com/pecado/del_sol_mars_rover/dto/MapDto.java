package com.pecado.del_sol_mars_rover.dto;

import com.pecado.del_sol_mars_rover.entities.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MapDto {
    private String name;
    private Integer width;
    private Integer height;

    public Map toEntity() {
        Map map = new Map();
        map.setName(name);
        map.setWidth(width);
        map.setHeight(height);
        return map;
    }
}
