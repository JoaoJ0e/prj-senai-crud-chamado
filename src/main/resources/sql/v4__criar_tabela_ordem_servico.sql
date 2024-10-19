CREATE TABLE ordem_servico (
    id INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,
    tecnico_id BIGINT NOT NULL,
    cliente_id BIGINT NOT NULL,
    valor NUMERIC(10, 2),
    data_criacao DATE NOT NULL,
    CONSTRAINT fk_ordem_servico_tecnico FOREIGN KEY (tecnico_id) REFERENCES tecnico (id),
    CONSTRAINT fk_ordem_servico_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);
