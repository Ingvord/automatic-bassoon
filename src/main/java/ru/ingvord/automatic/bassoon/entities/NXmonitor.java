package ru.ingvord.automatic.bassoon.entities;

public record NXmonitor(
        String mode,  // "monitor" or "timer"
        Float preset,
        Float integral
) {
}
