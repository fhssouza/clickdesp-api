# SouzaTech

#### Autores
1. [x] [Fábio Souza](https://github.com/fhssouza)

## Clickdesp

1. [ ] Api para despachante de veículos

### Tecnologias

* Java 17
* SpringBoot 2.7.11
* SpringWeb
* SpringTest
* SpringDataJpa
* Hibernate
* H2 database
* Postgres
* Flyway
* Spring Segurity
* JWT
* Password Encoder
* Projeto Lombok
* Swagger OpenAPI
* DBeaver

#### Ambientes de execução

O projeto pode se executado em ambiente dev que roda o banco de dados Postgres ou em Teste que o roda o banco de dados H2 em  `resources/application.properties`.

*Configurando a execução em modo Test*


#### **`application.properties`**
```shell
spring.profiles.active=test
```

*Configurando a execução em modo Dev*


#### **`application.properties`**
```shell
spring.profiles.active=dev
```

### Executando a aplicação

Para executar a aplicação é simples, basta abrir a classe `SpringbootCrudApiApplication` e ativar  `run` ou `debug` em sua IDE.

ou executar o comando abaixo:

```shell
mvn spring-boot:run
```

### Endpoint OpenAPI Swagger

1. [x] [http://localhost:8080/clickdesp/swagger-ui/index.html](http://localhost:8080/clickdesp/swagger-ui/index.html)



