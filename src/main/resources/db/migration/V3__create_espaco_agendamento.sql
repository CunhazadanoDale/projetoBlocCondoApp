CREATE TABLE tb_espaco (
espaco_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
condominio_id BIGINT NOT NULL REFERENCES tb_condominio,
nome VARCHAR(60) NOT NULL,
descricao TEXT,
capacidade INTEGER,
antecedencia_min_horas INTEGER,
cancelamento_min_horas INTEGER,
limite_reserva_semana INTEGER,
ativo boolean NOT NULL DEFAULT true,
version BIGINT NOT NULL DEFAULT 0
)