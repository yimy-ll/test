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

public class ListaProductosTest {
    private Response response;
    String requestBody;

    @Given("hay productos creados")
    public void hayProductos() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8000;
        RestAssured.basePath = "/products";

        requestBody = "{\"name\":\"Windows\",\"description\":\"Producto personalizado para Windows\",\"productVersions\":[]}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();

        requestBody = "{\"name\":\"Samsung\",\"description\":\"Producto personalizado para Samsung\",\"productVersions\":[]}";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post();
    }

    @Given("no hay productos creados")
    public void noHayProductos() {
        // No se crea ningun producto
    }

    @When("el colaborador de soporte visualiza el listado de productos")
    public void obtenerListadoDeProductos() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8000;
        RestAssured.basePath = "/products";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .get();
    }

    @Then("se le mostrará el listado de los productos")
    public void listadoDeProductos() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<ProductDTO> listaProductos = response.jsonPath().getList("", ProductDTO.class);

        Assertions.assertEquals(2, listaProductos.size(), "Se esperaba que la lista de productos tenga 2 productos");
    }

    @Then("no se mostrará ningún producto")
    public void listadoDeProductosVacio() {
        Assertions.assertEquals(200, response.getStatusCode(), "Se esperaba un código de estado 200");

        List<ProductDTO> listaProductos = response.jsonPath().getList("", ProductDTO.class);

        Assertions.assertTrue(listaProductos.isEmpty(), "Se esperaba que la lista de productos esté vacía");
    }
}
