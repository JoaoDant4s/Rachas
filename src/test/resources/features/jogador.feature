Feature: Jogador
    Scenario: Cadastrar jogador com sucesso
        Given username e password válidos e usuario nao esta presente no db
        When cadastro o jogador
        Then encontro o jogador cadastrado

    Scenario: Cadastrar jogador sem username
        Given jogador sem o atributo username
        When cadastro o jogador sem username
        Then erro no cadastro do jogador 400

    Scenario: Buscar jogador por username
        Given um jogdor existente com username valido
        When busco o jogador por username
        Then o jogador é retornado com sucesso