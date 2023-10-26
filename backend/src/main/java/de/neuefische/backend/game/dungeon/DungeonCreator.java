package de.neuefische.backend.game.dungeon;

import de.neuefische.backend.game.core.Room;
import de.neuefische.backend.game.core.Vector2d;

import java.util.List;
import java.util.Random;

public class DungeonCreator {

    private DungeonCreator() {}

    static Random random = new Random();

    private static int[][] createRooms(Dungeon map, List<Room> rooms) {
        int vSize = map.tileMap.length;
        int hSize = map.tileMap[0].length;
        int[][] tileMap = new int[vSize][hSize];
        for (int i = 0; i < 50; i++) {
            Room newRoom = rooms.get(random.nextInt(rooms.size()));
            Vector2d position = new Vector2d(
                    random.nextInt(hSize - newRoom.getHSize() - 2) + 1,
                    random.nextInt(vSize - newRoom.getVSize() - 2) + 1);
            boolean possiblePlace = true;
            for (int k = position.getY() - 1; k < position.getY() + newRoom.getVSize() + 2; k++) {
                for (int l = position.getX() - 1; l < position.getX() + newRoom.getHSize() + 2; l++) {
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
                for (int k = position.getY(); k < position.getY() + newRoom.getVSize(); k++) {
                    for (int l = position.getX(); l < position.getX() + newRoom.getHSize(); l++) {
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
