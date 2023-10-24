package de.neuefische.backend.service;

import de.neuefische.backend.game.core.Room;
import de.neuefische.backend.game.core.Vector2d;
import de.neuefische.backend.game.dungeon.DungeonCreator;
import de.neuefische.backend.model.GameMap;
import de.neuefische.backend.repository.GameRepository;
import de.neuefische.backend.repository.RoomRepository;
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
        gameRepository.save(gameMap);
        return gameMap;
    }

}
