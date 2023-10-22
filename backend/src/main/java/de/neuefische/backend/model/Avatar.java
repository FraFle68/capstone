package de.neuefische.backend.model;

public record Avatar(
        String id,
        String name,
        String title,
        String job,
        int experience,
        int strength,
        int intelligence,
        String weapon,
        String armor
) {}
