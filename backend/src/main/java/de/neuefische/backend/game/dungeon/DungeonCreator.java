package de.neuefische.backend.game.dungeon;

import de.neuefische.backend.game.core.Room;
import de.neuefische.backend.game.core.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonCreator {

    private DungeonCreator() {}

    static Random random = new Random();
    static int[][] tileMap;

    private static void createCorridor(Vector2d actualPosition) {
        int hPos = actualPosition.getX();
        int vPos = actualPosition.getY();
        tileMap[vPos][hPos] = 2;
        List<Vector2d> possibleNextPosition = new ArrayList<>();

        do {
            possibleNextPosition.clear();
            if (vPos > 1 && tileMap[vPos - 2][hPos] == 0 && tileMap[vPos - 2][hPos - 1] == 0 && tileMap[vPos - 2][hPos + 1] == 0
                    && tileMap[vPos - 1][hPos] == 0 && tileMap[vPos - 1][hPos - 1] == 0 && tileMap[vPos - 1][hPos + 1] == 0) {
                possibleNextPosition.add(new Vector2d(hPos, vPos - 1));
            }
            if (vPos < tileMap.length - 2 && tileMap[vPos + 2][hPos] == 0 && tileMap[vPos + 2][hPos - 1] == 0 && tileMap[vPos + 2][hPos + 1] == 0
                    && tileMap[vPos + 1][hPos] == 0 && tileMap[vPos + 1][hPos - 1] == 0 && tileMap[vPos + 1][hPos + 1] == 0) {
                possibleNextPosition.add(new Vector2d(hPos, vPos + 1));
            }
            if (hPos > 1 && tileMap[vPos][hPos - 2] == 0 && tileMap[vPos - 1][hPos - 2] == 0 && tileMap[vPos + 1][hPos - 2] == 0
                    && tileMap[vPos][hPos - 1] == 0 && tileMap[vPos - 1][hPos - 1] == 0 && tileMap[vPos + 1][hPos - 1] == 0) {
                possibleNextPosition.add(new Vector2d(hPos - 1, vPos));
            }
            if (hPos < tileMap[0].length - 2 && tileMap[vPos][hPos + 2] == 0 && tileMap[vPos - 1][hPos + 2] == 0 && tileMap[vPos + 1][hPos + 2] == 0
                    && tileMap[vPos - 1][hPos] == 0 && tileMap[vPos - 1][hPos - 1] == 0 && tileMap[vPos - 1][hPos + 1] == 0) {
                possibleNextPosition.add(new Vector2d(hPos + 1, vPos));
            }
            if (!possibleNextPosition.isEmpty()) {
                Vector2d nextPosition = possibleNextPosition.get(random.nextInt(possibleNextPosition.size()));
                createCorridor(nextPosition);
                possibleNextPosition.remove(nextPosition);
            }
        }
        while (!possibleNextPosition.isEmpty());
    }

    private static int[][] createRooms(Dungeon map, List<Room> rooms) {


        int vSize = map.tileMap.length;
        int hSize = map.tileMap[0].length;
        tileMap = new int[vSize][hSize];
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

        List<Vector2d> possibleCorridorFields = new ArrayList<>();
        do {
            possibleCorridorFields.clear();
            for (int i = 1; i < vSize - 1; i++) {
                for (int j = 1; j < hSize - 1; j++) {
                    if (tileMap[i][j] == 0 && tileMap[i][j - 1] == 0 && tileMap[i][j + 1] == 0 && tileMap[i + 1][j] == 0 && tileMap[i - 1][j] == 0
                            && tileMap[i - 1][j - 1] == 0 && tileMap[i - 1][j + 1] == 0 && tileMap[i + 1][j - 1] == 0 && tileMap[i + 1][j + 1] == 0 ) {
                        possibleCorridorFields.add(new Vector2d(j, i));
                    }
                }
            }
            if (!possibleCorridorFields.isEmpty()) {
                Vector2d firstCorridorField = possibleCorridorFields.get(random.nextInt(possibleCorridorFields.size()));
                createCorridor(firstCorridorField);
            }
        } while (!possibleCorridorFields.isEmpty());

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
