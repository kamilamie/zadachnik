package ru.itis.zadachnik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.zadachnik.parsing_services.CodingBatParsingService;

@SpringBootApplication
public class ZadachnikApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZadachnikApplication.class, args);
    }

}
