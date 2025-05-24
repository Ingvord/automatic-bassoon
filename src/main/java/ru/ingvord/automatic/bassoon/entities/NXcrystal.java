package ru.ingvord.automatic.bassoon.entities;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import java.util.List;

public record NXcrystal(
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        List<Float> wavelength
) {
}
