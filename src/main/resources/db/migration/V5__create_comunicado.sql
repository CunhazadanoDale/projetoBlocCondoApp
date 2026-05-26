CREATE TABLE tb_comunicado(
comunicado_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
condominio_id BIGINT NOT NULL REFERENCES tb_condominio(condominio_id),
titulo VARCHAR(60) NOT NULL,
conteudo VARCHAR(255) NOT NULL,
escopo_tipo VARCHAR(20) NOT NULL CHECK(escopo_tipo IN('TODOS', 'BLOCO', 'UNIDADE')),
escopo_valor TEXT,
expira_em TIMESTAMP,
ativo boolean NOT NULL,
criado_em TIMESTAMP NOT NULL DEFAULT now()
);