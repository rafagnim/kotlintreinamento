CREATE TABLE tb_endereco (
   id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   ds_rua varchar(200) not null,
   nm_numero int not null,
   ds_cidade varchar(100) not null,
   ds_estado varchar(100) not null,
   ds_senha varchar(200) not null,
   fl_ativo bit not null,
   user_id int not null,
   FOREIGN KEY (user_id) REFERENCES tb_usuario(id)
)