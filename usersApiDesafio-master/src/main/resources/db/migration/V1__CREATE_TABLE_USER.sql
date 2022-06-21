CREATE TABLE tb_usuario (
   id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   ds_nome varchar(200) not null,
   nm_idade int not null,
   ds_telefone varchar(50) not null,
   ds_email varchar(50) not null,
   ds_senha varchar(200) not null,
   fl_ativo bit not null
)