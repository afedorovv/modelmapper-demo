package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.convertor.BookConvertor;
import org.example.dto.BookDto;
import org.example.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookConvertor bookConvertor;

    @GetMapping
    public List<BookDto> findAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(bookConvertor::convertToDto)
                .collect(Collectors.toList());
    }
}
