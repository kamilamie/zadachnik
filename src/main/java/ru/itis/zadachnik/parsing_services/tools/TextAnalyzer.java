package ru.itis.zadachnik.parsing_services.tools;

import ru.itis.zadachnik.enums.ProblemTopic;

import java.util.ArrayList;
import java.util.List;

public class TextAnalyzer {
    public static List<ProblemTopic> analyze(String problemText) {
        List<ProblemTopic> problemTopics = new ArrayList<>();
        for (ProblemTopic topic : ProblemTopic.values()) {
            if (problemText.toLowerCase().contains(topic.toString().toLowerCase()))
                problemTopics.add(topic);

        }
        return problemTopics;
    }
}
