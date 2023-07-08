package ru.oksana.springcourse.Project2Boot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksana.springcourse.Project2Boot.models.Person;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

   Optional<Person> findByFullName(String name);

   List<Person> findByRole(String role);
}
