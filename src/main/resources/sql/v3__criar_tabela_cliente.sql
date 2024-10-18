CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    cpf VARCHAR(11),
    data_criacao DATE
);
