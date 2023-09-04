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
<hr>

```GET /veiculos/search```

Retorna os veículos de acordo com filtros passados através de query string.<br>
No enunciado original esse endpoint(rota) deveria ser igual ao de cima ```GET /veiculos``` somente com diferentes parametros como query string.<br> 
No entanto não foi possível seguir com o enunciado original, pois o Spring não permite dois endpoints iguais em nomenclatura e metodo HTTP.<br>
Por isso esse endpoint foi modificado para <i>/search</i>
<br>Segue print do erro: 
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
