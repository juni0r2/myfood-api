
create table cidade (
    id bigint not null auto_increment,
    nome_cidade varchar(255),
    nome_estado varchar(80),
    primary key (id)
) engine=InnoDB default charset=utf8;