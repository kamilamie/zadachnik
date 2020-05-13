package ru.itis.zadachnik.parsing_services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
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
public class CodingBatParsingService {
    private final CommunicationLanguage COMM_LANG = CommunicationLanguage.ENGLISH;
    private final Source SOURCE = Source.CODINGBAT;

    private final ProgrammingLanguage PROG_LANG_JAVA = ProgrammingLanguage.JAVA;
    private final ProgrammingLanguage PROG_LANG_PYTHON = ProgrammingLanguage.PYTHON;
    private final String BASE_URL = "https://codingbat.com/";

    @Autowired
    private ProblemsRepository problemsRepository;

    public void getProblems(){
        try {
            getProblemsListByLanguage(PROG_LANG_JAVA);
            getProblemsListByLanguage(PROG_LANG_PYTHON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getProblemsListByLanguage(ProgrammingLanguage lang) throws IOException {
        Document categoriesPage = Jsoup.connect(BASE_URL + lang).get();
        List<Element> categoriesLinks = categoriesPage.select("a[href^='/" + lang + "/']");

        for (Element c : categoriesLinks) {
            String category = c.attr("href");
            Document problemsPage = Jsoup.connect(c.attr("abs:href")).get();
            List<Element> problemsLinks = problemsPage.select("a[href^='/prob/']");

            for (Element p : problemsLinks) {
                Problem problem = fillProblem(p);
                problem.setProgrLanguage(lang);
                problem.setTopics(fillTopics(category, problem.getText()));
                problem.setDifficulty(fillDifficulty(category));
                problemsRepository.save(problem);
            }
        }
    }

    private Problem fillProblem(Element problemLink) throws IOException {
        Document problemPage = Jsoup.connect(problemLink.attr("abs:href")).get();
        StringBuilder problemText = new StringBuilder(problemPage.select("p[class=\"max2\"]").get(0).text());
        List<TextNode> textNodes = problemPage.select("div > div > table > tbody > tr > td:nth-child(1)").get(0).textNodes();
        for (TextNode textNode : textNodes) {
            problemText.append("<br>").append(textNode.text());
        }
        return Problem.builder()
                .commLanguage(COMM_LANG)
                .source(SOURCE)
                .text(problemText.toString())
                .build();
    }

    private List<Topic> fillTopics(String category, String text){
        List<Topic> topics = TextAnalyzer.analyze(category);
        topics.addAll(TextAnalyzer.analyze(text));
        if (topics.isEmpty()){
            topics.add(Topic.BASIC);
        }
        return topics.stream().distinct().collect(Collectors.toList());
    }

    private Difficulty fillDifficulty(String category){
        if (category.contains("1")){
            return Difficulty.EASY;
        } else if (category.contains("2")){
            return Difficulty.MEDIUM;
        } else if (category.contains("3")){
            return Difficulty.HARD;
        }
        return Difficulty.EASY;
    }


}
