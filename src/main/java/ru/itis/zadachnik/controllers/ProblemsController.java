package ru.itis.zadachnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.services.ProblemService;

@Controller
public class ProblemsController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/problems")
    public String getProblemsPage(ModelMap modelMap) {
        modelMap.addAttribute("problems", problemService.getAllProblems());

        return "problems";
    }

    @GetMapping("/problems/{problem-id}")
    public String getProblemPage(@PathVariable("problem-id") String id, ModelMap modelMap) {
        Problem problem = problemService.getProblemById(Long.parseLong(id));
        modelMap.addAttribute("problem", problem);
        return "problem";
    }

}
