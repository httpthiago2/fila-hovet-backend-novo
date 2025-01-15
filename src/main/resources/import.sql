INSERT INTO filahovet.sala (id, nome) values (1, 'Consultório 001');
INSERT INTO filahovet.usuario (id, nome, usuario, senha, perfil) values (1, "Thiago Gonzalez", "tgonzalez", "12345", "MEDICO")

INSERT INTO filahovet.fila (id, nome, codigo, situacao, sala_id, usuario_id) VALUES (1, 'Raio X', 'RX', 'ABERTA', 1, 1);

INSERT INTO filahovet.senha (ordem, tipo, pet, tutor, situacao, fila_id) values (1, 'REGULAR', 'Tito', 'Thiago', 'ATENDIDA', 1);

INSERT INTO filahovet.senha (ordem, tipo, pet, tutor, situacao, fila_id) values (2, 'PRIORIDADE', 'Lui', 'Luisa', 'ATENDIDA', 1);

INSERT INTO filahovet.senha (ordem, tipo, pet, tutor, situacao, fila_id) values (3, 'REGULAR', 'Lui', 'Luisa', 'PENDENTE_ATENDIMENTO', 1);

INSERT INTO filahovet.senha (ordem, tipo, pet, tutor, situacao, fila_id) values (4, 'REGULAR', 'Pipoca', 'João', 'PENDENTE_ATENDIMENTO', 1);

INSERT INTO filahovet.senha (ordem, tipo, pet, tutor, situacao, fila_id) values (5, 'REGULAR', 'Cachorro', 'Fabio', 'PENDENTE_ATENDIMENTO', 1);
