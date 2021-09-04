# Blog-Framework

## Resumo:
API REST que representa um Blog, em que para utilizar qualquer funcionalidade é preciso estar logado.
Login com autenticação via Token JWT
Visualizar todos os Posts ou por ID, cadastrar e excluir Posts.
Visualizar todas as Galerias ou por ID, cadastrar e excluir Galerias.
Visualizar todos os Comentários por Post ou visualizar um comentário em específico por ID, cadastrar e excluir Comentários.
OBS: Para excluir qualquer registro é preciso que o usuário seja o criador do tal registro.

## Tecnologias Utilizadas:
-Java

-Spring Boot, Data, Security

-Token JWT

-Beans Validation

-PostgreSQL

-Maven

-Swagger

## Mais informações:
-Documentação Swagger detalhada da API pode ser acessada através da requisição "/swagger-ui/"

-Para se logar, basta fazer um POST para "/auth" passando login e password

-Na pasta src/main/resources há um arquivo data.sql que contém os inserts de alguns registros que populam o BD.

-Na pasta /images há a imagem do diagrama UML do Blog.

-Usuários para testes:
login: alexandre
password: 123456

login: junior
password: 123

login: jose
password: 321
