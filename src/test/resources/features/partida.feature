Feature: Partida
    Scenario: Adicionar nova partida com sucesso
        Given uma nova partida a ser adicionada
        When adiciono a partida
        Then a partida é adicionada com sucesso

    Scenario: Buscar partida por número
        Given uma partida existente com número válido
        When busco a partida por número
        Then a partida é retornada com sucesso