package ru.oksana.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.oksana.springcourse.Project2Boot.models.Person;
import ru.oksana.springcourse.Project2Boot.repositories.PeopleRepository;
import ru.oksana.springcourse.Project2Boot.security.PersonDetails;

import java.util.Optional;


@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByFullName(s);
        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        return new PersonDetails(person.get());
    }

    public boolean findUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();
        boolean roleAdmin;
        if (role.equals("ROLE_ADMIN")) {
           roleAdmin = true;
        }
        else
        {roleAdmin=false;}
        return roleAdmin;
    }
}
