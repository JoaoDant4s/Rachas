INSERT INTO usuario (email, username, password, role)
VALUES ('maria.silva@email.com', 'maria.silva', 'St@rG@z3r^#', 'ADMIN');
INSERT INTO usuario (email, username, password, role)
VALUES ('joao.santos@email.com', 'joao.santos', 'M0n3yB@g#*', 'ADMIN');
INSERT INTO usuario (email, username, password, role)
VALUES ('ana.pereira@email.com', 'ana.pereira', 'St@rG@z3r^#', 'CLIENTE');
INSERT INTO usuario (email, username, password, role)
VALUES ('pedro.oliveira@email.com', 'pedro.oliveira', 'M0n3yB@g#*', 'CLIENTE');
INSERT INTO usuario (email, username, password, role)
VALUES ('laura.costa@email.com', 'laura.costa', 'Blu3B1rd!^', 'CLIENTE');
INSERT INTO usuario (email, username, password, role)
VALUES ('luis.rodrigues@email.com', 'luis.rodrigues', 'Blu3B1rd!^', 'CLIENTE');
INSERT INTO usuario (email, username, password, role)
VALUES ('sofia.almeida@email.com', 'sofia.almeida', 'C0mpl1c@ted#', 'CLIENTE');
INSERT INTO usuario (email, username, password, role)
VALUES ('miguel.ferreira@email.com', 'miguel.ferreira', 'C0mpl1c@ted#', 'CLIENTE');
INSERT INTO usuario (email, username, password, role)
VALUES ('carolina.sousa@email.com', 'carolina.sousa', 'Pr0t3g1d0!', 'CONVIDADO');
INSERT INTO usuario (email, username, password, role)
VALUES ('tiago.santos@email.com', 'tiago.santos', 'Pr0t3g1d0!', 'CONVIDADO');


INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Tarefa aula 1', 'Completar nossa API', '2024-04-15 22:00:00', 'ALTA', 1);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Ler um livro - técnico', 'Ler o livro Código Limpo', '2024-04-30 19:00:00', 'MEDIA', 1);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Ler um livro - cotidiano', 'O poder do hábito', '2024-04-30 19:00:00', 'MEDIA', 1);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Ver um filme', 'Matrix', '2024-04-20 22:00:00', 'BAIXA', 2);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Ver um filme', 'A Origem', '2024-04-25 23:00:00', 'BAIXA', 2);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Finalizar o projeto do projeto', 'Nosso projeto', '2024-04-17 19:00:00', 'ALTA', 3);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Ir ao café', 'Tomar um cafezinho e apreciar a rua', '2024-04-16 09:00:00', 'MEDIA', 3);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Dar uma volta', 'Andar pela cidade como um turista', '2024-04-19 10:00:00', 'BAIXA', 3);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Levar o cachorro pra passear', 'Viva os doguinhos!', '2024-04-16 10:00:00', 'ALTA', 4);
INSERT INTO todo_item (titulo, detalhes, data, prioridade, usuario_id)
    VALUES ('Pizza e cerveja', 'Relaxar! O trabalho já está finalizado', '2024-04-26 19:00:00', 'BAIXA', 4);