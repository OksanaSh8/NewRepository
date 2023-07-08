package ru.oksana.springcourse.Project2Boot.services;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksana.springcourse.Project2Boot.models.Book;
import ru.oksana.springcourse.Project2Boot.models.Person;
import ru.oksana.springcourse.Project2Boot.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public List<Person> findByRole(String role) {

        if (peopleRepository.findByRole(role).isEmpty())
            return Collections.emptyList();
        else
            return peopleRepository.findByRole(role);
    }

    public Optional<Person> findByFullName(String name) {
        return peopleRepository.findByFullName(name);
    }

    public Person findOne(int id) {
        Optional<Person> findPerson = peopleRepository.findById(id);
        return findPerson.orElse(null);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            person.get().getBooks().forEach(book -> {
                long diffInDays = (new Date().getTime() - book.getCreatedAt().getTime()) / 1000 / 60 / 60 / 24;
                if (diffInDays > 10)
                    book.setNumberDates(true);
            });
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }


    @Transactional
    public void update(int id, Person updatedPerson) {
        Person person=findOne(id);

        updatedPerson.setPassword(person.getPassword());
        updatedPerson.setRole(person.getRole());

        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
