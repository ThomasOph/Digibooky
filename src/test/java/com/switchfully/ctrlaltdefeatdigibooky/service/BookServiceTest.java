package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookCreateDto;

import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.RentalMapper;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.UserMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.*;
import com.switchfully.ctrlaltdefeatdigibooky.repository.BookRepository;
import com.switchfully.ctrlaltdefeatdigibooky.repository.FineRepository;
import com.switchfully.ctrlaltdefeatdigibooky.repository.RentalRepository;
import com.switchfully.ctrlaltdefeatdigibooky.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookServiceTest {
//    private RentalRepository rentalRepository = new RentalRepository();
//    private BookRepository bookRepository = new BookRepository();
//    private UserRepository userRepository = new UserRepository();
//    private RentalMapper rentalMapper = new RentalMapper();
//    private UserMapper userMapper = new UserMapper();
//    private UserService userService = new UserService(userRepository,
//            userMapper);
//    private BookService bookService = new BookService(bookRepository,
//            userService, )
//    private RentalService rentalService = new RentalService(rentalRepository,
//            );
//
//    @Test
//    void addNewBookDto() {
//        Book book1 = new Book("979 0 596 52068 7", "title 1", new Author("Christoph", "Parrez"), "summary 1");
//        Book book2 = new Book("979 0 596 52068 7", "title 2", new Author("Christoph", "Parrez"), "summary 2");
//        Book book3 = new Book("979 0 596 52068 7", "title 3", new Author("Christoph", "Parrez"), "summary 3");
//
//        BookRepository repository = new BookRepository();
//        BookMapper mapper = new BookMapper();
//        BookService service = new BookService(repository,
//                new UserService(new UserRepository(),
//                        new UserMapper(new FineService(new FineRepository()))), new RentalService(new RentalRepository()));
//
//        BookCreateDto bookDto1 = mapper.toCreateDto(book1);
//        BookCreateDto bookDto2 = mapper.toCreateDto(book2);
//        BookCreateDto bookDto3 = mapper.toCreateDto(book3);
//
//        service.addBook(bookDto1, "TheLibrarian");
//        service.addBook(bookDto2, "TheLibrarian");
//        service.addBook(bookDto3, "TheLibrarian");
//
//        Assertions.assertNotNull(service.getAllBooks());
//    }
}