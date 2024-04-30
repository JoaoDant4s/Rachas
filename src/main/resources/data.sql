INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Maria Silva', 4.5, 'maria.silva', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Joao Dantas', 4.7, 'joao.dantas', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Ana Pereira', 4.2, 'ana.pereira', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Pedro Oliveira', 4.9, 'pedro.oliveira', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Laura Costa', 4.0, 'laura.costa', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Luis Rodrigues', 4.3, 'luis.rodrigues', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Sofia Almeida', 4.8, 'sofia.almeida', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Miguel Ferreira', 4.6, 'miguel.ferreira', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Carolina Sousa', 3.9, 'carolina.sousa', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');

INSERT INTO jogador (nome, avaliacao, username, password, role)
VALUES ('Tiago Santos', 4.1, 'tiago.santos', '$2a$10$6ASvnj/AxsZGRK.qR/pB8O8k6kj/Cp0fvSO1XE9cafL4ZIzXIDg/q', 'BASIC');


INSERT INTO racha (uuid, localizacao, clima, data, quantidade_maxima, quantidade_atual, disponivel, esporte,
                   avaliacao_minima, duracao, dono_da_bola, prioridade)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'São Paulo, SP', 'Ensolarado', '2024-04-30', 20, 10, true, 'Futebol', 3,
        '15h - 17h', 'Carlos Eduardo', 'ALTA'),
       ('123e4567-e89b-12d3-a456-426614174001', 'Rio de Janeiro, RJ', 'Chuvoso', '2024-05-05', 15, 14, true, 'Vôlei',
        2, '17h - 19h', 'Maria Fernanda', 'MEDIA');


INSERT INTO partida (numero, timeA, timeB, duracao, placar)
VALUES (1, 'A', 'B', '5 min', '2x0'),
       (2, 'A', 'C', '10 min', '2x1');
