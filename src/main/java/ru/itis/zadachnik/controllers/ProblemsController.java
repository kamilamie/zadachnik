package ru.itis.zadachnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zadachnik.services.ProblemService;

@Controller
public class ProblemsController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/problems")
    public String getCalculatorPage(ModelMap modelMap) {
        modelMap.addAttribute("problems", problemService.getAllProblems());

        return "problems";
    }

}
