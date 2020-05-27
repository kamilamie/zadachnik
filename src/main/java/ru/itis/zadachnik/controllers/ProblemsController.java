package ru.itis.zadachnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.zadachnik.enums.Topic;
import ru.itis.zadachnik.forms.NewAssignmentForm;
import ru.itis.zadachnik.forms.NewProblemForm;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;
import ru.itis.zadachnik.security.details.UserDetailsImpl;
import ru.itis.zadachnik.services.AssignmentService;
import ru.itis.zadachnik.services.GroupService;
import ru.itis.zadachnik.services.ProblemService;
import ru.itis.zadachnik.services.UserService;

import javax.validation.Valid;
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
        modelMap.addAttribute("topics", Topic.values());
        return "problems";
    }

    @GetMapping("/problems/{problem-id}")
    public String getProblemPage(@PathVariable("problem-id") String id, ModelMap modelMap, Authentication authentication) {
        Optional<User> curr_user = userService.getCurrentUser(authentication);
        curr_user.ifPresent(user -> modelMap.addAttribute("role", user.getRole().toString()));
        Problem problem = problemService.getProblemById(Long.parseLong(id));
        modelMap.addAttribute("problem", problem);
        modelMap.addAttribute("topics", Topic.values());
        return "problem";
    }

    @PostMapping("/addProblem")
    public String addNewProblem(@Valid NewProblemForm newProblemForm, Authentication authentication) {
        Long id = problemService.addNewProblem(newProblemForm);
        return "redirect:/problems/" + id;
    }

    @PostMapping("/problems/{problem-id}/edit")
    public String editProblem(@Valid NewProblemForm newProblemForm, @PathVariable("problem-id") String id, Authentication authentication) {
        problemService.editProblem(newProblemForm, id);
        return "redirect:/problems/" + id;
    }

}
