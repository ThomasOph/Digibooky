package com.switchfully.ctrlaltdefeatdigibooky.service;

import com.switchfully.ctrlaltdefeatdigibooky.dto.BookDto;
import com.switchfully.ctrlaltdefeatdigibooky.mappers.BookMapper;
import com.switchfully.ctrlaltdefeatdigibooky.model.Book;
import com.switchfully.ctrlaltdefeatdigibooky.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Autowired
    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addBook(BookDto newBookDto) {
        repository.addBook(mapper.toBook(newBookDto));
    }

    public List<BookDto> getAllBooks() {
        return mapper.toDto(repository.getAllBooks());
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
