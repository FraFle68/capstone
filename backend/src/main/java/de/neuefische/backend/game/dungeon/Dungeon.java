package de.neuefische.backend.game.dungeon;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dungeon {
    int floor;

    int[][] tileMap;
    int[][] contentMap;


    public Dungeon(int vSize, int hSize, int floor) {
        this.floor = floor;
        tileMap = new int[vSize][hSize];
        contentMap = new int[vSize][hSize];

        for (int i = 0; i < vSize; i++) {
            for (int j = 0; j < hSize; j++) {
                tileMap[i][j] = 0;
                contentMap[i][j] = 0;
            }
        }
    }


}
