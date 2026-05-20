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
);

CREATE TABLE tb_agendamento(
agendamento_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
espaco_id BIGINT NOT NULL REFERENCES tb_espaco,
morador_id BIGINT REFERENCES tb_morador,
nome_responsavel VARCHAR(60) NOT NULL,
unidade_responsavel VARCHAR(60) NOT NULL,
inicio TIMESTAMP NOT NULL,
fim TIMESTAMP NOT NULL,
status VARCHAR(30) NOT NULL CHECK(status IN('PENDENTE', 'CONFIRMADO', 'CANCELADO', 'EXPIRADO')),
observacao TEXT,
criado_em TIMESTAMP NOT NULL DEFAULT now()
);