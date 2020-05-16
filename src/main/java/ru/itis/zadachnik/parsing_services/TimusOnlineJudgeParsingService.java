package ru.itis.zadachnik.parsing_services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.enums.*;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.parsing_services.tools.TextAnalyzer;
import ru.itis.zadachnik.repositories.ProblemsRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimusOnlineJudgeParsingService {
    private final CommunicationLanguage COMM_LANG = CommunicationLanguage.RUSSIAN;
    private final ProgrammingLanguage PROG_LANG = ProgrammingLanguage.ANY;
    private final Source SOURCE = Source.TIMUS;

    private final String BASE_URL = "https://acm.timus.ru/problemset.aspx?space=1&page=all&locale=ru";

    @Autowired
    private ProblemsRepository problemsRepository;

    public void getProblems() {
        try {
            getProblemsList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getProblemsList() throws IOException {
        Document problemsPage = Jsoup.connect(BASE_URL).get();
        List<Element> problemsLinks = problemsPage.select("a[href^='problem.']");

        for (Element e: problemsLinks){
            Problem problem = fillProblem(e);
            problemsRepository.save(problem);
        }
    }

    private Problem fillProblem(Element problemLink) throws IOException {
        Document problemPage = Jsoup.connect(problemLink.attr("abs:href")+"&locale=ru").get();
        List<Element> textParts = problemPage.select("div[class=problem_par_normal], h3[class=problem_subtitle]");
        StringBuilder problemText = new StringBuilder();
        for (Element e : textParts){
            if (e.html().equals("Пример")) break;
            problemText.append(e.html());
            if(e.className().equals("problem_subtitle")) problemText.append(":");
            problemText.append("<br>");
        }
        Element difficulty = problemPage.select("div[class=problem_links] > span").get(0);

        return Problem.builder()
                .commLanguage(TextAnalyzer.detectLang(problemText.toString()))
                .source(SOURCE)
                .progrLanguage(PROG_LANG)
                .difficulty(getDifficulty(difficulty.toString()))
                .text(problemText.toString())
                .topics(fillTopics(problemText.toString()))
                .build();
    }

    private Difficulty getDifficulty(String difficulty){
        int diff_level = Integer.parseInt(difficulty.split(" ")[1].split("<")[0]);
        if (diff_level < 300)
            return Difficulty.EASY;
        else if (diff_level < 1700)
            return Difficulty.MEDIUM;
        else
            return Difficulty.HARD;
    }

    private List<Topic> fillTopics(String text){
        List<Topic> topics = TextAnalyzer.detectTopics(text);
        topics.add(Topic.OLYMPIAD);
        return topics;
    }
}
