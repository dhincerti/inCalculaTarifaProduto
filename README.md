# Objetivo

Esse repositório tem como objetivo, apresentar uma solução simples, porém correta, do
[Desafio Produtos de Seguros 🚀](https://github.com/itausegdev/backend-challenge)

# Architecture

## Linguagem e persistência
Java foi escolhido como linguagem principal principalmente por:
* Meu conhecimento pessoal;
* A estrutura Spring Boot
    * Facilita a criação de aplicativos independentes (sem necessidade de configurações de servidor)
      o que nos permite começar rapidamente a codificar o que realmente agrega valor: regras de negócios.

Banco de dados H2
* Totalmente compatível com a linguagem
* Peristência feita em memória, não tendo a necessidade de instalação/configuração de infra
* Disponibiliza console web para gerenciamento
* Uma das opções sugeridas pelo teste 

Veja mais:
* [Why Spring Boot?](https://dzone.com/articles/why-springboot)
* [Spring Boot Autoscaler](https://piotrminkowski.wordpress.com/2018/09/18/spring-boot-autoscaler/)
* [H2 Database Engine](https://www.h2database.com/html/main.html)

# Como executar o projeto
Dependências:
- [JDK 17 (ou superior)](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3.2+](https://maven.apache.org/download.cgi)

Depois de clonar ou baixar este projeto, você pode usar a linha de comando para navegar até
a pasta do projeto e executar:

`mvn spring-boot:run`

Ou

Abra sua IDE favorita e excecute a classe principal CalculaTarifasApplicationRunner


# Utilizando o projeto
Assim que o projeto estiver rodando e funcionando, as APIs de produto estarão disponíveis por meio dos endpoint:
- `POST localhost:8080/api/produto`
- `GET localhost:8080/api/produto/{id}`
- `PUT localhost:8080/api/produto/{id}`
- `DELETE localhost:8080/api/produto/{id}`

A collection para o Insomnia está disponível em `src/main/resources/collection-Insomnia.json`

## Exemplo:
### Curl
```
curl --request POST \
--url http://localhost:8080/api/produto \
--header 'Content-Type: application/json' \
--data '{
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": 100.0000
}'
```

### Request Body:
```javascript
{
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": 100.0000
}
```

### Response body:
```javascript
{
    "id": "33e778b0-c226-46cc-a304-3fa88c7a3962",
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": "100.00",
    "preco_tarifado": "103.20"
}
```

# Utilizando o console H2
A interface grafica está habilitada para facilitar a consulta direta ao banco de dados, caso necessário.
Bastando acessar no seu navegador a URL local:
- `http://localhost:8080/api/h2-console`

Ao abrir a tela inicial, informar os seguintes valores:
- JDBC URL: `jdbc:h2:mem:produtos`
- User Name: `sa`
- Password: `admin`