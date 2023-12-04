package com.example.backend.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class VerTicketTest {
    private Response response;
    String requestBody;
    String productId;
    String productVersionId;
    String ticketId;
    @Given("que existe al menos un producto con una versión")
    public void crearProductoYVersionDeProducto() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8000;
        RestAssured.basePath = "/products";

        requestBody = "{\"name\":\"Windows\",\"description\":\"Producto personalizado para Windows\",\"productVersions\":[]}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        productId = response.jsonPath().getString("data.id");

        RestAssured.basePath = "/products/versions";

        requestBody = "{\"name\":\"Version 1 de Windows\",\"description\":\"Version inicial de la aplicación para Windows\",\"productId\":" + productId + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        productVersionId = response.jsonPath().getString("data.id");
    }

    @Given("que existe al menos un ticket")
    public void hayVersionConUnTicket() {
        RestAssured.basePath = "/products/versions/tickets";

        requestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":" + productVersionId + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        ticketId = response.jsonPath().getString("data.id");
    }

    @Given("que no hay tickets existentes")
    public void noHayVersionConTickets() {
        // No agrego nigun ticket
    }

    @When("el colaborador de soporte ingresa al detalle del ticket")
    public void obtenerTicketsDeLaVersionConTickets() {
        RestAssured.basePath = "/tickets/" + ticketId;

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get();
    }

    @Then("se le mostrarán todos los datos relacionados al ticket")
    public void detalleDelTicket() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        Assertions.assertEquals("Problema con el login", response.jsonPath().getString("title"), "El title no es el esperado");
        Assertions.assertEquals("Al usuario no lo deja loggearse desde el celular", response.jsonPath().getString("description"), "El description no es el esperado");
        Assertions.assertEquals("ABIERTO", response.jsonPath().getString("state"), "El state no es el esperado");
        Assertions.assertEquals("3", response.jsonPath().getString("severity"), "El severity no es el esperado");
        Assertions.assertEquals(productVersionId, response.jsonPath().getString("productVersionId"), "El productVersionId no es el esperado");
    }

    @Then("no se le mostrará nada")
    public void detalleDeTicketInexistente() {
        Assertions.assertEquals(400, response.getStatusCode(), "Se esperaba un código de estado 400");
    }
}
