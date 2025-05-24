package ru.ingvord.automatic.bassoon.entities;

public record NXsource(
        String type,
        String name,
        String probe // neutron, x-ray, electron
) {
}
