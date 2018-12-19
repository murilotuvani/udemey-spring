-- drop database udemyspring;
-- create database udemyspring;
-- create user udemyspring@localhost identified by 'udemyspring';
-- grant all on udemyspring.* to udemyspring@localhost;
use udemyspring;
create table empresa (
id bigint(20) auto_increment,
cnpj varchar(255) not null,
data_atualizacao  datetime not null,
data_criacao datetime not null,
razao_social varchar(255) not null,
primary key (id),
unique key un_empr_cnpj (cnpj)
) engine=InnoDB default charset=utf8;

CREATE TABLE `funcionario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) COLLATE utf8_bin NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `nome` varchar(255) COLLATE utf8_bin NOT NULL,
  `perfil` varchar(255) COLLATE utf8_bin NOT NULL,
  `qtd_horas_almoco` float DEFAULT NULL,
  `qtd_horas_trabalho_dia` float DEFAULT NULL,
  `senha` varchar(255) COLLATE utf8_bin NOT NULL,
  `valor_hora` decimal(19,2) DEFAULT NULL,
  `empresa_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_func_empr` (`empresa_id`),
  CONSTRAINT `fk_func_empr` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`)
) engine=InnoDB default charset=utf8;

CREATE TABLE `lancamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `data_atualizacao` datetime NOT NULL,
  `data_criacao` datetime NOT NULL,
  `descricao` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `localizacao` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `tipo` varchar(255) COLLATE utf8_bin NOT NULL,
  `funcionario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lanc_func` (`funcionario_id`),
  CONSTRAINT `fk_lanc_func` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

unlock tables;