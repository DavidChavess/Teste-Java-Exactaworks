![logo](https://user-images.githubusercontent.com/49525514/89597610-f9d90280-d830-11ea-8421-bb76e3ac9809.png)

# Processo seletivo Programador Java

## 💻 Project

Criar uma aplicação web para gerenciamento de gastos de um pessoa

### Progresso
1. Criar uma API RESTful para gerenciar gastos de uma pessoa.
2. Criar uma client para acessar as funcionalidades da API do passo 1.
3. Criar um projeto WEB para acessar a API do passo 1 usando o client do passo

### Modelo de Dados do Gasto:
- Id gasto.
- Nome da pessoa.
- Descrição.
- data/hora.
- Valor.
- Tags.

## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias backend e web:
### Backend
- Java 11
- JPA
- Hibernate
- Documentação com Swagger <a href= 'http://localhost:8080/swagger-ui.html'>link</a>
- PostgreSQL
- Versionamento de banco de dados com Liquibase
### web
- HTML5
- CSS3
- Javascript

# 🔖 Instruções

Antes de seguir o passo a passo, você irá precisar ter o Java instalado no seu computador

### Configuração do Banco de Dados

1. Faça o download do PostgreSQL e instale no seu computador</br> <a href='https://www.enterprisedb.com/downloads/postgres-postgresql-downloads'>Download</a>
</br> <b>obs</b>: você precisa instalar na porta padrão que é 5432 e colocar a senha 1234567

2. Crie um banco de dados com o nome: testebackend-db

### Executando a aplicação 

1. Dentro do repositório, faça o download do zip do projeto.

2. Vá até o local onde você baixou o projeto e descompacte o zip

3. Dentro da pasta do projeto entre em backend e copie o caminho da url

</br>
4. Agora abri um terminal cmd e digite o comando: cd + caminhoDaUrl, deve ficar mais ou menos assim: 

- C:\Users\david\Downloads\Teste-Java-Exactaworks-master\Teste-Java-Exactaworks-master\backend

</br>
5. Agora digite o seguinte comando: 

- mvn package

</br>
6. Repare que foi criado uma pasta com o nome target dentro de backend. No cmd digite: 

- cd target

</br>
7. Dentro da pasta target tem um arquivo.jar, vamos precisar dele, portanto digite:

- Java -jar Teste-Java-0.0.1-SNAPSHOT.jar

</br>
8. Pronto! Isso irá levanter o servidor tomcat com nosso banco de dados e a API RESTfull
</br>
9. Agora é só navegar até a raiz do projeto, entre na pasta web, e abri o arquivo index.html.

## Considerações

Neste projeto web não foi abordado responsividade de tela, então é fortemente recomendado que você utilize uma tela igual ou superior a 960px. Também poderia ser utilizado o docker para facilitar a configuração do banco de dados e evitar de ter que baixar manualmente, porem não optei por usar o docker, uma vez que o mesmo tem uma difícil instalação no windows 10 Home.
