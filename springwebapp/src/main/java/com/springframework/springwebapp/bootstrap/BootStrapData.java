package com.springframework.springwebapp.bootstrap;

import com.springframework.springwebapp.domain.Author;
import com.springframework.springwebapp.domain.Book;
import com.springframework.springwebapp.domain.Publisher;
import com.springframework.springwebapp.repositories.AuthorRepository;
import com.springframework.springwebapp.repositories.BookRepository;
import com.springframework.springwebapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher=new Publisher();

        publisher.setAddressLine1("aleea retezat, nr.6");
        publisher.setName("publisher1");
        publisher.setCity("cluj-napoca");
        publisher.setState("cluj");
        publisher.setZip("400680");

        publisherRepository.save(publisher);
        System.out.println("number of publishers: "+publisherRepository.count());

        Author eric=new Author("Eric","Evans");
        Book ddd=new Book("Domain Driven Design","123123");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author john=new Author("John","Doe");
        Book noEJB=new Book("No-EJB","123455");
        john.getBooks().add(noEJB);
        noEJB.getAuthors().add(john);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(john);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("publisher number of books:"+publisher.getBooks().size());





    }
}
