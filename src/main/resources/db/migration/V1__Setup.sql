-- drop database udemyspring;
-- create database udemyspring;
-- create user udemyspring@localhost identified by 'udemyspring';
use udemyspring;
create table empresa (
id bigint(20) auto_increment,
cnpj varchar(255) not null,
data_atualizacao  datetime not null,
data_criacao datetime not null,
razao_social varchar(255) not null,
primary key (id)
) engine=InnoDB default charset=utf8;

unlock tables;