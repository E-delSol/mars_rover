package com.pecado.del_sol_mars_rover.services;

import com.pecado.del_sol_mars_rover.dto.RoverDto;
import com.pecado.del_sol_mars_rover.dto.RoverResponseDto;
import com.pecado.del_sol_mars_rover.entities.Direction;
import com.pecado.del_sol_mars_rover.entities.Map;
import com.pecado.del_sol_mars_rover.entities.Rover;
import com.pecado.del_sol_mars_rover.repository.MapRepository;
import com.pecado.del_sol_mars_rover.repository.RoverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoverServiceImpTest {

    @Mock
    private RoverRepository roverRepository;
    @Mock
    private MapRepository mapRepository;
    @Mock
    private ObstacleService obstacleService;
    @InjectMocks
    private RoverServiceImp roverService;

    private Map defaultMap;

    @BeforeEach
    void setUp() {
        defaultMap = new Map();
        defaultMap.setId(1L);
        defaultMap.setWidth(10);
        defaultMap.setHeight(10);
    }

    private Rover createRover(int x, int y, Direction direction) {
        Rover rover = new Rover();
        rover.setId(1L);
        rover.setX(x);
        rover.setY(y);
        rover.setDirection(direction);
        rover.setMap(defaultMap);
        return rover;
    }

    @Test
    void givenValidData_whenCreateRover_thenSavesRover() {
        // Given
        RoverDto roverDto = new RoverDto(0,0, Direction.N, 1L);
        Map mockMap = new Map();
        mockMap.setId(1L);
        when(mapRepository.findById(roverDto.getMapId())).thenReturn(Optional.of(mockMap));

        // When
        roverService.createRover(roverDto);

        // Then
        verify(roverRepository).save(argThat(r ->
                Objects.equals(r.getX(), roverDto.getX()) &&
                        Objects.equals(r.getY(), roverDto.getY()) &&
                r.getDirection() == Direction.N &&
                r.getMap().equals(mockMap)
        ));
    }

    @Test
    void givenNonExistingMap_whenCreateRover_thenThrowsException() {
        // Given
        RoverDto roverDto = new RoverDto(0, 0, Direction.N, 99L);
        when(mapRepository.findById(roverDto.getMapId())).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> roverService.createRover(roverDto));

        // Then
        assertEquals("Map not found with ID: 99", exception.getMessage());
    }

    @Test
    void givenNonExistingRover_whenSendCommands_thenThrowsException() {
        // Given
        when(roverRepository.findById(99L)).thenReturn(Optional.empty());

        // When
        Exception ex = assertThrows(IllegalArgumentException.class, () -> roverService.sendCommands(99L, "ffrfflff"));

        // Then
        assertEquals("Rover not found with ID: 99", ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "N, f, 0, 1",
            "N, b, 0, -1",
            "E, f, 1, 0",
            "E, b, -1, 0",
            "S, f, 0, -1",
            "S, b, 0, 1",
            "W, f, -1, 0",
            "W, b, 1, 0"
    })
    void testRoverMovement(Direction direction, char command, int expectedX, int expectedY) {
        // Given
        Rover roverMock = createRover(5, 5, direction);
        when(roverRepository.findById(1L)).thenReturn(Optional.of(roverMock));
        System.out.println("Testing movement: " + direction + " " + command);
        // When
        roverService.sendCommands(1L, String.valueOf(command));

        // Then
        verify(roverRepository).save(argThat(r ->
                r.getX() == 5 + expectedX &&
                r.getY() == 5 + expectedY &&
                r.getDirection() == direction
        ));
    }

    @Test
    void givenRoverDirectionNull_whenSendCommands_thenThrowsException() {
        // Given
        Rover roverMock = createRover(0, 0, null);
        when(roverRepository.findById(1L)).thenReturn(Optional.of(roverMock));

        // When
        Exception ex = assertThrows(IllegalArgumentException.class, () -> roverService.sendCommands(1L, "f"));

        // Then
        assertEquals("Rover direction is not set.", ex.getMessage());
    }

    @Test
    void givenRoverFacingEast_whenCommandsAreFBLR_thenFinalPositionIsCorrect() {
        // Given
        Rover roverMock = createRover(0, 0, Direction.E);
        when(roverRepository.findById(1L)).thenReturn(Optional.of(roverMock));

        // When
        roverService.sendCommands(1L, "fflffrb");

        // Then
        verify(roverRepository).save(argThat(r ->
                r.getX() == 1 &&
                        r.getY() == 2 &&
                        r.getDirection() == Direction.E
        ));
    }

    @Test
    void givenInvalidCommand_whenSendCommands_thenThrowsException() {
        // Given
        Rover roverMock = createRover(0, 0, Direction.N);
        when(roverRepository.findById(1L)).thenReturn(Optional.of(roverMock));

        // When
        Exception ex = assertThrows(IllegalArgumentException.class, () -> roverService.sendCommands(1L, "ffx"));

        // Then
        assertEquals("Invalid command: x", ex.getMessage());
    }

    @Test
    void givenObstacleAtPosition_whenSendCommands_thenRoverStopBeforeObstacle() {
        // Given
        Rover roverMock = createRover(0, 0, Direction.N);
        when(roverRepository.findById(1L)).thenReturn(Optional.of(roverMock));
        when(obstacleService.isObstacleAtPosition(roverMock.getMap().getId(), 0, 1)).thenReturn(true);

        // When
        roverService.sendCommands(1L, "f");

        // Then
        verify(roverRepository).save(argThat(r ->
                r.getX() == 0 &&
                r.getY() == 0 &&
                r.getDirection() == Direction.N
        ));
    }

    @Test
    void givenId_whenGetRover_thenReturnsRoverResponseDto() {
        // Given
        RoverResponseDto roverMock = new RoverResponseDto(1L, 0, 0, Direction.N, 1L);
        when(roverRepository.findByIdResponseDto(1L)).thenReturn(Optional.of(roverMock));

        // When
        RoverResponseDto result = roverService.getRover(1L);

        // Then
        assertEquals(roverMock, result);
    }

    @Test
    void whenGetAllRovers_thenReturnsAllRovers() {
        // Given
        RoverResponseDto rover1 = new RoverResponseDto(1L, 0, 0, Direction.N, 1L);
        RoverResponseDto rover2 = new RoverResponseDto(2L, 1, 1, Direction.N, 1L);
        when(roverRepository.findAllResponseDto()).thenReturn(List.of(rover1, rover2));

        // When
        List<RoverResponseDto> result = roverService.getAllRovers();

        // Then
        assertEquals(2, result.size());
        assertEquals(rover1, result.get(0));
        assertEquals(rover2, result.get(1));
    }

    @Test
    void givenRoverId_whenDeleteRover_thenDeletesRover() {
        // Given
        Long roverId = 1L;
        when(roverRepository.existsById(roverId)).thenReturn(true);
        // When
        roverService.deleteRover(roverId);

        // Then
        verify(roverRepository).deleteById(roverId);
    }

    @Test
    void givenNonExistingRoverId_whenDeleteRover_thenThrowsException() {
        // Given
        Long roverId = 99L;
        when(roverRepository.existsById(roverId)).thenReturn(false);

        // When
        Exception exception = assertThrows(IllegalArgumentException.class, () -> roverService.deleteRover(roverId));

        // Then
        assertEquals("Rover not found with ID: " + roverId, exception.getMessage());
    }

}
