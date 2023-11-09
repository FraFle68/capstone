package de.neuefische.backend.service;

import de.neuefische.backend.game.core.Vector2d;
import de.neuefische.backend.game.dungeon.Dungeon;
import de.neuefische.backend.model.GameMap;
import de.neuefische.backend.repository.GameRepository;
import de.neuefische.backend.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMapExistingMap() {
        String gameId = "1";
        GameMap expectedMap = new GameMap(gameId, new Dungeon(50, 50, 0), new Vector2d(0, 0));

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(expectedMap));

        GameMap retrievedMap = gameService.getMap(gameId);

        assertEquals(expectedMap, retrievedMap);
        verify(gameRepository, times(1)).findById(gameId);
        verify(gameRepository, times(1)).save(expectedMap);
    }

    /*@Test
    void testGetMapNewMap() {
        String gameId = "2";

        when(gameRepository.findById(gameId)).thenReturn(Optional.empty());
        when(roomRepository.findAll()).thenReturn(new ArrayList<>());

        GameMap retrievedMap = gameService.getMap(gameId);

        assertEquals(gameId, retrievedMap.id());
        verify(gameRepository, times(1)).findById(gameId);
        verify(gameRepository, times(1)).save(any(GameMap.class));
    }*/

    @Test
    void testDeleteMap() {
        String gameId = "1";

        gameService.deleteMap(gameId);

        verify(gameRepository, times(1)).deleteById(gameId);
    }

    @Test
    void testChangePosition() {
        String gameId = "1";
        Vector2d newPosition = new Vector2d(1, 1);
        GameMap existingMap = new GameMap(gameId, new Dungeon(50, 50, 0), new Vector2d(1, 1));

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(existingMap));
        when(gameRepository.save(any(GameMap.class))).thenReturn(existingMap);

        GameMap updatedMap = gameService.changePosition(gameId, newPosition);

        assertEquals(newPosition, updatedMap.position());
        verify(gameRepository, times(1)).findById(gameId);
        verify(gameRepository, times(1)).save(any(GameMap.class));
    }

    @Test
    void testGetItem() {
        String gameId = "1";
        Vector2d position = new Vector2d(1, 1);
        GameMap existingMap = new GameMap(gameId, new Dungeon(50, 50, 0), new Vector2d(0, 0));

        when(gameRepository.findById(gameId)).thenReturn(Optional.of(existingMap));
        when(gameRepository.save(any(GameMap.class))).thenReturn(existingMap);

        GameMap updatedMap = gameService.getItem(gameId, position);

        assertEquals(0, updatedMap.dungeon().getContentMap()[position.getY()][position.getX()]);
        verify(gameRepository, times(1)).findById(gameId);
        verify(gameRepository, times(1)).save(any(GameMap.class));
    }
}
