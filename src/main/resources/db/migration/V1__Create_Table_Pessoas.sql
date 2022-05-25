CREATE TABLE `tb_pessoas` (
  `id_pessoa` bigint NOT NULL AUTO_INCREMENT,
  `email_pessoa` varchar(255) DEFAULT NULL,
  `nome_pessoa` varchar(255) DEFAULT NULL,
  `nomesocial_pessoa` varchar(255) DEFAULT NULL,
  `sobrenome_pessoa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_pessoa`)
)