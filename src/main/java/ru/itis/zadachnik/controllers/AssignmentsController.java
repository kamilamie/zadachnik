package ru.itis.zadachnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zadachnik.security.details.UserDetailsImpl;
import ru.itis.zadachnik.services.AssignmentService;

@Controller
public class AssignmentsController {


    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/assignments")
    public String getAssignmentsPage(ModelMap modelMap, Authentication authentication){
        Long currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();
        modelMap.addAttribute("assignments", assignmentService.getAllAssignmentsByUserId(currentUserId));
        return "assignments";
    }
}
