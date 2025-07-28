package com.pecado.del_sol_mars_rover.entities;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {

    @Test
    void wrapX_shouldWrapAround_whenXExceedsWidth() {
        // Given
        Map map = new Map();
        map.setWidth(5);

        // When
        Integer wrappedAt5 = map.wrapX(5);
        Integer wrappedAt6 = map.wrapX(6);
        Integer wrappedAtMinus1 = map.wrapX(-1);

        // Then
        assertEquals(0, wrappedAt5); // 7 % 5 = 2
        assertEquals(1, wrappedAt6); // 6 % 5 = 1
        assertEquals(4, wrappedAtMinus1); // 4 % 5 = 4
    }

    @Test
    void wrapY_shouldWrapAround_whenYExceedsHeight() {
        // Given
        Map map = new Map();
        map.setHeight(5);

        // When
        Integer wrappedAt5 = map.wrapY(5);
        Integer wrappedAt6 = map.wrapY(6);
        Integer wrappedAtMinus1 = map.wrapY(-1);

        // Then
        assertEquals(0, wrappedAt5); // 7 % 5 = 2
        assertEquals(1, wrappedAt6); // 6 % 5 = 1
        assertEquals(4, wrappedAtMinus1); // 4 % 5 = 4
    }

}
