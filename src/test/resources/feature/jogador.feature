Feature: Jogador
    Scenario: Cadastrar jogador com sucesso
        Given username e password válidos e usuario nao esta presente no db
        When cadastro o jogador
        Then encontro o jogador cadastrado
    Scenario: Cadastrar jogador sem username
        Given jogador sem o atributo username
        When cadastro o jogador sem username
        Then erro no cadastro 400