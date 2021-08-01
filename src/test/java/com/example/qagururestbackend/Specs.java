package com.example.qagururestbackend;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.*;

public class Specs {

    public static RequestSpecification request = with()
            .baseUri("http://localhost:8080")
            .log().all()
            .contentType(JSON);

    public static RequestSpecification requestToGetAuthor = with()
            .baseUri("http://localhost:8080")
            .log().all()
            .contentType(JSON)
            .body("{\"book_author\": \"Савин Р.\"}");

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
