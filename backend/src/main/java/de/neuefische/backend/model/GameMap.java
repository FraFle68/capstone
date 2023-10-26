package de.neuefische.backend.model;

import de.neuefische.backend.game.core.Vector2d;
import de.neuefische.backend.game.dungeon.Dungeon;

public record GameMap (
        String id,
        Dungeon dungeon,
        Vector2d position
) {

}
