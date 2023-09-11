alter table pedido
modify column data_confirmacao TIMESTAMP  not null;

alter table pedido
modify column data_cancelamento TIMESTAMP  not null;

alter table pedido
modify column data_entrega TIMESTAMP  not null;