alter table pedido
modify column data_confirmacao TIMESTAMP  null;

alter table pedido
modify column data_cancelamento TIMESTAMP  null;

alter table pedido
modify column data_entrega TIMESTAMP  null;