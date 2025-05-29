package ru.ingvord.automatic.bassoon;

import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.service.AiServices;
import ru.ingvord.automatic.bassoon.ai.services.ElnExtractor;
import ru.ingvord.automatic.bassoon.entities.NXmonopdEntry;
import ru.ingvord.automatic.bassoon.hdf5.Hdf5Writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static dev.langchain4j.model.chat.request.ResponseFormat.JSON;

public class Application {
    public static void main(String[] args) throws IOException {


        // runs in CPU-mode of this small laptop (no GPU)
        OllamaChatModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("gemma3:1b")
//                .responseFormat(JSON)
                .temperature(0.8)
                .logRequests(true)
                .logResponses(true)
                .timeout(Duration.ofSeconds(60))
                .build();

        // using AI service to extract meta data and create native Java objects tree, see entities
        ElnExtractor elnExtractor = AiServices.create(ElnExtractor.class, model);

//        Alas ollama does not support tooling, but this can be used to "teach" AI to read from HDF5 for instance
//        ElnParser parser = AiServices.builder(ElnParser.class)
//                .chatLanguageModel(model)
//                .tools(new Hdf5StorageTools())
//                    .build();

        // pure Java hdf5 writer
        Hdf5Writer hdf5Writer = new Hdf5Writer();

        // do stuff
        String inputFileName = args[0];
        NXmonopdEntry extracted = elnExtractor.extract(Files.readString(Paths.get(inputFileName)));
        hdf5Writer.write(extracted, inputFileName);
    }
}
