Feature: Racha
    Scenario: Adicionar novo racha com sucesso
        Given um novo racha a ser adicionado
        When cadastro o racha
        Then o racha é adicionado com sucesso

    Scenario: Buscar racha por UUID
        Given um racha existente com UUID válido
        When busco o racha por UUID
        Then o racha é retornado com sucesso