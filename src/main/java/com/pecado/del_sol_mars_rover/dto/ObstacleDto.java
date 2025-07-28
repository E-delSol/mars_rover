package com.pecado.del_sol_mars_rover.dto;

import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.entities.Obstacle;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ObstacleDto {
    private Integer x;
    private Integer y;
    private Long mapId;

    public Obstacle toEntity(Map map) {
        Obstacle obstacle = new Obstacle();
        obstacle.setX(x);
        obstacle.setY(y);
        obstacle.setMap(map);
        return obstacle;
    }

}
