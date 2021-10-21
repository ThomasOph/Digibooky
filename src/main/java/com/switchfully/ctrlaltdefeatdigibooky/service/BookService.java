package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public void addBook(BookDto newBookDto) {
        bookRepository.addBook(bookMapper.toBook(newBookDto));
    }

    public List<BookDto> getAllBooks() {
        return bookMapper.toDto(bookRepository.getAllBooks());
    }

    /*
    		List<String> strings = new ArrayList<>();
		strings.add("thomas");
		strings.add("christoph");
		strings.add("hans");
		strings.add("ibrahim");
		strings.add("wouter");
		String search = "*h*".replace("*", "(.*)");
		List<String> result = strings.stream()
				.filter(str -> str.matches(search))
				.collect(Collectors.toList());
		System.out.println(result);
     */
}
