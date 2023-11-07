package de.neuefische.backend.service;

import de.neuefische.backend.game.core.Room;
import de.neuefische.backend.game.core.Vector2d;
import de.neuefische.backend.game.dungeon.Dungeon;
import de.neuefische.backend.game.dungeon.DungeonCreator;
import de.neuefische.backend.model.GameMap;
import de.neuefische.backend.repository.GameRepository;
import de.neuefische.backend.repository.RoomRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameServiceTest {
    private final GameRepository mockGameRepo = mock(GameRepository.class);
    private final RoomRepository mockRoomRepo = mock(RoomRepository.class);
    final DungeonCreator mockDungeonCreator = mock(DungeonCreator.class);


    GameService gameService = new GameService(mockGameRepo, mockRoomRepo);

    @Test
    void testGetMap_withMapInDatabase() {

        GameMap expectedMap = new GameMap("123", new Dungeon(), new Vector2d(0, 0));
        when(mockGameRepo.findById("123")).thenReturn(Optional.of(expectedMap));

        GameMap result = gameService.getMap("123");
        //THEN
        verify(mockGameRepo).findById("123");

        assertEquals(expectedMap, result);
    }

    @Test
    void testGetMap_withoutMapInDatabase() {

        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, 5, 5));

        GameMap expectedMap = new GameMap("123", new Dungeon(), new Vector2d(0, 0));
        when(mockGameRepo.findById("123")).thenReturn(null);
        when(mockDungeonCreator.createDungeon(50, 50, 0, rooms)).thenReturn(new Dungeon());

        GameMap result = gameService.getMap("123");
        //THEN
        verify(mockGameRepo).findById("123");

        assertEquals(expectedMap, result);
    }

    @Test
    void testDeleteMap() {
        //GIVEN
        String id = "123";

        //WHEN
        gameService.deleteMap(id);

        //THEN
        verify(mockGameRepo).deleteById(id);
    }


}