package tech.grupo4.java.rachas.service.jogador;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tech.grupo4.java.rachas.model.JogadorRequest;

public class JogadorStepDefinition {
    private RequestSpecification request = RestAssured.given().baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);

    private Response response;
    private JogadorRequest jogadorReq;

    @Given("username e password válidos e usuario nao esta presente no db")
    public void jogadorNotExist() {
        jogadorReq = new JogadorRequest();
        jogadorReq.setNome("joao");
        jogadorReq.setUsername("joao.dantas");
        jogadorReq.setPassword("Qw3@Vp8x$eR7nB#1mZ");

        response = request.when().delete("/jogadores/joao.dantas");
    }

    @Given("jogador sem o atributo username")
    public void jogadorWithoutUsername() {
        jogadorReq = new JogadorRequest();
        jogadorReq.setNome("joao");
        jogadorReq.setPassword("Qw3@Vp8x$eR7nB#1mZ");
    }

    @Given("um jogdor existente com username valido")
    public void jogadorExistenteComUsernameValido() {
        // Assegure-se de que o jogador existe no banco de dados antes de buscá-lo
        jogadorNotExist();
        response = request.body(jogadorReq).when().post("/jogadores");
        assertEquals(201, response.getStatusCode(), "Erro ao criar jogador: " + response.getBody().asString());
    }

    @When("cadastro o jogador")
    public void registerJogador() {
        response = request.body(jogadorReq).when().post("/jogadores");
    }

    @When("cadastro o jogador sem username")
    public void registerJogadorWithoutName() {
        response = request.body(jogadorReq).when().post("/jogadores");
    }

    @When("busco o jogador por username")
    public void buscoOJogadorPorUsername() {
        response = request.when().get("/jogadores/joao.dantas");
    }

    @Then("encontro o jogador cadastrado")
    public void searchJogador() {
        response = request.pathParam("username", "joao.dantas").when().get("/jogadores/{username}");
        String name = response.jsonPath().getString("username");
        assertEquals("joao.dantas", name);
    }

    @Then("erro no cadastro do jogador {int}")
    public void error400(Integer status) {
        assertEquals(status.intValue(), response.getStatusCode());
    }

    @Then("o jogador é retornado com sucesso")
    public void jogadorRetornadoComSucesso() {
        assertEquals(200, response.getStatusCode());
    }
}
