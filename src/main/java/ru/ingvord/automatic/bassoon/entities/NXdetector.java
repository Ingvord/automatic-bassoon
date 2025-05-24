package ru.ingvord.automatic.bassoon.entities;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.List;

public record NXdetector(
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        List<Float> polarAngle,
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        List<Integer> data
) {
}
