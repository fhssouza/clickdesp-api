create table categoria (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	primary key (id)
) engine=InnoDB;

create table cidade (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	estado_id bigint,
	primary key (id)
) engine=InnoDB;

create table estado (
	id bigint not null auto_increment,
	nome varchar(20) not null,
	primary key (id)
) engine=InnoDB;

create table item_ordem_servico (
	id bigint not null auto_increment,
	observacao varchar(255),
	valor_servico decimal(19,2),
	ordem_servico_id bigint not null,
	servico_id bigint not null,
	primary key (id)
) engine=InnoDB;

create table ordem_servico (
	id bigint not null auto_increment,
	data_criacao datetime(6),
	observacao varchar(255),
	status varchar(20),
	veiculo_id bigint,
	primary key (id)
) engine=InnoDB;

create table proprietario (
	id bigint not null auto_increment,
	cpf_ou_cnpj varchar(18) not null,
	email varchar(100) not null,
	endereco_bairro varchar(30) not null,
	endereco_cep varchar(10) not null,
	endereco_complemento varchar(100) not null,
	endereco_logradouro varchar(200) not null,
	endereco_numero varchar(10) not null,
	habilitacao varchar(20) not null,
	identidade varchar(20) not null,
	nome varchar(80) not null,
	responsavel varchar(80) not null,
	tipo varchar(50),
	endereco_cidade_id bigint,
	primary key (id)
) engine=InnoDB;

create table servico (
	id bigint not null auto_increment,
	descricao varchar(100) not null,
	categoria_id bigint not null,
	primary key (id)
) engine=InnoDB;

create table telefone (
	proprietario_id bigint not null,
	telefones varchar(10)
) engine=InnoDB;

create table usuario (
	id bigint not null auto_increment,
	email varchar(100) not null,
	nome varchar(80) not null,
	senha varchar(100) not null,
	sobrenome varchar(80) not null,
	primary key (id)
) engine=InnoDB;

create table veiculo (
	id bigint not null auto_increment,
	alienacao_finduciaria bit not null,
	ano integer not null,
	arrendamento bit not null,
	chassi varchar(20) not null,
	combustivel varchar(20) not null,
	cor varchar(20) not null,
	crv integer not null,
	data_crv datetime(6) not null,
	marca varchar(20) not null,
	modelo varchar(60) not null,
	placa varchar(20) not null,
	procedencia varchar(20) not null,
	renavam varchar(20) not null,
	proprietario_id bigint,
	primary key (id)
) engine=InnoDB;

alter table cidade add constraint fk_cidade_estado
foreign key (estado_id) references estado (id);

alter table item_ordem_servico add constraint fk_item_ordem_servico_ordem_servico
foreign key (ordem_servico_id) references ordem_servico (id);

alter table item_ordem_servico add constraint fk_item_ordem_servico_servico
foreign key (servico_id) references servico (id);

alter table ordem_servico add constraint fk_ordem_servico_veiculo
foreign key (veiculo_id) references veiculo (id);

alter table proprietario add constraint fk_proprietario_cidade
foreign key (endereco_cidade_id) references cidade (id);

alter table servico add constraint fk_servico_categoria
foreign key (categoria_id) references categoria (id);

alter table telefone add constraint fk_telefone_proprietario
foreign key (proprietario_id) references proprietario (id);

alter table veiculo add constraint fk_veiculo_proprietario
foreign key (proprietario_id) references proprietario (id);