package tech.grupo4.java.rachas.service.racha;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tech.grupo4.java.rachas.racha.RachaRequest;

public class RachaStepDefinition {

    private RequestSpecification request = RestAssured.given().baseUri("http://localhost:8080")
            .contentType(ContentType.JSON);

    private Response response;
    private RachaRequest rachaReq;

    @Given("um novo racha a ser adicionado")
    public void novoRachaASerAdicionado() {
        rachaReq = new RachaRequest();
        rachaReq.setLocalizacao("Local");
        rachaReq.setClima("Clima");
        rachaReq.setQuantidadeMaxima(10);
        rachaReq.setQuantidadeAtual(0);
        rachaReq.setEsporte("Esporte");
        rachaReq.setAvaliacaoMinima(5);
        rachaReq.setDuracao("Duracao");
        rachaReq.setDonoDaBola("tiago.santos");
    }

    @Given("um racha existente com UUID válido")
    public void rachaExistenteComUUIDValido() {

    }

    @Given("um novo racha sem data")
    public void novoRachaSemData() {
        rachaReq = new RachaRequest();
        rachaReq.setLocalizacao("Local");
        rachaReq.setClima("Clima");
        rachaReq.setQuantidadeMaxima(10);
        rachaReq.setQuantidadeAtual(0);
        rachaReq.setEsporte("Esporte");
        rachaReq.setAvaliacaoMinima(5);
        rachaReq.setDuracao("Duracao");
        rachaReq.setDonoDaBola("Dono");
    }

    @When("cadastro o racha")
    public void adicionoORacha() {
        response = request.body(rachaReq).when().post("/rachas");
    }

    @When("busco o racha por UUID")
    public void buscoORachaPorUUID() {
        response = given().when().get("/rachas/123e4567-e89b-12d3-a456-426614174000");
    }

    @Then("o racha é adicionado com sucesso")
    public void rachaAdicionadoComSucesso() {
        assertEquals(201, response.getStatusCode());
    }

    @Then("o racha é retornado com sucesso")
    public void rachaRetornadoComSucesso() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("erro no cadastro do racha {int}")
    public void erroNoCadastro(Integer status) {
        assertEquals(status, response.getStatusCode());
    }
}
