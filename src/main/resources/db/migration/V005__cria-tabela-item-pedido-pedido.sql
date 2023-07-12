create table pedido (
	id bigint not null auto_increment,
	sub_total DECIMAL(10,2) not null,
	taxa_frete DECIMAL(10,2) not null,
	valor_total DECIMAL(10,2) not null,
	data_criacao TIMESTAMP not null,
	data_confirmacao TIMESTAMP not null,
	data_cancelamento TIMESTAMP not null,
	data_entrega TIMESTAMP not null,
	status VARCHAR(10) not null,

	restaurante_id bigint not null,
	usuario_cliente_id bigint not null,
	forma_pagamento_id bigint not null,


	endereco_cidade_id bigint(20) not null,
  	endereco_cep varchar(9) not null,
  	endereco_logradouro varchar(100) not null,
  	endereco_numero varchar(20) not null,
  	endereco_complemento varchar(60) null,
  	endereco_bairro varchar(60) not null,

  	primary key (id),

  	constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id),
  	constraint fk_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
  	constraint fk_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id)

) engine=InnoDB default charset=utf8;

create table item_pedido (
	id bigint not null auto_increment,
	quantidade smallint(6) not null,
	preco_unitario DECIMAL(10,2) not null,
	preco_total DECIMAL(10,2) not null,
	observacao VARCHAR(255),
	produto_id bigint not null,
	pedido_id bigint not null,

	primary key (id),
	unique key uk_item_pedido_produto (pedido_id, produto_id),

  constraint fk_item_pedido_pedido foreign key (pedido_id) references pedido (id),
  constraint fk_item_pedido_produto foreign key (produto_id) references produto (id)

) engine=InnoDB default charset=utf8;