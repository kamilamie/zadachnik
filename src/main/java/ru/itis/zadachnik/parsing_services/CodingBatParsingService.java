package ru.itis.zadachnik.parsing_services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.zadachnik.enums.CommunicationLanguage;
import ru.itis.zadachnik.enums.ProgrammingLanguage;
import ru.itis.zadachnik.enums.Source;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.repositories.ProblemsRepository;

import java.io.IOException;
import java.util.List;

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
            Document problems_page = Jsoup.connect(c.attr("abs:href")).get();
            List<Element> problems = problems_page.select("a[href^='/prob/']");

            for (Element p : problems) {
                Problem problem = fillProblem(p);
                problem.setProgrLanguage(lang);
                problemsRepository.save(problem);
                System.out.println(problemsRepository.findAll().get(0).getText());
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
        //TODO: check problemText for TOPICS and fill them
        return Problem.builder()
                .commLanguage(COMM_LANG)
                .source(SOURCE)
                .text(problemText.toString())
                .build();
    }

}
