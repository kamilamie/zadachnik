package ru.itis.zadachnik.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProblemsController {

    @GetMapping("/header")
    public String getCalculatorPage() {
        return "header";
    }

}
