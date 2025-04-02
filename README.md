# Projeto Covil do Calango

Este é um projeto de uma API utilizando Spring Boot 3.0, baseado na arquitetura de microserviços e com suporte a várias funcionalidades do Spring Framework, como injeção de dependência, segurança, JPA, entre outros.

## Estrutura do Projeto

A estrutura do projeto segue o padrão típico do Spring Boot:

- **controller**: Contém as classes que gerenciam as requisições HTTP.
- **model**: Contém as classes de modelo (entidades do JPA).
- **repository**: Contém os repositórios do Spring Data JPA.
- **service**: Contém a lógica de negócios.
- **application.properties**: Configurações da aplicação.:

## Objetivo

Esse projeto é uma API para ser consumida pelos demais sistemas da Covil do Calango, uma loja Geek de board e card games, o principal foco da API é auxiliar na geração de comandas, tendo em vista que a loja também trabalha com alimentação, servindo lanches e produtos para seus clientes.

Atualmente o sistema está sendo servido no [render](http://render.com/) e exposto por essa [url](https://covil-calango-api.onrender.com).


