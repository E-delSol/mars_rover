package com.pecado.del_sol_mars_rover.dto;

import com.pecado.del_sol_mars_rover.entities.Direction;
import lombok.Data;

@Data
public class RoverResponseDto {
    private Long id;
    private Integer x;
    private Integer y;
    private Direction direction;
    private Long mapId;

    public RoverResponseDto(Long id, Integer x, Integer y, Direction direction, Long mapId) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.mapId = mapId;
    }

}
