package de.neuefische.backend.controller;

import de.neuefische.backend.model.Avatar;
import de.neuefische.backend.model.NewCharacter;
import de.neuefische.backend.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("{id}")
    Optional<Avatar> getAvatarById(@PathVariable String id) {
        return characterService.getAvatarById(id);
    }

    @PostMapping("/new")
    public Avatar saveCharacter(@RequestBody NewCharacter newCharacter) {
        return characterService.save(newCharacter);
    }

    @PutMapping("/edit")
    public Avatar editCharacter(@RequestBody NewCharacter newCharacter) {
        return characterService.save(newCharacter);
    }

}
