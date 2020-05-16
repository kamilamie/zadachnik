package ru.itis.zadachnik.parsing_services.tools;

import ru.itis.zadachnik.enums.CommunicationLanguage;
import ru.itis.zadachnik.enums.Topic;

import java.util.ArrayList;
import java.util.List;

public class TextAnalyzer {
    public static List<Topic> detectTopics(String problemText) {
        List<Topic> topics = new ArrayList<>();
        for (Topic topic : Topic.values()) {
            if (problemText.toLowerCase().contains(topic.toString().toLowerCase()))
                topics.add(topic);
        }
        return topics;
    }

    public static CommunicationLanguage detectLang(String problemText){
        int c = 1;
        for(int i = 0; i < problemText.length(); i++) {
            if(Character.UnicodeBlock.of(problemText.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                c++;
            }
            if (c > problemText.length()/2)
                return CommunicationLanguage.RUSSIAN;
        }
        return CommunicationLanguage.ENGLISH;
    }
}
