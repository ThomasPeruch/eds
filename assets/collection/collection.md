<h3>Collection</h3>

Para importar a collection no seu postman copie todo o json abaixo e siga os seguintes passos: 
- Abra o postman
- File > Import
- Clique em Raw Text
- Cole o código abaixo
- Clique em Import<br>


Json da collection:

````
{
	"info": {
		"_postman_id": "5718cbdc-b3df-4129-9fe7-15f1d0117045",
		"name": "EDS",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "Create Vehicle",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"veiculo\":\"A4\",\n    \"marca\":\"Audi\",\n    \"ano\":2019,\n    \"descricao\":\"perfeito para vida cotidiana, vida noturna e vida tranquila no interior ou ate aventuras radicais\",\n    \"chassi\":\"ssss\",\n    \"preco\":56000\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/veiculos",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos"
					]
				}
			},
			"response": []
		},
		{
			"name": "Update Vehicle",
			"request": {
				"method": "PUT",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\n    \"id\": 1,\n    \"veiculo\": \"Fiesta\",\n    \"marca\": \"Ford\",\n    \"ano\": 2010,\n    \"descricao\": \"Ford Fiesta ano 2010\",\n    \"vendido\": true,\n    \"chassi\": \"FF110 2010 C\",\n    \"preco\": 29000.00\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/veiculos/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete Vehicle",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/veiculos/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Find Vehicles by filters",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/veiculos/search?value=2020&field=year&operator=<=",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"search"
					],
					"query": [
						{
							"key": "page",
							"value": "0",
							"disabled": true
						},
						{
							"key": "linesPerPage",
							"value": "10",
							"disabled": true
						},
						{
							"key": "value",
							"value": "2020"
						},
						{
							"key": "field",
							"value": "year"
						},
						{
							"key": "operator",
							"value": "<="
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Find Vehicles",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/veiculos",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos"
					],
					"query": [
						{
							"key": "page",
							"value": "0",
							"disabled": true
						},
						{
							"key": "linesPerPage",
							"value": "10",
							"disabled": true
						},
						{
							"key": "value",
							"value": "2020",
							"disabled": true
						},
						{
							"key": "field",
							"value": "year",
							"disabled": true
						},
						{
							"key": "operator",
							"value": "<=",
							"disabled": true
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Find Vehicle By Id",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/veiculos/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"veiculos",
						"1"
					],
					"query": [
						{
							"key": "page",
							"value": "0",
							"disabled": true
						},
						{
							"key": "linesPerPage",
							"value": "10",
							"disabled": true
						}
					]
				}
			},
			"response": []
		}
	]
}
````
