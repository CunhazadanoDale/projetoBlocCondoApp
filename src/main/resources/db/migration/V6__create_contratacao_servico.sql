CREATE TABLE tb_servico_oferecido(
servico_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
prestador_id BIGINT NOT NULL REFERENCES tb_prestador(prestador_id),
categoria_servico TEXT NOT NULL CHECK(categoria_servico IN('ELETRICA', 'HIDRAULICA', 'LIMPEZA', 'PINTURA', 'JARDIM', 'OUTRO')),
titulo VARCHAR(100) NOT NULL,
descricao TEXT NOT NULL,
valor_hora DECIMAL,
valor_fixo DECIMAL,
ativo boolean NOT NULL
);

CREATE TABLE tb_contratacao(
contratacao_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
morador_id BIGINT NOT NULL REFERENCES tb_morador(morador_id),
prestador_id BIGINT NOT NULL REFERENCES tb_prestador(prestador_id),
servico_id BIGINT NOT NULL REFERENCES tb_servico_oferecido(servico_id),
valor_combinado DECIMAL NOT NULL,
descricao TEXT NOT NULL,
status_contrato TEXT NOT NULL CHECK(status_contrato IN('ACEITO', 'AGUARDANDO', 'RECUSADO', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO')),
data_solicitada TIMESTAMP,
criado_em TIMESTAMP NOT NULL DEFAULT now(),
concluido_em TIMESTAMP
);