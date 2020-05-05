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
        List<Element> categories = categoriesPage.select("a[href^='/" + lang + "/']");


        for (Element c : categories) {
            String category = c.attr("href");

            Document problems_page = Jsoup.connect(c.attr("abs:href")).get();
            List<Element> problems = problems_page.select("a[href^='/prob/']");

            for (Element p : problems) {
                Problem problem = fillProblem(p);
                problem.setProgrLanguage(lang);
                problem.setTopics(fillTopics(category, problem.getText()));
                problem.setDifficulty(fillDifficulty(category));
                problemsRepository.save(problem);
            }
        }
    }

    private Problem fillProblem(Element problem) throws IOException {
        Document problem_page = Jsoup.connect(problem.attr("abs:href")).get();
        StringBuilder problemText = new StringBuilder(problem_page.select("p[class=\"max2\"]").get(0).text());
        List<TextNode> textNodes = problem_page.select("div > div > table > tbody > tr > td:nth-child(1)").get(0).textNodes();
        for (TextNode textNode : textNodes) {
            problemText.append("<br>").append(textNode.text());
        }
        return Problem.builder()
                .commLanguage(COMM_LANG)
                .source(SOURCE)
                .text(problemText.toString())
                .build();
    }

    private List<ProblemTopic> fillTopics(String category, String text){
        List<ProblemTopic> topics = TextAnalyzer.analyze(category);
        topics.addAll(TextAnalyzer.analyze(text));
        if (topics.isEmpty()){
            topics.add(ProblemTopic.BASIC);
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
