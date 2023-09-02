CREATE TABLE if not exists public.veiculos(
	id bigserial not null,
	veiculo VARCHAR(80) not null,
	marca VARCHAR(30) not null,
	ano int not null,
    descricao VARCHAR(200) not null,
    vendido boolean,
    created timestamp not null,
    updated timestamp,
    chassi VARCHAR(80) not null,
    preco money not null,
	primary key (id)
);