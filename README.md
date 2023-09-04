<h2> Desafio Backend</h2>
Desenvolver uma API JSON RESTful em Java, utilizando os frameworks Quarkus ou Spring boot, com os métodos (GET, POST, PUT, DELETE). Salvar dados em um banco de dados MySQL, utilizando JPA.

<br>Especificação<br>
Foi montada uma base de veículo seguindo a seguinte estrutura:
````
veiculo:   string
marca:     string
ano:       integer
descricao: String
vendido:   boolean
created:   ??? (Escolha livre)
updated:   ??? (Escolha livre)
chassi:     String
preco:     ??? (Escolha livre)
````
Banco escolhido: PostgreSQL

<h4>Os seguintes endpointes foram desenvolvidos:</h4>
  
```GET /veiculos```
<br><br>Retorna todos os veículos paginados, cada página por padrão tem 5 itens
Este endpoint funciona da seguinte forma:
Dois parâmetros devem ser passados como query param e são eles:<br>
- <i>page</i>: Representa a pagina <br>
- <i>linesPerPage</i>: Número de itens que aparecerão por página<br>
<br> ![buscaPaginada](https://github.com/ThomasPeruch/eds/blob/master/assets/img/buscaPaginada.png)
<hr>

```GET /veiculos/search```

Tambem paginado como o endpoint acima, retorna os veículos de acordo com filtros passados através de query string.<br><br>
Este endpoint funciona da seguinte forma:
Três parâmetros devem ser passados como query param e são eles:<br>
- <i>field</i>: Representa qual campo será usado na busca filtrada<br>
- <i>operator</i>: Representa qual comparação será feita, igual, maior ...<br>
- <i>value</i>: Represntar qual valor sera buscado no filtro.<br>

Exemplos:<br>
Caso deseje buscar veiculos somente da marca BMW<br/>
![filtroPorMarca](https://github.com/ThomasPeruch/eds/blob/master/assets/img/filtroPorMarca.png)

Ou deseje buscar por veículos com o preço minimo de R$50000<br/>
![filtroPorPrecoMinimo](https://github.com/ThomasPeruch/eds/blob/master/assets/img/filtroPorPrecoMinimo.png)

No enunciado original esse endpoint(rota) deveria ser igual ao de cima ```GET /veiculos``` somente com diferentes parametros como query string.<br> 
No entanto não foi possível seguir com o enunciado original, pois o Spring não permite dois endpoints iguais em nomenclatura e metodo HTTP.<br>
Por isso esse endpoint foi modificado para <i>/search</i>
<br>Segue print do erro: <br>
![ambiguos mapping controller](https://github.com/ThomasPeruch/eds/assets/60239342/8544980f-a48d-458e-b708-568c113b3cce)

<hr>

```GET /veiculos/{id}```

Retorna os detalhes do veículo
<hr>

```POST /veiculos```

Adiciona um novo veículo
<hr>

```PUT /veiculos/{id}```

Atualiza os dados de um veículo
<hr>

```DELETE /veiculos/{id}```

Apaga o veículo

<h3>Tecnologias Utilizadas</h3>

- Java 17
- Spring 3
- Springdoc open api integrado com interface do Swagger UI
- PostgreSQL(via docker compose)
- Maven
- Docker

<h2>Instruções</h2>

Para rodar aplicação certifique de ter os seguintes requisitos atentidos:<br>
- Possuir java 17<br>
- Possuir Docker e docker compose<br>
- E ter as seguintes portas disponiveis no seu computador: 8080 para a aplicação e 5432 para o banco de dados postgres.

Executar o comando <i>docker-compose up -d</i> na pasta raíz do projeto, assim será criado um container com especificações descritas no arquivo docker-compose.yml. Nesse caso é subir uma instância do banco PostgreSQL.

<hr>

Link do swagger do projeto http://127.0.0.1:8080/swagger-ui.html , somente acessível quando a aplicação já estiver rodando.

<hr>

Para importar a collection e interagir com a aplicação clique [aqui](https://github.com/ThomasPeruch/eds/blob/master/assets/collection/collection.md) e siga as instruçoes. 

<hr>

Credencias de acesso do banco de dados PostgreSQL do projeto:<br>
url: <i>jdbc:postgresql://localhost:5432/eds</i><br>
usuario: <i>postgres</i><br>
senha: <i>eds</i><br><br>
Obs: essas mesmas credencias podem ser encontradas no arquivo [application.properties](https://github.com/ThomasPeruch/eds/blob/master/src/main/resources/application.properties) da aplicação
