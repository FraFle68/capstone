package de.neuefische.backend.game.dungeon;

import de.neuefische.backend.game.core.Room;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DungeonCreatorTest {





    @Test
    void testCreateDungeonWithStartPosition() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(0, 3,3));
        int hSize = 50;
        int vSize = 50;
        int floor = 0;
        Dungeon dungeon = DungeonCreator.createDungeon(hSize, vSize, floor, rooms);

        int[][] contentMap = dungeon.contentMap;
        boolean startPositionFound = false;

        for (int i = 0; i < vSize; i++) {
            for (int j = 0; j < hSize; j++) {
                if (contentMap[i][j] == 99) {
                    startPositionFound = true;
                    break;
                }
            }
            if (startPositionFound) {
                break;
            }
        }

        assertTrue(startPositionFound, "Dungeon should contain a start position (content with value 99).");
    }
}