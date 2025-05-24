package ru.ingvord.automatic.bassoon.ai.services;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import ru.ingvord.automatic.bassoon.entities.NXmonopdEntry;

public interface ElnExtractor {
    @SystemMessage("Extract experiment info and return in JSON for this Java type: NXmonopdEntry")
    @UserMessage("Extract from: {{it}}")
    NXmonopdEntry extract(String elnRecord);  // <-- LangChain4J auto-converts!
}
