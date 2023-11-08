package de.neuefische.backend.game.dungeon;


import de.neuefische.backend.game.core.Vector2d;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dungeon {
    int floor;

    int[][] tileMap;
    int[][] contentMap;
    Vector2d position;

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
