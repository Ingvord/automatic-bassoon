package ru.ingvord.automatic.bassoon.hdf5;

import io.jhdf.HdfFile;
import io.jhdf.WritableHdfFile;
import ru.ingvord.automatic.bassoon.entities.NXmonopdEntry;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Hdf5Writer {

    public void write(NXmonopdEntry entry, String inputFile) throws IOException {
        System.out.println("Writing entry: " + entry);
        // create a new file
        try(WritableHdfFile target = HdfFile.write(Paths.get(inputFile.replace(".txt", ".h5")))) {
            // writing scalar data
            target.putDataset("title", Optional.ofNullable(entry.title()).orElse("NA"));
            target.putDataset("startTime", Optional.ofNullable(entry.startTime()).orElse(LocalDateTime.now()).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            target.putDataset("definition", Optional.ofNullable(entry.definition()).orElse("NA"));
            // == Instrument ==
            target.putDataset("instrument_source_name", Optional.ofNullable(entry.instrument().source().name()).orElse("NA"));
            target.putDataset("instrument_source_type", Optional.ofNullable(entry.instrument().source().type()).orElse("NA"));
            target.putDataset("instrument_source_probe", Optional.ofNullable(entry.instrument().source().probe()).orElse("NA"));
            // writing arrays
            var wavelength = entry.instrument().crystal().wavelength();
            if(Objects.nonNull(wavelength) && !entry.instrument().crystal().wavelength().isEmpty())
                target.putDataset("instrument_crystal_wavelength",
                    entry.instrument().crystal().wavelength()
                            .stream()
                            .filter(Objects::nonNull)
                            .mapToDouble(Float::floatValue)
                            .toArray());
            var polarAngle = entry.instrument().detector().polarAngle();
            if(Objects.nonNull(polarAngle) && !polarAngle.isEmpty())
                target.putDataset("instrument_detector_polarAngle",
                    polarAngle
                            .stream()
                            .filter(Objects::nonNull)
                            .mapToDouble(Float::floatValue)
                            .toArray());
            var data = entry.instrument().detector().data();
            if(Objects.nonNull(data) && !data.isEmpty())
                target.putDataset("instrument_detector_data",
                    data
                            .stream()
                            .filter(Objects::nonNull)
                            .mapToDouble(Integer::intValue)
                            .toArray());
            // == Sample ==
            target.putDataset("sample", Optional.ofNullable(entry.sample().name()).orElse("NA"));
            target.putDataset("rotationAngle", Optional.ofNullable(entry.sample().rotationAngle()).orElse(0.0F));
        }
        System.out.println("Done.");
    }
}
