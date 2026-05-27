CREATE TABLE tb_movimentacao_saldo(
movimentacao_id BIGSERIAL PRIMARY KEY,
prestador_id BIGINT NOT NULL REFERENCES tb_prestador(prestador_id),
contratacao_id BIGINT REFERENCES tb_contratacao(contratacao_id),
tipo_credito TEXT NOT NULL CHECK(tipo_credito IN('CREDITO', 'DEBITO')),
valor DECIMAL NOT NULL,
descricao TEXT NOT NULL,
id_evento_gateway TEXT UNIQUE,
criado_em TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE tb_saque(
saque_id BIGSERIAL PRIMARY KEY,
prestador_id BIGINT NOT NULL REFERENCES tb_prestador(prestador_id),
valor DECIMAL NOT NULL,
chave_pix TEXT NOT NULL,
status TEXT NOT NULL CHECK(status IN('PROCESSADO', 'PENDENTE', 'FALHOU')),
solicitado_em TIMESTAMP NOT NULL DEFAULT now(),
processado_em TIMESTAMP,
erro_mensagem TEXT
);