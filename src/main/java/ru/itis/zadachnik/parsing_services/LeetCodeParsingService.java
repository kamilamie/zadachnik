package ru.itis.zadachnik.parsing_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.enums.*;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.repositories.ProblemsRepository;

import java.util.Collections;

@Service
public class LeetCodeParsingService {

    private final CommunicationLanguage COMM_LANG = CommunicationLanguage.ENGLISH;
    private final Source SOURCE = Source.LEETCODE;
    private final ProgrammingLanguage PROG_LANG_ANY = ProgrammingLanguage.ANY;

    @Autowired
    private ProblemsRepository problemsRepository;

    public void saveProblem(String text, Topic t, Difficulty d){
        Problem problem = Problem.builder()
                .commLanguage(COMM_LANG)
                .progrLanguage(PROG_LANG_ANY)
                .source(SOURCE)
                .text(text)
                .topics(Collections.singletonList(t))
                .difficulty(d)
                .build();
        problemsRepository.save(problem);
    }
}
