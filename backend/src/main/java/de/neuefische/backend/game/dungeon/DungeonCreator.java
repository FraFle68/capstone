package de.neuefische.backend.game.dungeon;

public class DungeonCreator {

    private static int[][] createRooms(Dungeon map) {
        int[][] tileMap = new int[map.tileMap.length][map.tileMap[0].length];
        tileMap[0][0] = 5;
        return tileMap;
    }

    private static int[][] createContent(Dungeon map) {
        int[][] contentMap = new int[map.contentMap.length][map.contentMap[0].length];
        contentMap[0][0] = 7;
        return contentMap;
    }

    public static Dungeon createDungeon(int hSize, int vSize, int floor) {

        Dungeon map = new Dungeon(vSize, hSize, floor);
        map.floor = floor;
        map.tileMap = createRooms(map);
        map.contentMap = createContent(map);
        return map;
    }


}
