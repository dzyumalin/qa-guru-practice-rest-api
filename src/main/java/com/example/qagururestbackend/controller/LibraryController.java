package com.example.qagururestbackend.controller;

import com.example.qagururestbackend.domain.BookInfo;
import com.example.qagururestbackend.domain.BooksOutput;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    Map<String, BookInfo> books = Map.of(
            "Куликов С.", BookInfo.builder()
                    .bookAuthor("Куликов С.")
                    .bookTitle("Тестирование программного обеспечения. Базовый курс 2-е издание")
                    .build(),
            "Савин Р.", BookInfo.builder()
                    .bookAuthor("Савин Р.")
                    .bookTitle("Тестирование DOT COM")
                    .build(),
            "Бек К.", BookInfo.builder()
                    .bookAuthor("Бек К.")
                    .bookTitle("Экстремальное программирование. Разработка через тестирование")
                    .build()
    );

    @PostMapping("book/add")
    public BooksOutput addBookTitle(@RequestBody BookInfo bookInfo) {
        if (books.get(bookInfo.getBookTitle()) == null) {
            return BooksOutput.builder()
                    .bookTitle(bookInfo.getBookTitle())
                    .bookAuthor(bookInfo.getBookAuthor())
                    .report("Book added")
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book used");
        }
    }

    @GetMapping("books/getAll")
    @ApiOperation("get all books")
    public List<BookInfo> getAllBooksInfo() {
        return books.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
