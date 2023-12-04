package com.example.backend.cucumber;

import com.example.backend.domain.dto.ProductVersionDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ListaVersionesTest {
    private Response response;
    String requestBody;
    String productId1;
    String productId2;
    @Given("existe al menos un producto")
    public void crearProductoYVersionDeProducto() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8000;
        RestAssured.basePath = "/products";

        requestBody = "{\"name\":\"Windows\",\"description\":\"Producto personalizado para Windows\",\"productVersions\":[]}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        productId1 = response.jsonPath().getString("data.id");

        requestBody = "{\"name\":\"Samsung\",\"description\":\"Producto personalizado para Samsung\",\"productVersions\":[]}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        productId2 = response.jsonPath().getString("data.id");
    }

    @Given("que hay un producto con versiones")
    public void hayProductoConVersiones() {
        RestAssured.basePath = "/products/versions";

        requestBody = "{\"name\":\"Version 1 de Windows\",\"description\":\"Version inicial de la aplicación para Windows\",\"productId\":" + productId1 + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        requestBody = "{\"name\":\"Version 2 de Windows\",\"description\":\"Version actualizada de la aplicación para Windows\",\"productId\":" + productId1 + "}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();
    }

    @Given("que hay un producto sin versiones")
    public void hayProductoSinVersiones() {
        // No le agrego ninguna version
    }

    @When("el colaborador de soporte ingresa al listado de versiones del producto con versiones")
    public void obtenerVersionesDelProductoConVersiones() {
        RestAssured.basePath = "/products/" + productId1 + "/versions";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get();
    }

    @When("el colaborador de soporte ingresa al listado de versiones del producto sin versiones")
    public void obtenerVersionesDelProductoSinVersiones() {
        RestAssured.basePath = "/products/" + productId2 + "/versions";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get();
    }

    @Then("se le mostrará el listado de las versiones del producto")
    public void listadoDeVersionesDelProductoConVersiones() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<ProductVersionDTO> listaVersiones = response.jsonPath().getList("", ProductVersionDTO.class);

        Assertions.assertEquals(2, listaVersiones.size(), "Se esperaba que la lista de versiones de productos tenga 2 versiones");
    }

    @Then("se le mostrará un listado vacío dado que no hay versiones para el producto")
    public void entoncesSeLeMostraraUnListadoVacio() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<ProductVersionDTO> listaVersiones = response.jsonPath().getList("", ProductVersionDTO.class);

        Assertions.assertTrue(listaVersiones.isEmpty(), "Se esperaba que la lista de versiones de productos esté vacía");
    }
}
