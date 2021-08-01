package com.example.qagururestbackend;

import com.example.qagururestbackend.domain.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.qagururestbackend.Specs.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class QaGuruRestBackendApplicationTests {

    @Test
    void getAllBooksTest() {
        given()
                .spec(request)
                .when()
                .get("books/getAll")
                .then()
                .log().body()
                .spec(responseSpec)
                .extract()
                .as(BookInfo[].class);
    }

    @Test
    void getBookAuthorTest() {
        given()
                .spec(requestToGetAuthor)
                .when()
                .get("books/getAll")
                .then()
                .log().body()
                .spec(responseSpec)
                .body("book_author", hasItem("Савин Р."));
    }

    @Test
    void addBookTest() {
        given()
                .spec(request)
                .body("{\"book_author\": \"Бхаргава А.\"," +
                        "\"book_title\": \"Грокаем алгоритмы\"}")
                .when()
                .post("book/add")
                .then()
                .log().body()
                .spec(responseSpec)
                .body("book_author", is("Бхаргава А."))
                .body("book_title", is("Грокаем алгоритмы"))
                .body("report", is("Book added"));
    }

}
