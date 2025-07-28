package com.pecado.del_sol_mars_rover.dto;

import com.pecado.del_sol_mars_rover.entities.Direction;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.entities.Rover;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RoverDto {
    private Integer x;
    private Integer y;
    private Direction direction;
    private Long mapId;

    public Rover toEntity(Map map) {
        Rover rover = new Rover();
        rover.setX(x);
        rover.setY(y);
        rover.setDirection(direction);
        rover.setMap(map);
        return rover;
    }
}
