CREATE TABLE `tb_endereco` (
	`endereco_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cep` VARCHAR(255) NULL DEFAULT NULL,
	`complemento` VARCHAR(255) NULL DEFAULT NULL,
	`logradouro` VARCHAR(255) NULL DEFAULT NULL,
	`municipio` VARCHAR(255) NULL DEFAULT NULL,
	`numero` VARCHAR(255) NULL DEFAULT NULL,
	`uf` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`endereco_id`) USING BTREE
);

CREATE TABLE `tb_telefone` (
	`telefone_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`ddd` INT(11) NULL DEFAULT NULL,
	`numero` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`telefone_id`) USING BTREE
);

CREATE TABLE `tb_usuario` (
	`usuario_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(255) NULL DEFAULT NULL,
	`idade` INT(11) NULL DEFAULT NULL,
	`isactive` BIT(1) NULL DEFAULT NULL,
	`nome` VARCHAR(255) NULL DEFAULT NULL,
	`senha` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`usuario_id`) USING BTREE
);


CREATE TABLE `tb_usuario_enderecos` (
	`usuario_usuario_id` BIGINT(20) NOT NULL,
	`enderecos_endereco_id` BIGINT(20) NOT NULL,
	UNIQUE INDEX `UK_tcgunwel3fb2u9k9d1mj4cbfy` (`enderecos_endereco_id`) USING BTREE,
	INDEX `FK26bpn29pet8wh0igfcxl86fa4` (`usuario_usuario_id`) USING BTREE,
	CONSTRAINT `FK26bpn29pet8wh0igfcxl86fa4` FOREIGN KEY (`usuario_usuario_id`) REFERENCES `apiclientes`.`tb_usuario` (`usuario_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK4cksr5x7akvff55h2hw608d4e` FOREIGN KEY (`enderecos_endereco_id`) REFERENCES `apiclientes`.`tb_endereco` (`endereco_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);


CREATE TABLE `tb_usuario_telefones` (
	`usuario_usuario_id` BIGINT(20) NOT NULL,
	`telefones_telefone_id` BIGINT(20) NOT NULL,
	UNIQUE INDEX `UK_5qx9vm4cy0oi2yybbosi3or44` (`telefones_telefone_id`) USING BTREE,
	INDEX `FKlyj5a7t3ad9aqhkf1boklgvas` (`usuario_usuario_id`) USING BTREE,
	CONSTRAINT `FKlyj5a7t3ad9aqhkf1boklgvas` FOREIGN KEY (`usuario_usuario_id`) REFERENCES `apiclientes`.`tb_usuario` (`usuario_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FKsx9jndc5vlhwl3kcn3px9ik1u` FOREIGN KEY (`telefones_telefone_id`) REFERENCES `apiclientes`.`tb_telefone` (`telefone_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
);
