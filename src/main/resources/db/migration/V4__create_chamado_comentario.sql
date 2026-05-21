CREATE TABLE tb_chamado(
chamado_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
condominio_id BIGINT NOT NULL REFERENCES tb_condominio,
morador_id BIGINT REFERENCES tb_morador,
nome_requerente VARCHAR(60) NOT NULL,
unidade_requerente VARCHAR(60) NOT NULL,
titulo VARCHAR(20) NOT NULL,
descricao TEXT NOT NULL,
categoria_chamado VARCHAR(30) NOT NULL CHECK(categoria_chamado IN('MANUTENCAO', 'LIMPEZA', 'SEGURANCA', 'BARULHO', 'OUTRO')),
urgencia_chamado VARCHAR(30) NOT NULL CHECK(urgencia_chamado IN('ALTA', 'MEDIA', 'BAIXA')),
status_chamado VARCHAR(30) NOT NULL CHECK(status_chamado IN('ABERTO', 'EM_ANDAMENTO', 'RESOLVIDO', 'FECHADO')),
criado_em TIMESTAMP NOT NULL DEFAULT now(),
resolvido_em TIMESTAMP
)