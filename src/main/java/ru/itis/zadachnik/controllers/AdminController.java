package ru.itis.zadachnik.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.zadachnik.enums.Role;
import ru.itis.zadachnik.security.details.UserDetailsImpl;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String getProblemsPage(ModelMap modelMap, Authentication authentication) {

        return "admin";
    }
}
