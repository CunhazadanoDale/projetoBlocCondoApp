CREATE TABLE tb_morador(
morador_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
nome_completo VARCHAR(60) NOT NULL,
conta_id BIGINT UNIQUE REFERENCES tb_conta,
condominio_id BIGINT NOT NULL REFERENCES tb_condominio,
unidade TEXT,
bloco TEXT,
telefone VARCHAR(255),
criado_em TIMESTAMP DEFAULT now()
);

CREATE TABLE tb_prestador(
prestador_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
conta_id BIGINT NOT NULL UNIQUE REFERENCES tb_conta,
nome_completo VARCHAR(60) NOT NULL,
cpf_ou_cnpj VARCHAR(20) NOT NULL UNIQUE,
telefone VARCHAR(255),
descricao TEXT,
saldo DECIMAL DEFAULT 0,
status VARCHAR(30) NOT NULL CHECK (status IN('APROVADO', 'REPROVADO', 'PENDENTE')),
avaliacao_media DECIMAL,
total_avaliacoes INTEGER,
criado_em TIMESTAMP DEFAULT now()
);