package ru.itis.zadachnik.models;

import ru.itis.zadachnik.enums.CommunicationLanguage;
import ru.itis.zadachnik.enums.ProgrammingLanguage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

public class Problem {

    private Long id;

    private Topic topic;

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage progrLanguage;

    @Enumerated(EnumType.STRING)
    private CommunicationLanguage commLanguage;

    private String text;

    private List<Solution> solutions;




}
