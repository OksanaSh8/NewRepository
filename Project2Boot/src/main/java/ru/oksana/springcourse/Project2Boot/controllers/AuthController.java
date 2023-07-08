package ru.oksana.springcourse.Project2Boot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.oksana.springcourse.Project2Boot.models.Person;
import ru.oksana.springcourse.Project2Boot.services.PersonDetailsService;
import ru.oksana.springcourse.Project2Boot.services.RegistrationService;
import ru.oksana.springcourse.Project2Boot.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService, PersonDetailsService personDetailsService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/registration";
        registrationService.register(person);
        return "redirect:/auth/login";

    }
}
