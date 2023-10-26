package de.neuefische.backend.controller;

import de.neuefische.backend.model.GameMap;
import de.neuefische.backend.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}
