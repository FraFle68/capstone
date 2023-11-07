package de.neuefische.backend.service;

import de.neuefische.backend.game.core.Vector2d;
import de.neuefische.backend.game.dungeon.Dungeon;
import de.neuefische.backend.game.dungeon.DungeonCreator;
import de.neuefische.backend.model.GameMap;
import de.neuefische.backend.repository.GameRepository;
import de.neuefische.backend.repository.RoomRepository;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final RoomRepository roomRepository;

    public GameService(GameRepository gameRepository, RoomRepository roomRepository) {
        this.gameRepository = gameRepository;
        this.roomRepository = roomRepository;
    }

    public GameMap getMap(String id) {
        Optional<GameMap> savedMap = gameRepository.findById(id);
        GameMap gameMap = savedMap.orElseGet(() ->
                new GameMap(id, DungeonCreator.createDungeon(50, 50, 0, roomRepository.findAll()), new Vector2d(0, 0)));
        for(int i = 0; i < gameMap.dungeon().getTileMap().length; i++) {
            for (int j = 0; j < gameMap.dungeon().getTileMap()[0].length; j++) {
                if (gameMap.dungeon().getContentMap()[j][i] == 99) {
                    gameMap.position().setX(j);
                    gameMap.position().setY(i);
                }
            }
        }
        gameRepository.save(gameMap);
        return gameMap;
    }

    public void deleteMap(String id) {
        gameRepository.deleteById(id);
    }

    public GameMap changePosition(String id, Vector2d newPosition) {
        GameMap newMap;
        Dungeon newDungeon;
        int[][] contentMap;
        Optional<GameMap> actualMap = gameRepository.findById(id);
        if (actualMap.isPresent()) {
            newDungeon = actualMap.get().dungeon();
            contentMap = actualMap.get().dungeon().getContentMap();
            contentMap[actualMap.get().position().getX()][actualMap.get().position().getY()] = 0;
            contentMap[newPosition.getY()][newPosition.getX()] = 99;
            newDungeon.setPosition(newPosition);
            newDungeon.setContentMap(contentMap);
            newMap = new GameMap(actualMap.get().id(), newDungeon, newPosition);

            return gameRepository.save(newMap);
        }
        return new GameMap("1", new Dungeon(), new Vector2d(0, 0));

    }
}
