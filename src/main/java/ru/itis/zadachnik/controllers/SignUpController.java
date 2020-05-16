package ru.itis.zadachnik.controllers;

import ru.itis.zadachnik.forms.UserRegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.zadachnik.services.GroupService;
import ru.itis.zadachnik.services.UserService;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/signUp")
    public String getSignUpPage(ModelMap modelMap) {
        modelMap.addAttribute("groups", groupService.getAllGroups());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserRegisterForm userRegisterForm, BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
            modelMap.addAttribute("errors", errors);
            return "signUp";
        }
        userService.signUp(userRegisterForm);
        return "redirect:/login";
    }
}
