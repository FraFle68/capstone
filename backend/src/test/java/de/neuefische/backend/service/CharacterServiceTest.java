package de.neuefische.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import de.neuefische.backend.model.Avatar;
import de.neuefische.backend.model.NewCharacter;
import de.neuefische.backend.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterService characterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCharacterForWarriorJob() {
        NewCharacter newCharacter = new NewCharacter("1", "Aragorn", "warrior");
        Avatar expectedAvatar = new Avatar("1", "Aragorn", "Nobody", "warrior", 0, 7, 5, "Dagger", "Cloth");

        when(characterRepository.save(any(Avatar.class))).thenReturn(expectedAvatar);

        Avatar savedAvatar = characterService.save(newCharacter);

        assertEquals(expectedAvatar, savedAvatar);
        verify(characterRepository, times(1)).save(any(Avatar.class));
    }

    @Test
    void testSaveCharacterForNonWarriorJob() {
        NewCharacter newCharacter = new NewCharacter("2", "Gandalf", "mage");
        Avatar expectedAvatar = new Avatar("2", "Gandalf", "Nobody", "mage", 0, 5, 7, "Wand", "Cape");

        when(characterRepository.save(any(Avatar.class))).thenReturn(expectedAvatar);

        Avatar savedAvatar = characterService.save(newCharacter);

        assertEquals(expectedAvatar, savedAvatar);
        verify(characterRepository, times(1)).save(any(Avatar.class));
    }

    @Test
    void testGetAvatarById() {
        String characterId = "1";
        Avatar expectedAvatar = new Avatar("1", "John", "Nobody", "warrior", 0, 7, 5, "Dagger", "Cloth");

        when(characterRepository.findById(characterId)).thenReturn(Optional.of(expectedAvatar));

        Optional<Avatar> retrievedAvatar = characterService.getAvatarById(characterId);

        assertEquals(expectedAvatar, retrievedAvatar.orElse(null));
        verify(characterRepository, times(1)).findById(characterId);
    }
}
