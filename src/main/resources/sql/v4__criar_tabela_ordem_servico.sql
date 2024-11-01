CREATE TABLE chamado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    observacoes TEXT,
    prioridade INT NOT NULL,
    status INT NOT NULL,
    tecnico_id BIGINT NOT NULL,
    cliente_id BIGINT NOT NULL,
    data_abertura DATE NOT NULL,
    data_fechamento DATE,
    CONSTRAINT fk_chamado_tecnico FOREIGN KEY (tecnico_id) REFERENCES tecnico (id),
    CONSTRAINT fk_chamado_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);