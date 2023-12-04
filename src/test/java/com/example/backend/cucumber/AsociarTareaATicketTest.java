package com.example.backend.cucumber;

import com.example.backend.domain.dto.ProductDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AsociarTareaATicketTest {
    private Response response;
    String requestBody;
    String productId;
    String productVersionId;
    String ticketId;
    String tarea;
    @Given("que existe un ticket")
    public void crearTicket() {
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

        RestAssured.basePath = "/products/versions/tickets";

        requestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":" + productVersionId + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        ticketId = response.jsonPath().getString("data.id");
    }

    @Given("que hay una tarea")
    public void hayUnaTarea() {
        // Creo tarea desde el backend de Proyectos
        tarea = "1";
    }

    @Given("que no hay tareas existentes")
    public void noHayUnaTarea() {
        // No hay niguna tarea creada
        tarea = "";
    }

    @Given("hay una tarea asociada a un ticket")
    public void hayTareaAsociadaATicket() {
        RestAssured.basePath = "/tickets/" + ticketId;

        tarea = "1";

        requestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":" + productVersionId + ",\"listLinkedTasks\":[" + tarea + "]}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put();
    }

    @When("el colaborador de soporte intenta asociar la tarea al ticket")
    public void asociarTareaATicket() {
        RestAssured.basePath = "/tickets/" + ticketId;

        requestBody = "{\"title\":\"Problema con el login\",\"description\":\"Al usuario no lo deja loggearse desde el celular\",\"state\":\"ABIERTO\",\"severity\":3,\"productVersionId\":" + productVersionId + ",\"listLinkedTasks\":[" + tarea + "]}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put();
    }

    @Then("se le informará que la tarea se asoció correctamente al ticket indicado")
    public void asociacionExitosa() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<Long> listaTareas = response.jsonPath().getList("listLinkedTasks", Long.class);
        Assertions.assertEquals(1, listaTareas.size(), "Se esperaba que la lista de tareas tenga 1 tarea");
    }

    @Then("se le informará que no hay tareas existentes, por lo que no se puede asociar al ticket")
    public void asociacionSinExito() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<Long> listaTareas = response.jsonPath().getList("listLinkedTasks", Long.class);
        Assertions.assertTrue(listaTareas.isEmpty(), "Se esperaba que la lista de tareas esté vacía");
    }

    @Then("no se hará nada ya que la tarea es la misma a la que estaba asociado anteriormente")
    public void asociacionYaExistente() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<Long> listaTareas = response.jsonPath().getList("listLinkedTasks", Long.class);
        Assertions.assertEquals(1, listaTareas.size(), "Se esperaba que la lista de tareas tenga 1 tarea");
    }
}
