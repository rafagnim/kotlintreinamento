CREATE TABLE `tb_cep` (
	`logradouro` VARCHAR(255) NOT NULL,
	`municipio` VARCHAR(255) NOT NULL,
	`uf` VARCHAR(255) NOT NULL,
	`cep` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`logradouro`, `municipio`, `uf`) USING BTREE
);