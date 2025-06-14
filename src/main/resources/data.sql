DROP TABLE IF EXISTS tb_aluguel_filme;
DROP TABLE IF EXISTS tb_aluguel;
DROP TABLE IF EXISTS tb_cliente;
DROP TABLE IF EXISTS tb_filme;

CREATE TABLE tb_filme (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    diretor VARCHAR(255),
    genero VARCHAR(100),
    ano_lancamento INT,
    quantidade_disponivel INT
);

CREATE TABLE tb_cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE
);

CREATE TABLE tb_aluguel (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    data_aluguel DATE NOT NULL,
    data_devolucao DATE,
    status VARCHAR(30) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES tb_cliente(id)
);

CREATE TABLE tb_aluguel_filme (
    aluguel_id BIGINT,
    filme_id BIGINT,
    PRIMARY KEY (aluguel_id, filme_id),
    FOREIGN KEY (aluguel_id) REFERENCES tb_aluguel(id),
    FOREIGN KEY (filme_id) REFERENCES tb_filme(id)
);

INSERT INTO tb_filme (titulo, diretor, genero, ano_lancamento, quantidade_disponivel) VALUES
('O Poderoso Chefão', 'Francis Ford Coppola', 'Crime', 1972, 3),
('Pulp Fiction', 'Quentin Tarantino', 'Crime', 1994, 2),
('Clube da Luta', 'David Fincher', 'Drama', 1999, 4),
('Matrix', 'Lana Wachowski', 'Ficção Científica', 1999, 1),
('Interestelar', 'Christopher Nolan', 'Ficção Científica', 2014, 3),
('O Senhor dos Anéis: A Sociedade do Anel', 'Peter Jackson', 'Fantasia', 2001, 2),
('O Senhor dos Anéis: As Duas Torres', 'Peter Jackson', 'Fantasia', 2002, 1),
('O Senhor dos Anéis: O Retorno do Rei', 'Peter Jackson', 'Fantasia', 2003, 2),
('Batman: O Cavaleiro das Trevas', 'Christopher Nolan', 'Ação', 2008, 4),
('A Origem', 'Christopher Nolan', 'Ficção Científica', 2010, 3),
('Gladiador', 'Ridley Scott', 'Histórico', 2000, 1),
('Os Infiltrados', 'Martin Scorsese', 'Crime', 2006, 2),
('Django Livre', 'Quentin Tarantino', 'Faroeste', 2012, 4),
('Kill Bill: Vol. 1', 'Quentin Tarantino', 'Ação', 2003, 3),
('Kill Bill: Vol. 2', 'Quentin Tarantino', 'Ação', 2004, 2),
('O Regresso', 'Alejandro G. Iñárritu', 'Drama', 2015, 1),
('La La Land', 'Damien Chazelle', 'Musical', 2016, 2),
('Whiplash', 'Damien Chazelle', 'Drama', 2014, 3),
('O Grande Gatsby', 'Baz Luhrmann', 'Drama', 2013, 1),
('O Lobo de Wall Street', 'Martin Scorsese', 'Comédia', 2013, 4),
('O Irlandês', 'Martin Scorsese', 'Crime', 2019, 2),
('Vingadores: Ultimato', 'Irmãos Russo', 'Ação', 2019, 3),
('Pantera Negra', 'Ryan Coogler', 'Ação', 2018, 1),
('Homem de Ferro', 'Jon Favreau', 'Ação', 2008, 2),
('Capitão América: O Soldado Invernal', 'Irmãos Russo', 'Ação', 2014, 3),
('Thor: Ragnarok', 'Taika Waititi', 'Aventura', 2017, 1),
('Guardiões da Galáxia', 'James Gunn', 'Aventura', 2014, 4),
('Homem-Aranha: No Aranhaverso', 'Bob Persichetti', 'Animação', 2018, 2),
('Toy Story', 'John Lasseter', 'Animação', 1995, 3),
('Up: Altas Aventuras', 'Pete Docter', 'Animação', 2009, 1),
('Wall-E', 'Andrew Stanton', 'Animação', 2008, 2),
('Ratatouille', 'Brad Bird', 'Animação', 2007, 3),
('Divertidamente', 'Pete Docter', 'Animação', 2015, 1),
('Soul', 'Pete Docter', 'Animação', 2020, 2),
('Coco', 'Lee Unkrich', 'Animação', 2017, 4),
('O Rei Leão', 'Roger Allers', 'Animação', 1994, 2),
('Forrest Gump', 'Robert Zemeckis', 'Drama', 1994, 3),
('Titanic', 'James Cameron', 'Romance', 1997, 1),
('Avatar', 'James Cameron', 'Ficção Científica', 2009, 2),
('Coração Valente', 'Mel Gibson', 'Histórico', 1995, 3),
('O Pianista', 'Roman Polanski', 'Drama', 2002, 2),
('O Labirinto do Fauno', 'Guillermo del Toro', 'Fantasia', 2006, 4),
('A Forma da Água', 'Guillermo del Toro', 'Fantasia', 2017, 2),
('O Artista', 'Michel Hazanavicius', 'Drama', 2011, 1),
('Birdman', 'Alejandro G. Iñárritu', 'Drama', 2014, 2),
('Cisne Negro', 'Darren Aronofsky', 'Drama', 2010, 3),
('A Rede Social', 'David Fincher', 'Drama', 2010, 1),
('Jogos Vorazes', 'Gary Ross', 'Ação', 2012, 2),
('Crepúsculo', 'Catherine Hardwicke', 'Romance', 2008, 2);

INSERT INTO tb_cliente (nome, email, cpf) VALUES
('Diego Cutrim', 'diego@email.com', '123.456.789-00'),
('Maria Silva', 'maria@email.com', '321.654.987-00'),
('João Souza', 'joao@email.com', '456.789.123-00'),
('Ana Lima', 'ana@email.com', '987.654.321-00'),
('Pedro Santos', 'pedro@email.com', '741.852.963-00');

INSERT INTO tb_aluguel (cliente_id, data_aluguel, data_devolucao, status) VALUES
(1, '2025-06-01', NULL, 'EM_ANDAMENTO'),
(2, '2025-06-02', '2025-06-05', 'DEVOLVIDO'),
(3, '2025-06-03', NULL, 'EM_ANDAMENTO');

INSERT INTO tb_aluguel_filme (aluguel_id, filme_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 10), (2, 15),
(3, 25), (3, 30), (3, 35);