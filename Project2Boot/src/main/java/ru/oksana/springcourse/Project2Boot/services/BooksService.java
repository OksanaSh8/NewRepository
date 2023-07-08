package ru.oksana.springcourse.Project2Boot.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksana.springcourse.Project2Boot.models.Book;
import ru.oksana.springcourse.Project2Boot.models.Person;
import ru.oksana.springcourse.Project2Boot.repositories.BooksRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAllOrderByYear(){
        return (booksRepository.findAll(Sort.by("year")));
    }

    public Book findOne(int id) {
        Optional<Book> findBook = booksRepository.findById(id);
        return findBook.orElse(null);
    }

    // Returns null if book has no owner
    public Person getBookOwner(int id) {
        // Здесь Hibernate.initialize() не нужен, так как владелец (сторона One) загружается не лениво
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    public List<Book> searchByTitle(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        // добавляем по сути новую книгу (которая не находится в Persistence context), поэтому нужен save()
        updatedBook.setId(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner()); // чтобы не терялась связь при обновлении
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void release(int id){
        Book book = findOne(id);
        book.setOwner(null);
        book.setCreatedAt(null);
    }

    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                    book.setCreatedAt(new Date()); // текущее время
                }
        );
    }
}
