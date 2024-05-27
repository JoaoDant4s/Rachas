Feature: Racha
    Scenario: Adicionar novo racha com sucesso
        Given um novo racha a ser adicionado
        When cadastro o racha
        Then encontro o racha cadastrado

    Scenario: Buscar racha por UUID
        Given um racha existente com UUID válido
        When busco o racha por UUID
        Then o racha é retornado com sucesso
        
    Scenario: Tentar adicionar um novo racha sem data
        Given um novo racha sem data
        When tento adicionar o racha
        Then erro no cadastro 400