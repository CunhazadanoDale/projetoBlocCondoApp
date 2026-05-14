CREATE TABLE tb_condominio (
condominio_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
nome_condominio VARCHAR(60) NOT NULL,
cnpj_condominio VARCHAR(14) NOT NULL UNIQUE,
cep TEXT, nome_rua TEXT, bairro TEXT, cidade TEXT, estado TEXT, numero TEXT, complemento TEXT,
telefone_condominio TEXT,
email_sindico TEXT,
senha_atual TEXT,
senha_expira_em TIMESTAMP,
ativo BOOLEAN,
criado_em TIMESTAMP DEFAULT now()
);


CREATE TABLE tb_conta(
conta_id BIGSERIAL PRIMARY KEY,
uuid UUID NOT NULL UNIQUE,
email TEXT NOT NULL UNIQUE,
senha_hash TEXT NOT NULL,
role VARCHAR(20) NOT NULL CHECK (role IN ('MORADOR', 'PRESTADOR')),
ativo BOOLEAN,
criado_em TIMESTAMP DEFAULT now()
);