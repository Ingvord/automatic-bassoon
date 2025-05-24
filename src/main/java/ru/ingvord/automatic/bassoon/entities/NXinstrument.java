package ru.ingvord.automatic.bassoon.entities;

public record NXinstrument(
        NXsource source,
        NXcrystal crystal,
        NXdetector detector
) {
}
