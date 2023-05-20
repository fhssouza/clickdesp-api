INSERT INTO categoria (descricao) VALUES ('HABILITAÇÃO');
INSERT INTO categoria (descricao) VALUES ('VEICULOS');
INSERT INTO categoria (descricao) VALUES ('OUTROS');

INSERT INTO servico (descricao, categoria_id) VALUES ('TRANSFERÊNCIA DE PROPRIEDADE', 2);
INSERT INTO servico (descricao, categoria_id) VALUES ('LICENCIAMENTO', 2);
INSERT INTO servico (descricao, categoria_id) VALUES ('MUDANÇA DE CARACTERÍSTICA', 2);
INSERT INTO servico (descricao, categoria_id) VALUES ('1ª HABILITAÇÃO', 1);

INSERT INTO estado (nome) VALUES ('Pará');
INSERT INTO estado (nome) VALUES ('Amazonas');

INSERT INTO cidade (nome, estado_id) VALUES ('Belém', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 2);