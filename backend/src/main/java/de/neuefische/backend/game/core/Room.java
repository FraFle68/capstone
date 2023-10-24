package de.neuefische.backend.game.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    int id;
    int hSize;
    int vSize;
}
