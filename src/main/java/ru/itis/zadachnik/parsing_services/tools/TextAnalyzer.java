package ru.itis.zadachnik.parsing_services.tools;

import ru.itis.zadachnik.enums.Topic;

import java.util.ArrayList;
import java.util.List;

public class TextAnalyzer {
    public static List<Topic> analyze(String problemText) {
        List<Topic> topics = new ArrayList<>();
        for (Topic topic : Topic.values()) {
            if (problemText.toLowerCase().contains(topic.toString().toLowerCase()))
                topics.add(topic);
        }
        return topics;
    }
}
