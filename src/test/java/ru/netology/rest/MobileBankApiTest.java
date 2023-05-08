package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


class MobileBankApiTest {
    @Test
    void shouldReturnStatusCode() {
        given().
                baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .statusCode(200)
        ;
    }

    @Test
    void shouldReturnContenType() {
        given().
                baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
        ;
    }

    @Test
    void shouldReturnBody() {
        given().
                baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .statusCode(200)
                .body("", hasSize(3))
                .body("[1].currency", equalTo("USD"))
                .body("every { it.balance >= 0 }", is(true))
        ;
    }

    @Test
    void shouldReturnJsonScheme() {
        given().
                baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"))
        ;
    }
}