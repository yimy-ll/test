package com.example.backend.cucumber;

import com.example.backend.domain.dto.TicketDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ListaTicketsTest {
    private Response response;
    String requestBody;
    String productId;
    String productVersionId1;
    String productVersionId2;
    @Given("que existe al menos un producto con una versión con tickets y otra versión sin tickets")
    public void crearProductoYDosVersionesDeProducto() {
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

        productVersionId1 = response.jsonPath().getString("data.id");

        requestBody = "{\"name\":\"Version 2 de Windows\",\"description\":\"Version actualizada de la aplicación para Windows\",\"productId\":" + productId + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        productVersionId2 = response.jsonPath().getString("data.id");
    }

    @Given("que hay una versión con tickets")
    public void hayVersionConTickets() {
        RestAssured.basePath = "/products/versions/tickets";

        requestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":" + productVersionId1 + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        requestBody = "{\"title\":\"Problema con la barra de tareas\",\"description\":\"Al usuario no le aparece la barra de tareas\",\"state\":\"ABIERTO\",\"severity\":2,\"productVersionId\":" + productVersionId1 + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();
    }

    @Given("que hay una versión sin tickets")
    public void hayVersionSinTickets() {
        // No le agrego ningun ticket
    }

    @When("el colaborador de soporte ingresa al listado de tickets de la versión con tickets")
    public void obtenerTicketsDeLaVersionConTickets() {
        RestAssured.basePath = "/products/versions/" + productVersionId1 + "/tickets";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get();
    }

    @When("el colaborador de soporte ingresa al listado de tickets de la versión sin tickets")
    public void obtenerTicketsDeLaVersionSinTickets() {
        RestAssured.basePath = "/products/versions/" + productVersionId2 + "/tickets";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get();
    }

    @Then("se le mostrará el listado de los tickets de la versión")
    public void listadoDeVersionesDelProductoConVersiones() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<TicketDTO> listaTickets = response.jsonPath().getList("", TicketDTO.class);

        Assertions.assertEquals(2, listaTickets.size(), "Se esperaba que la lista de tickets de productos tenga 2 tickets");
    }

    @Then("se le mostrará un listado vacío dado que no hay tickets para la versión")
    public void entoncesSeLeMostraraUnListadoVacio() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<TicketDTO> listaTickets = response.jsonPath().getList("", TicketDTO.class);

        Assertions.assertTrue(listaTickets.isEmpty(), "Se esperaba que la lista de tickets de la versión esté vacía");
    }
}
