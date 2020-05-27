package ru.itis.zadachnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.zadachnik.forms.NewAssignmentForm;
import ru.itis.zadachnik.forms.UserRegisterForm;
import ru.itis.zadachnik.models.Assignment;
import ru.itis.zadachnik.models.Problem;
import ru.itis.zadachnik.models.User;
import ru.itis.zadachnik.security.details.UserDetailsImpl;
import ru.itis.zadachnik.services.AssignmentService;
import ru.itis.zadachnik.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AssignmentsController {


    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/assignments")
    public String getAssignmentsPage(ModelMap modelMap, Authentication authentication){
        Optional<User> curr_user = userService.getCurrentUser(authentication);
        if(curr_user.isPresent()){
            List<Assignment> assignments = assignmentService.getAllAssignmentsByUser(curr_user.get());
            modelMap.addAttribute("assignments", assignmentService.getAllAssignmentsByUser(curr_user.get()));
            modelMap.addAttribute("role", curr_user.get().getRole().toString());
        }
        return "assignments";
    }

    @GetMapping("/assignments/{assignment-id}")
    public String getProblemPage(@PathVariable("assignment-id") String id, ModelMap modelMap, Authentication authentication) {
        Assignment assignment = assignmentService.getAssignmentById(Long.parseLong(id));
        modelMap.addAttribute("assignment", assignment);
        User curr_user = userService.getCurrentUser(authentication).get();
        modelMap.addAttribute("role", curr_user.getRole().toString());
        return "assignment";
    }

    @PostMapping("/addAssignment")
    public String addNewAssignment(@Valid NewAssignmentForm newAssignmentForm, Authentication authentication){
        User teacher = userService.getCurrentUser(authentication).get();
        assignmentService.addNewAssignment(newAssignmentForm, teacher);
        return "redirect:/assignments";
    }

    @PostMapping("/assignments/{assignment-id}/addSolution")
    public String addSolution(@RequestParam String solution, @PathVariable("assignment-id") String id, Authentication authentication){
        assignmentService.addSolution(Long.parseLong(id), solution);
        return "redirect:/assignments";
    }


}
