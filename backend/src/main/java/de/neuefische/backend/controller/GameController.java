package de.neuefische.backend.controller;

import de.neuefische.backend.game.core.Vector2d;
import de.neuefische.backend.model.GameMap;
import de.neuefische.backend.service.GameService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{id}")
    public GameMap gameMap(@PathVariable String id) {
        return gameService.getMap(id);
    }

    @DeleteMapping("{id}")
    public void deleteMap(@PathVariable String id) {
        gameService.deleteMap(id);
    }

    @PutMapping("changeposition/{id}")
    public GameMap changePosition(@PathVariable String id, @RequestBody Vector2d position) {
        return gameService.changePosition(id, position);
    }
}
