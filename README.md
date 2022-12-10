# Carrinho de compra API

## Descrição

REST API construída com JAVA. Documentação feita com Swagger UI.

## Status do Projeto
🚧 API em construção

## Features

 - [x] Inserir items no carrinho
 - [x] Receber dados do carrinho
 - [x] Fechar um carrinho
 - [ ] Excluir itens do carrinho

## Rodando a aplicação localmente

Tenha instalado em sua máquina:

 - JavaJDK v.8
 - Git
 - Grade v.7
 - Postman

Siga os passos seguintes para rodar localmente a aplicação:

 1. Clone o repositório: 
        
        git clone git@github.com:andersonfpcorrea/shopping-cart-api.git
2. Entre na raiz do projeto:

        cd shopping-cart-api
4. Rode o comando:
        
       ./gradelew bootrun
5. Acesse [localhost:8080/swagger-ui](http://localhost:8080/swagger-ui/#/cart-resource) para ver a documentação e interagir com os `endpoints`

![Swagger page](https://i.ibb.co/zNYmRSv/Screenshot-from-2022-12-10-18-31-45.png)

## Tecnologias usadas

<table>
<tr>
	<th>Dependência</th>
	<th>Versão</th>
</tr>
<tr>
	<td>spring initialzr</td>
	<td><a href="https://start.spring.io/">https://start.spring.io/</a></td>
</tr>
<tr>
	<td>spring-boot-starter-web</td>
	<td>2.7.4</td>
</tr>
<tr>
	<td>spring-boot-starter-data-jpa</td>
	<td>2.7.4</td>
</tr>
<tr>
	<td>lombok</td>
	<td>1.18.24</td>
</tr>
<tr>
	<td>springfox-boot-starter</td>
	<td>3.0.0</td>
</tr>
<tr>
	<td>h2</td>
	<td>2.1.214</td>
</tr>
</table>


