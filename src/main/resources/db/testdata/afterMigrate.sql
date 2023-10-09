set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert ignore into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert ignore into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert ignore into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert ignore into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert ignore into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into grupo (nome) values ('FUNCIONARIO'),('GERENTE'),('DIRETOR');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into grupo_permissao (grupo_id, permissao_id) values (1,1), (2,1), (2,2), (3,1), (3,2);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 0, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into usuario (nome, email, senha, data_cadastro) values ('Israel Alencar', 'israel@teste.com', '123', utc_timestamp);
insert into usuario (nome, email, senha, data_cadastro) values ('Diego Dionisio', 'diego@teste.com', '123', utc_timestamp);
insert into usuario (nome, email, senha, data_cadastro) values ('Adriano Ardaia', 'adriano@teste.com', '123', utc_timestamp);
insert into usuario (nome, email, senha, data_cadastro) values ('Lucas Alencar', 'lucas@teste.com', '123', utc_timestamp);

insert into usuario_grupo (usuario_id, grupo_id) values (1,1),(1,2),(1,3),(2,1),(2,3),(3,2),(3,3);

insert into restaurante_usuario_responsavel (usuario_id, restaurante_id) values (1,1), (1,5), (2,2),(3,2), (3,3), (4,1), (4,6);

insert into pedido (id, codigo, sub_total, taxa_frete, valor_total, data_criacao, status, restaurante_id, usuario_cliente_id,
    forma_pagamento_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_logradouro, endereco_numero)
values (1, 'fcbbe016-532c-11ee-8ea2-00090ffe0001',47.8, 7.8, 55.6, '2023-10-16 12:00:30', 'CRIADO', 1,1,1,1, 'Monte Castelo', '79010170', 'Rachid Neder', '16');

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 7, 2, 23.9, 47.8, 'Bem passado');

insert into pedido (id, codigo, sub_total, taxa_frete, valor_total, data_criacao, status, restaurante_id, usuario_cliente_id,
    forma_pagamento_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_logradouro, endereco_numero)
values (2, '89969a33-92e4-41ea-8f58-fb579b6e88e8', 67.6, 9.18, 76.78, '2023-10-26 02:00:30', 'CONFIRMADO', 2,3,2,2, 'Santa Mônica', '79106600', 'Sonora', '54');

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total)
values (2, 2, 9, 4, 16.9, 67.6);

insert into pedido (id, codigo, sub_total, taxa_frete, valor_total, data_criacao, status, restaurante_id, usuario_cliente_id,
    forma_pagamento_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_logradouro, endereco_numero)
values (3, '85687a62-30ee-4eaf-8671-7857351d9abd', 146.4, 15.9, 162.3, '2023-10-26 02:00:30', 'ENTREGUE', 1,1,1,1, 'Coophatrabalho', '7910000', 'Ibirapua', '1000');

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total)
values (6, 3, 9, 3, 48.8, 146.4);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, sub_total, taxa_frete, valor_total)
values (4, 'b8f1abe6-e14b-4700-8850-00e0d823db2a', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CONFIRMADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 4, 2, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 3, 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, sub_total, taxa_frete, valor_total)
values (5, '84e2e9f7-429c-4a8e-9107-2ce2525d7316', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CONFIRMADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 5, 6, 1, 79, 79, 'Ao ponto');





