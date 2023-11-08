package de.neuefische.backend.game.dungeon;

import de.neuefische.backend.game.core.Room;
import de.neuefische.backend.game.core.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonCreator {

    private DungeonCreator() {}
    @SuppressWarnings("java:S2245")
    static Random random = new Random();
    static int[][] tileMap;
    static int vSize;
    static int hSize;

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

    private static void addRooms(List<Room> rooms) {
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
    }

    private static void addCorridors() {
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
    }

    private static void addDorways() {
        List<Integer> positionGroups = new ArrayList<>();
        for (int i = 1; i < tileMap.length - 1; i++) {
            for (int j = 1; j < tileMap[0].length - 1; j++) {
                if (tileMap[i - 1][j] != 0 && tileMap[i + 1][j] != 0 && tileMap[i][j - 1] == 0 && tileMap[i][j + 1] == 0) {
                    positionGroups.add(j);
                } else {
                    if (!positionGroups.isEmpty()) {
                        tileMap[i][positionGroups.get(random.nextInt(positionGroups.size()))] = 3;
                        positionGroups.clear();
                    }
                }
            }
        }
        for (int i = 1; i < tileMap[0].length - 1; i++) {
            for (int j = 1; j < tileMap.length - 1; j++) {
                if (tileMap[j][i - 1] != 0 && tileMap[j][i + 1] != 0 && tileMap[j - 1][i] == 0 && tileMap[j + 1][i] == 0) {
                    positionGroups.add(j);
                } else {
                    if (!positionGroups.isEmpty()) {
                        tileMap[positionGroups.get(random.nextInt(positionGroups.size()))][i] = 4;
                        positionGroups.clear();
                    }
                }
            }
        }
    }

    private static int[][] createRooms(Dungeon map, List<Room> rooms) {

        vSize = map.tileMap.length;
        hSize = map.tileMap[0].length;
        tileMap = new int[vSize][hSize];

        addRooms(rooms);
        addCorridors();
        addDorways();

        return tileMap;
    }

    private static int[][] createContent(Dungeon map) {
        int[][] contentMap = new int[map.contentMap.length][map.contentMap[0].length];
        int x;
        int y;
        boolean check = false;
        for (int i = 0; i < 10; i++) {
            do {
                y = random.nextInt(contentMap.length);
                x = random.nextInt(contentMap[0].length);
                if (map.tileMap[y][x] == 1) {
                    check = true;
                }
            } while (!check);
            contentMap[y][x] = 1;
            check = false;
        }
        do {
            y = random.nextInt(contentMap.length);
            x = random.nextInt(contentMap[0].length);
            if (map.tileMap[y][x] == 1 && map.contentMap[y][x] == 0) {
                check = true;
            }
        } while (!check);
        contentMap[y][x] = 42;
        return contentMap;
    }

    private static Vector2d createStartPosition(Dungeon map) {

        Vector2d position = new Vector2d(25, 25);

        return position;
    }

    public static Dungeon createDungeon(int hSize, int vSize, int floor, List<Room> rooms) {

        Dungeon map = new Dungeon(vSize, hSize, floor);
        map.tileMap = createRooms(map, rooms);
        map.contentMap = createContent(map);
        Vector2d startPosition = createStartPosition(map);
        map.contentMap[startPosition.getY()][startPosition.getX()] = 99;
        map.position = startPosition;
        return map;
    }
}
