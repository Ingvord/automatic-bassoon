package ru.ingvord.automatic.bassoon.entities;

import java.time.LocalDateTime;

public record NXmonopdEntry(
        String title,
        LocalDateTime startTime,
        String definition,
        NXinstrument instrument,
        NXsample sample,
        NXmonitor monitor,
        NXdata data
) {
}
