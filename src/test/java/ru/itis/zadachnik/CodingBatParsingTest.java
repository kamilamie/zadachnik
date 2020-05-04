package ru.itis.zadachnik;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.itis.zadachnik.parsing_services.CodingBatParsingService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CodingBatParsingTest {

    @Autowired
    private CodingBatParsingService service;

    @Test
    public void parsingTest(){
        service.getProblems();
    }
}
