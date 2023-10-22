package de.neuefische.backend.service;

import de.neuefische.backend.model.Avatar;
import de.neuefische.backend.model.NewCharacter;
import de.neuefische.backend.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Avatar save(NewCharacter newCharacter) {

        int strength;
        int intelligence;
        String weapon;
        String armor;

        if (newCharacter.job().equals("warrior")) {
            strength = 7;
            intelligence = 5;
            weapon = "Dagger";
            armor = "Cloth";
        } else {
            strength = 5;
            intelligence = 7;
            weapon = "Wand";
            armor = "Cape";
        }

        Avatar saveCharacter = new Avatar(
                newCharacter.id(),
                newCharacter.name(),
                "Nobody",
                newCharacter.job(),
                0,
                strength,
                intelligence,
                weapon,
                armor
        );



        return characterRepository.save(saveCharacter);
    }

    public Optional<Avatar> getAvatarById(String id) {
        return characterRepository.findById(id);
    }
}
