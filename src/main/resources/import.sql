INSERT INTO tb_cliente (cpf, nome, data_de_nascimento) VALUES ('06937223129', 'Gabriel', '2003-05-06');

INSERT INTO tb_endereco (cidade, uf, cep, numero_residencia, cpf_cliente) VALUES ('Brasília', 'DF', '71687-758', 42, '06937223129');

INSERT INTO tb_contatos (telefone, email, cpf_cliente) VALUES ('61981912241', 'gabriel@gmail.com', '06937223129');

INSERT INTO tb_livro (isbn, titulo, autor, editora, exemplares) VALUES ('978-3-16-148410-0', 'O Alquimista', 'Paulo Coelho', 'HarperCollins', 5);

INSERT INTO tb_emprestimo(data_saida, data_devolucao_prevista, cpf_cliente) VALUES ('2024-10-27', '2024-11-10', '06937223129');

INSERT INTO tb_emprestimo_livro(emprestimo_cod, livro_isbn) VALUES (1, '978-3-16-148410-0');