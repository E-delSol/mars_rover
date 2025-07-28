package com.pecado.del_sol_mars_rover.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ObstacleResponseDto {
    private Long id;
    private Integer x;
    private Integer y;
    private Long mapId;
}
