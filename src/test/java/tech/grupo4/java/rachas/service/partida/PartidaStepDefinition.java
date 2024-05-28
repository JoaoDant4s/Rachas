package tech.grupo4.java.rachas.service.partida;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tech.grupo4.java.rachas.partida.PartidaRequest;

public class PartidaStepDefinition {

    private RequestSpecification request = RestAssured.given().baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);

    private Response response;
    private PartidaRequest partidaReq;

    @Given("uma nova partida a ser adicionada")
    public void novaPartidaASerAdicionada() {
        partidaReq = new PartidaRequest();
        partidaReq.setNumero(1);
        partidaReq.setTimeA("TimeA");
        partidaReq.setTimeB("TimeB");
        partidaReq.setDuracao("1h");
        partidaReq.setPlacar("1-0");
    }

    @Given("uma partida existente com número válido")
    public void partidaExistenteComNumeroValido() {
        novaPartidaASerAdicionada();
        response = request.body(partidaReq).when().post("/partidas");
        assertEquals(201, response.getStatusCode());
    }

    @Given("uma nova partida sem número")
    public void novaPartidaSemNumero() {
        partidaReq = new PartidaRequest();
        partidaReq.setTimeA("TimeA");
        partidaReq.setTimeB("TimeB");
        partidaReq.setDuracao("1h");
        partidaReq.setPlacar("1-0");
    }

    @When("adiciono a partida")
    public void adicionoAPartida() {
        response = request.body(partidaReq).when().post("/partidas");
    }

    @When("busco a partida por número")
    public void buscoAPartidaPorNumero() {
        response = given().when().get("/partidas/1");
    }

    @Then("a partida é adicionada com sucesso")
    public void partidaAdicionadaComSucesso() {
        assertEquals(201, response.getStatusCode());
    }

    @Then("a partida é retornada com sucesso")
    public void partidaRetornadaComSucesso() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("erro no cadastro da partida {int}")
    public void erroNoCadastro(Integer status) {
        assertEquals(status.intValue(), response.getStatusCode());
    }
}
