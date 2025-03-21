INSERT INTO usuario (nome, sobrenome, email, senha) VALUES ('Fabio', 'Brown', 'fabio@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO usuario (nome, sobrenome, email, senha) VALUES ('Marcelo', 'Green', 'marcelo@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tipo_servico (descricao) VALUES ('PRIMEIRO EMPLACAMENTO');
INSERT INTO tipo_servico (descricao) VALUES ('LICENCIAMENTO');
INSERT INTO tipo_servico (descricao) VALUES ('EMISSÃO DE HABILITAÇÃO');

INSERT INTO categoria (descricao) VALUES ('HABILITAÇÃO');
INSERT INTO categoria (descricao) VALUES ('VEICULOS');
INSERT INTO categoria (descricao) VALUES ('OUTROS');

INSERT INTO servico (descricao, preco, categoria_id) VALUES ('VISTORIA', '500.00', 2);
INSERT INTO servico (descricao, preco, categoria_id) VALUES ('ATENDENTE', '1000.00', 2);
INSERT INTO servico (descricao, preco, categoria_id) VALUES ('MUDANÇA DE CARACTERÍSTICA', '900.00', 2);
INSERT INTO servico (descricao, preco, categoria_id) VALUES ('1ª HABILITAÇÃO', '800.00', 1);

INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('16219323033', 'fabio@email.com', '123456', '204050-9', 'Fábio Souza', 'Fábio Souza', 'FISICA', '91981559030', '2025-01-01 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('57343832018', 'Deyvid@email.com', '234569', '20201530-5', 'Deyvid Andrade', 'Deyvid Andrade', 'FISICA', '91980807530', '2025-02-01 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('79511914000177', 'andradelegalizacao@email.com', '234569', '20201530-5', 'Andrade Legalização', 'Deyvid Andrade', 'JURIDICA', '91981605033', '2025-03-01 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('12345678901', 'maria@email.com', '345678', '305060-8', 'Maria Silva', 'Maria Silva', 'FISICA', '91981559031', '2024-11-15 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('98765432109', 'joao@email.com', '456789', '405070-7', 'João Santos', 'João Santos', 'FISICA', '91981559032', '2024-12-10 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('11222333444455', 'empresaX@email.com', '567890', '505080-6', 'Empresa X', 'Carlos Oliveira', 'JURIDICA', '91981559033', '2025-01-05 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('22333444555566', 'ana@email.com', '678901', '605090-5', 'Ana Costa', 'Ana Costa', 'FISICA', '91981559034', '2025-01-20 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('33444555666677', 'pedro@email.com', '789012', '705100-4', 'Pedro Alves', 'Pedro Alves', 'FISICA', '91981559035', '2025-02-12 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('44555666777788', 'empresaY@email.com', '890123', '805110-3', 'Empresa Y', 'Fernanda Lima', 'JURIDICA', '91981559036', '2025-02-25 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('55666777888899', 'lucas@email.com', '901234', '905120-2', 'Lucas Mendes', 'Lucas Mendes', 'FISICA', '91981559037', '2025-03-08 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('66777888999900', 'carla@email.com', '012345', '100530-1', 'Carla Rocha', 'Carla Rocha', 'FISICA', '91981559038', '2024-11-30 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('77888999000011', 'empresaZ@email.com', '123456', '110540-0', 'Empresa Z', 'Roberto Nunes', 'JURIDICA', '91981559039', '2024-12-20 00:00:00');
INSERT INTO proprietario (cpf_ou_cnpj, email, habilitacao, identidade, nome, responsavel, tipo_pessoa, telefone, create_at) VALUES ('88999000111122', 'fernando@email.com', '234567', '120550-9', 'Fernando Gomes', 'Fernando Gomes', 'FISICA', '91981559040', '2025-03-15 00:00:00');


INSERT INTO endereco (bairro, cep, complemento, logradouro, numero, proprietario_id) VALUES ('maracangalha', '66110-350', '1ª andar', 'Passagem São Sebastião', '90', 1);
INSERT INTO endereco (bairro, cep, complemento, logradouro, numero, proprietario_id) VALUES ('Cidade Nova', '66400-290', 'Ap 101, Edificio Colina', 'Travessa We 40', '40', 2);
INSERT INTO endereco (bairro, cep, complemento, logradouro, numero, proprietario_id) VALUES ('Centro', '66400-300', 'Sala 204, Edificio Metropolitan', 'Av Presidente Vargas', '60', 3);

--INSERT INTO telefone (proprietario_id, telefones) VALUES ('1','98155-9030');
--INSERT INTO telefone (proprietario_id, telefones) VALUES ('1','98158-3030');
--INSERT INTO telefone (proprietario_id, telefones) VALUES ('2','98080-7530');
--INSERT INTO telefone (proprietario_id, telefones) VALUES ('2','98175-2020');
--INSERT INTO telefone (proprietario_id, telefones) VALUES ('3','98177-1030');
--INSERT INTO telefone (proprietario_id, telefones) VALUES ('3','98160-5033');


INSERT INTO veiculo (placa, marca, modelo, chassi, renavam, cor, combustivel, ano, arrendamento, procedencia, alienacao_finduciaria, crv, data_crv, proprietario_id) VALUES ('JVC5857', 'Honda', 'Fit DX 1.4 Flex 16V 5p Aut.', '123456', '81065431584', 'Azul', 'GASOLINA', '2011', 'NÃO', 'NACIONAL', 'NÃO', '123456', '03/12/2023', '1');
INSERT INTO veiculo (placa, marca, modelo, chassi, renavam, cor, combustivel, ano, arrendamento, procedencia, alienacao_finduciaria, crv, data_crv, proprietario_id) VALUES ('JUG6758', 'Honda', 'CR-V LX 2.0 16V 2WD Mec.', '2023050', '28799793256', 'Prata', 'FLEX', '2012', 'NÃO', 'NACIONAL', 'NÃO', '101020', '03/11/2023', '2');
INSERT INTO veiculo (placa, marca, modelo, chassi, renavam, cor, combustivel, ano, arrendamento, procedencia, alienacao_finduciaria, crv, data_crv, proprietario_id) VALUES ('JUP7161', 'Hyundai', 'HB20S C.Style/C.Plus1.6 Flex 16V Aut. 4p', '352030', '55145632404', 'Preto', 'FLEX', '2014', 'NÃO', 'NACIONAL', 'NÃO', '203580', '05/09/2023', '3');

INSERT INTO ordem_servico (status, tipo_servico_id, observacao, veiculo_id) VALUES ('ABERTA', '1', 'Documentos pendentes de serem entregues', '1');
INSERT INTO ordem_servico (status, tipo_servico_id, observacao, veiculo_id) VALUES ('ABERTA', '2', 'Vistoria Pendente', '2');
INSERT INTO ordem_servico (status, tipo_servico_id, observacao, veiculo_id) VALUES ('ABERTA', '3', 'Solicitar documentos', '3');

INSERT INTO item_ordem_servico (desconto, quantidade, preco, ordemservico_id, servico_id) VALUES ('0.0', '1', '500.00', '1', '1');
INSERT INTO item_ordem_servico (desconto, quantidade, preco, ordemservico_id, servico_id) VALUES ('0.0', '1', '600.00', '1', '2');
INSERT INTO item_ordem_servico (desconto, quantidade, preco, ordemservico_id, servico_id) VALUES ('0.0', '1', '900.00', '2', '3');
INSERT INTO item_ordem_servico (desconto, quantidade, preco, ordemservico_id, servico_id) VALUES ('0.0', '1', '800.00', '3', '4');