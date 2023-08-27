INSERT INTO usuario (nome, sobrenome, email, senha) VALUES ('Fabio', 'Brown', 'fabio@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO usuario (nome, sobrenome, email, senha) VALUES ('Marcelo', 'Green', 'marcelo@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO categoria (descricao) VALUES ('HABILITAÇÃO');
INSERT INTO categoria (descricao) VALUES ('VEICULOS');
INSERT INTO categoria (descricao) VALUES ('OUTROS');

INSERT INTO servico (descricao, preco, categoria_id) VALUES ('TRANSFERÊNCIA DE PROPRIEDADE', '500.00', 2);
INSERT INTO servico (descricao, preco, categoria_id) VALUES ('LICENCIAMENTO', '1000.00', 2);
INSERT INTO servico (descricao, preco, categoria_id) VALUES ('MUDANÇA DE CARACTERÍSTICA', '900.00', 2);
INSERT INTO servico (descricao, preco, categoria_id) VALUES ('1ª HABILITAÇÃO', '800.00', 1);

INSERT INTO estado (nome) VALUES ('Pará');
INSERT INTO estado (nome) VALUES ('Amazonas');

INSERT INTO cidade (nome, estado_id) VALUES ('Belém', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Manaus', 2);

INSERT INTO proprietario (cpf_ou_cnpj, email, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, habilitacao, identidade, nome, responsavel, tipo, endereco_cidade_id) VALUES ('16219323033', 'fabio@email.com', 'maracangalha', '66110-350', '1ª andar', 'Passagem São Sebastião', '90', '123456', '204050-9', 'Fábio Souza', 'Fábio Souza', 'FISICA', 1);
INSERT INTO proprietario (cpf_ou_cnpj, email, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, habilitacao, identidade, nome, responsavel, tipo, endereco_cidade_id) VALUES ('57343832018', 'Deyvid@email.com', 'Cidade Nova', '66400-290', 'Ap 101, Edificio Colina', 'Travessa We 40', '40', '234569', '20201530-5', 'Deyvid Andrade', 'Deyvid Andrade', 'FISICA', 1);
INSERT INTO proprietario (cpf_ou_cnpj, email, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, habilitacao, identidade, nome, responsavel, tipo, endereco_cidade_id) VALUES ('79511914000177', 'andradelegalicacao@email.com', 'Centro', '66400-300', 'Sala 204, Edificio Metropolitan', 'Av Presidente Vargas', '60', '234569', '20201530-5', 'Andrade Legalicação', 'Deyvid Andrade', 'JURIDICA', 1);

INSERT INTO telefone (proprietario_id, telefones) VALUES ('1','98155-9030');
INSERT INTO telefone (proprietario_id, telefones) VALUES ('1','98158-3030');
INSERT INTO telefone (proprietario_id, telefones) VALUES ('2','98080-7530');
INSERT INTO telefone (proprietario_id, telefones) VALUES ('2','98175-2020');
INSERT INTO telefone (proprietario_id, telefones) VALUES ('3','98177-1030');
INSERT INTO telefone (proprietario_id, telefones) VALUES ('3','98160-5033');

INSERT INTO veiculo (placa, marca, modelo, chassi, renavam, cor, combustivel, ano, arrendamento, procedencia, alienacao_finduciaria, crv, data_crv, proprietario_id) VALUES ('JVC5857', 'Honda', 'Fit DX 1.4 Flex 16V 5p Aut.', '123456', '81065431584', 'Azul', 'GASOLINA', '2011', FALSE, 'NACIONAL', FALSE, '123456', '2011-12-03', '1');

INSERT INTO ordem_servico (status, observacao, veiculo_id) VALUES ('ABERTO', 'Teste', '1');

INSERT INTO item_ordem_servico (desconto, quantidade, preco, ordemservico_id, servico_id) VALUES ('0.0', '1', '500.00', '1', '1');
INSERT INTO item_ordem_servico (desconto, quantidade, preco, ordemservico_id, servico_id) VALUES ('0.0', '1', '600.00', '1', '2');