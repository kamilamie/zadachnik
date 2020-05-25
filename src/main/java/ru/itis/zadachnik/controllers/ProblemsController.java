package ru.itis.zadachnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.security.details.UserDetailsImpl;
import ru.itis.zadachnik.services.AssignmentService;
import ru.itis.zadachnik.services.ProblemService;
import ru.itis.zadachnik.services.UserService;

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

    /*@PostMapping("/problems/{problem-id}")
    public String submitSolution(@RequestParam String solution, @PathVariable("problem-id") String id, ModelMap modelMap, Authentication authentication) {
        Problem problem = problemService.getProblemById(Long.parseLong(id));
        modelMap.addAttribute("problem", problem);
        Optional<User> current_user = userService.getCurrentUser(authentication);
        if (current_user.isPresent()) {
            problemService.addSolution(solution, problem, current_user.get());
            return "problem";
        } else return "login";
    }*/

}
