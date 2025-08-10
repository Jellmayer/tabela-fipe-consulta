# Consulta na Tabela FIPE 🚗💨

Este projeto é uma aplicação de console desenvolvida em Java que permite ao usuário consultar os preços de carros, motos e caminhões utilizando uma API pública da Tabela FIPE. Esse projeto foi proposto pelo curso "Java: lambdas, streams e Spring Boot", da Alura, durante o programa Oracle Net Education.

## ✨ Funcionalidades
* **Consulta por Tipo de Veículo:** Escolha entre Carros, Motos e Caminhões.
* **Busca de Marcas:** Filtre e selecione a marca desejada a partir de uma lista completa.
* **Busca de Modelos:** Encontre um modelo específico dentro da marca selecionada.
* **Histórico de Preços:** Veja uma lista com os valores do veículo para todos os anos-modelo disponíveis.

## 📈 Melhorias Futuras
* **Tratamento de Erros:** Implementação de tratamento de erros para:
    * Validar as entradas do usuário (ex: garantir que códigos sejam numéricos).
    * Lidar com falhas de conexão ou respostas inesperadas da API.
    * Evitar que a aplicação encerre de forma abrupta em caso de dados inválidos.

## 🛠️ Tecnologias Utilizadas
* **Java 24**
* **Maven e Spring Boot**
* **Java `HttpClient`:** Utilizado para fazer as requisições HTTP para a API.
* **Streams API:** Aplicada para filtrar, ordenar e processar os dados recebidos da API de forma eficiente.
* **Jackson:** A classe `ResponseMapper` utiliza a biblioteca de desserialização Jackson para converter as respostas JSON da API em objetos Java.

## 🔗 API
Este projeto consome os dados da Fipe API (2.0.0), uma API pública não oficial da Tabela FIPE. Acesse em: [API Fipe]<https://deividfortuna.github.io/fipe/v2/>

## 🚀 Como Executar
1.  Clone este repositório para a sua máquina local.
2.  Compile e execute a classe principal.
3.  Siga as instruções exibidas no terminal, e pronto!
