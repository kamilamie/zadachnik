package ru.itis.zadachnik;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.itis.zadachnik.enums.*;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.parsing_services.CodingBatParsingService;
import ru.itis.zadachnik.parsing_services.LeetCodeParsingService;
import ru.itis.zadachnik.repositories.ProblemsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LeetCodeParsingTest {
    protected WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Autowired
    private LeetCodeParsingService leetCodeParsingService;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/kamila/Documents/study/laba/auto/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "https://leetcode.com";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Test
    public void launchLeetCodeParsing() {
        getProblemsByTopic("array");
//        getProblemsByTopic("sort");
//        getProblemsByTopic("recursion");
//        getProblemsByTopic("graph");
    }

    public void getProblemsByTopic(String topic) {
        driver.get("https://leetcode.com/tag/" + topic);
        List<WebElement> problemsList = driver.findElements(By.cssSelector("a[href^='/problems/']"));
        List<String> problemLinks = new ArrayList<>();
        for (WebElement w : problemsList) {
            problemLinks.add(w.getAttribute("href"));
        }
        fillProblems(problemLinks, topic);
    }

    public void fillProblems(List<String> paths, String topic) {
        for (String s : paths) {
            driver.get(s);
            WebElement pTextElement = driver.findElement(By.cssSelector("div.content__u3I1"));
            WebElement pDifficulty = driver.findElement(By.cssSelector("div[diff]"));
            leetCodeParsingService.saveProblem(pTextElement.getAttribute("innerHTML"), Topic.valueOf(topic.toUpperCase()),
                    Difficulty.valueOf(pDifficulty.getText().toUpperCase()));
        }
    }
}
