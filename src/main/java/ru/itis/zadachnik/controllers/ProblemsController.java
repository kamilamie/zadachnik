package ru.itis.zadachnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;
import ru.itis.zadachnik.security.details.UserDetailsImpl;
import ru.itis.zadachnik.services.AssignmentService;
import ru.itis.zadachnik.services.GroupService;
import ru.itis.zadachnik.services.ProblemService;
import ru.itis.zadachnik.services.UserService;

import java.util.Optional;

@Controller
public class ProblemsController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/problems")
    public String getProblemsPage(ModelMap modelMap, Authentication authentication) {
        Optional<User> curr_user = userService.getCurrentUser(authentication);
        curr_user.ifPresent(user -> modelMap.addAttribute("role", user.getRole().toString()));
        modelMap.addAttribute("problems", problemService.getAllProblems());
        modelMap.addAttribute("groups", groupService.getAllGroups());
        modelMap.addAttribute("students", userService.getAllStudents());
        return "problems";
    }

    @GetMapping("/problems/{problem-id}")
    public String getProblemPage(@PathVariable("problem-id") String id, ModelMap modelMap) {
        Problem problem = problemService.getProblemById(Long.parseLong(id));
        modelMap.addAttribute("problem", problem);
        return "problem";
    }

}
