package ru.oksana.springcourse.Project2Boot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.oksana.springcourse.Project2Boot.models.Person;
import ru.oksana.springcourse.Project2Boot.services.PeopleService;
import ru.oksana.springcourse.Project2Boot.services.PersonDetailsService;
import ru.oksana.springcourse.Project2Boot.services.RegistrationService;
import ru.oksana.springcourse.Project2Boot.util.PersonValidator;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator, RegistrationService registrationService, PersonDetailsService personDetailsService) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findByRole("ROLE_USER"));
        model.addAttribute("role", personDetailsService.findUserRole());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));

        return "people/show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";
        registrationService.register(person);
        peopleService.save(person);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);
        /*   peopleService.save(convertToPerson(id, personDTO));*/
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

}