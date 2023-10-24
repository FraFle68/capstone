package de.neuefische.backend.game.dungeon;

import de.neuefische.backend.game.core.Room;
import de.neuefische.backend.game.core.Vector2d;

import java.util.List;
import java.util.Random;

public class DungeonCreator {

    static Random random = new Random();

    private static int[][] createRooms(Dungeon map, List<Room> rooms) {
        int hSize = map.tileMap.length;
        int vSize = map.tileMap[0].length;
        int[][] tileMap = new int[hSize][vSize];
        for (int i = 0; i < 50; i++) {
            Room newRoom = rooms.get(random.nextInt(rooms.size()));
            Vector2d position = new Vector2d(random.nextInt(hSize - newRoom.getHSize() - 1) + 1, random.nextInt(vSize - newRoom.getVSize() - 1) + 1);
            boolean possiblePlace = true;
            for (int k = 0; k < newRoom.getHSize() + 2; k++) {
                for (int l = 0; l < newRoom.getVSize() + 2; l++) {
                    if (tileMap[k][l] == 1) {
                        possiblePlace = false;
                        break;
                    }
                }
                if (!possiblePlace) {
                    break;
                }
            }
            if (possiblePlace) {
                for (int k = position.getX(); k < position.getX() + newRoom.getHSize(); k++) {
                    for (int l = position.getY(); l < position.getY() + newRoom.getVSize(); l++) {
                        tileMap[k][l] = 1;
                    }
                }
            }
        }
        return tileMap;
    }

    private static int[][] createContent(Dungeon map) {
        int[][] contentMap = new int[map.contentMap.length][map.contentMap[0].length];
        contentMap[0][0] = 7;
        return contentMap;
    }

    public static Dungeon createDungeon(int hSize, int vSize, int floor, List<Room> rooms) {

        Dungeon map = new Dungeon(vSize, hSize, floor);
        map.floor = floor;
        map.tileMap = createRooms(map, rooms);
        map.contentMap = createContent(map);
        return map;
    }


}
