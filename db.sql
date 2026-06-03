CREATE TABLE IF NOT EXISTS categorias (
  id serial PRIMARY KEY,
  nombre TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
  id serial PRIMARY KEY,
  nombre TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
  id serial PRIMARY KEY,
  correo TEXT UNIQUE NOT NULL,
  username TEXT UNIQUE NOT NULL,
  contrasena TEXT NOT NULL,
  pfp TEXT DEFAULT 'default.png',
  sobre TEXT,
  fk_rol INTEGER,
  FOREIGN KEY(fk_rol) REFERENCES roles(id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS libros (
  id serial PRIMARY KEY,
  portada TEXT NOT NULL,
  precio float NOT NULL CHECK(precio >= 0),
  stock INT NOT NULL CHECK(stock >= 0),
  titulo TEXT UNIQUE NOT NULL,
  descripcion TEXT NOT NULL,
  contenido TEXT NOT NULL,
  paginas INT NOT NULL CHECK(paginas > 0),
  clasificacion float DEFAULT 0 CHECK(clasificacion >= 0 AND clasificacion <= 10),
  fk_categoria INT,

  FOREIGN KEY(fk_categoria) REFERENCES categorias(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ventas (
  id serial PRIMARY KEY,
  fk_libro INT,
  fk_usuario INT,
  cantidad INT NOT NULL CHECK(cantidad >= 0),
  total float NOT NULL CHECK(total >= 0),
  metodoPago TEXT NOT NULL,
  estado TEXT NOT NULL,
  fecha DATE DEFAULT CURRENT_DATE,
  FOREIGN KEY(fk_libro) REFERENCES libros(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,
  FOREIGN KEY(fk_usuario) REFERENCES usuarios(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS comentarios (
  id serial PRIMARY KEY,
  clasificacion INT CHECK(clasificacion >= 0 AND clasificacion <= 5),
  contenido TEXT,
  fk_autor INT,
  fk_libro INT,
  FOREIGN KEY(fk_autor) REFERENCES usuarios(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,

  FOREIGN KEY(fk_libro) REFERENCES libros(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS historialLectoras (
  fk_usuario INT,
  fk_libro INT,
  FOREIGN KEY(fk_usuario) REFERENCES usuarios(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,

  FOREIGN KEY(fk_libro) REFERENCES libros(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS clasificaciones (
  fk_usuario INT,
  fk_libro INT,
  clasificacion INT CHECK(clasificacion >= 0 AND clasificacion <= 5),
  FOREIGN KEY(fk_usuario) REFERENCES usuarios(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,

  FOREIGN KEY(fk_libro) REFERENCES libros(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

INSERT INTO categorias (nombre) VALUES
('Ficción'),
('Fantasía'),
('Ciencia Ficción'),
('Terror'),
('Romance'),
('Misterio'),
('Historia'),
('Biografías'),
('Autoayuda'),
('Ciencia');

INSERT INTO roles (nombre) VALUES
('usuario'),
('empleado');

INSERT INTO usuarios (correo, username, contrasena, fk_rol, sobre) VALUES
-- Empleado (ID 1)
-- hash_password_emp
('empleado1@tienda.com', 'Empleado_juan69', '$2a$10$eYftbUcd4NcTtwB1Fi5czejmHoF5v8wY.tHHyoJ/NyB6Ih/msVgRm'
, 2, 'Encargado de inventario y moderación.'),
-- Usuarios comunes (IDs 2 al 6)
-- hash_pass_1
('usuario1@correo.com', 'Carlos_99', '$2a$10$UIlllY0W7574ZC8TDY0jXu5TF.WiSdKGftwyB426bk6O.Qiby5qXG', 1, 'Amante de la lectura de terror.'),
('usuario2@correo.com', 'Ana_libros69', '$2a$10$UIlllY0W7574ZC8TDY0jXu5TF.WiSdKGftwyB426bk6O.Qiby5qXG', 1, 'Lectura ligera y romance.'),
('usuario3@correo.com', 'Luis_lector69', '$2a$10$UIlllY0W7574ZC8TDY0jXu5TF.WiSdKGftwyB426bk6O.Qiby5qXG', 1, 'Estudiante de historia.'),
('usuario4@correo.com', 'Sofia_g69', '$2a$10$UIlllY0W7574ZC8TDY0jXu5TF.WiSdKGftwyB426bk6O.Qiby5qXG', 1, 'Fan de la ciencia ficción.'),
('usuario5@correo.com', 'Diego_m69', '$2a$10$UIlllY0W7574ZC8TDY0jXu5TF.WiSdKGftwyB426bk6O.Qiby5qXG', 1, 'Leyendo un poco de todo.');

INSERT INTO libros (portada, precio, stock, titulo, descripcion, contenido, paginas, clasificacion, fk_categoria) VALUES
('img.jpg', 15.99, 10, 'El misterio de la cripta', 'Un libro lleno de suspenso.', 'Contenido del libro...', 320, 8.5, 6),
('img.jpg', 12.50, 5, 'Cazadores de estrellas', 'Aventura en el espacio exterior.', 'Contenido del libro...', 400, 9.0, 3),
('img.jpg', 18.00, 15, 'Amor en tiempos de lluvia', 'Una historia romántica ideal.', 'Contenido del libro...', 250, 7.5, 5),
('img.jpg', 22.00, 8, 'La caída del imperio', 'Análisis histórico profundo.', 'Contenido del libro...', 550, 9.2, 7),
('img.jpg', 9.99, 20, 'Tu mente, tu poder', 'Consejos de superación personal.', 'Contenido del libro...', 180, 6.8, 9),
('img.jpg', 14.25, 12, 'El último alquimista', 'Fantasía oscura medieval.', 'Contenido del libro...', 380, 8.0, 2),
('img.jpg', 16.50, 7, 'Sombras en la noche', 'Relatos cortos de terror.', 'Contenido del libro...', 210, 8.3, 4),
('img.jpg', 25.00, 4, 'La vida de un genio', 'Biografía autorizada.', 'Contenido del libro...', 600, 9.5, 8),
('img.jpg', 11.00, 14, 'El origen de las especies de bolsillo', 'Divulgación científica fácil.', 'Contenido del libro...', 150, 7.9, 10),
('img.jpg', 13.99, 9, 'Crónicas del mañana', 'Antología de ciencia ficción.', 'Contenido del libro...', 290, 8.1, 3),
('img.jpg', 17.30, 11, 'Secretos del pasado', 'Novela de ficción histórica.', 'Contenido del libro...', 430, 8.7, 1),
('img.jpg', 19.99, 6, 'El conjuro del bosque', 'Fantasía para jóvenes adultos.', 'Contenido del libro...', 340, 7.2, 2),
('img.jpg', 8.50, 25, 'Pasos hacia el éxito', 'Guía práctica diaria.', 'Contenido del libro...', 120, 6.0, 9),
('img.jpg', 15.00, 13, 'Susurros en el ático', 'Novela de misterio psicológico.', 'Contenido del libro...', 280, 8.4, 6),
('img.jpg', 21.99, 3, 'Guerra y paz en la era moderna', 'Ensayo histórico contemporáneo.', 'Contenido del libro...', 500, 9.0, 7),
('img.jpg', 10.50, 18, 'Poemas de un invierno', 'Ficción poética contemporánea.', 'Contenido del libro...', 100, 7.0, 1),
('img.jpg', 16.00, 10, 'La paradoja del tiempo', 'Física explicada de forma simple.', 'Contenido del libro...', 220, 8.9, 10),
('img.jpg', 14.99, 14, 'El pacto silencioso', 'Thriller policíaco atrapante.', 'Contenido del libro...', 360, 8.6, 6),
('img.jpg', 12.99, 16, 'Bajo el mismo cielo', 'Drama y romance juvenil.', 'Contenido del libro...', 310, 7.8, 5),
('img.jpg', 18.50, 5, 'El despertar de los muertos', 'Novela de terror zombie.', 'Contenido del libro...', 330, 8.2, 4);

INSERT INTO comentarios (clasificacion, contenido, fk_autor, fk_libro) VALUES
-- Usuario 2 (5 comentarios)
(4, 'Excelente misterio, me atrapó de principio a fin.', 2, 1),
(5, 'Increíbles paisajes espaciales, muy recomendado.', 2, 2),
(3, 'Un romance un poco predecible pero bonito.', 2, 3),
(4, 'Muy buen rigor histórico, excelente autor.', 2, 4),
(2, 'No soy muy fan de la autoayuda, estuvo regular.', 2, 5),
-- Usuario 3 (5 comentarios)
(5, 'La mejor novela histórica que he leído este año.', 3, 4),
(4, 'Un libro de fantasía muy sólido y oscuro.', 3, 6),
(5, 'Me dio escalofríos por las noches.', 3, 7),
(4, 'Una biografía muy completa e inspiradora.', 3, 8),
(3, 'Ciencia compleja pero bien explicada.', 3, 9),
-- Usuario 4 (5 comentarios)
(5, 'Ciencia ficción de la buena, excelente antología.', 4, 10),
(4, 'Gran ritmo narrativo en esta ficción histórica.', 4, 11),
(3, 'Fantasía entretenida para pasar el rato.', 4, 12),
(2, 'Demasiado repetitivo para mi gusto.', 4, 13),
(4, 'El giro del final no me lo esperaba para nada.', 4, 14),
-- Usuario 5 (5 comentarios)
(5, 'Un ensayo brillante sobre la sociedad actual.', 5, 15),
(3, 'Poemas bonitos, aunque algunos muy cortos.', 5, 16),
(5, 'Explica la física cuántica de forma magistral.', 5, 17),
(4, 'Un thriller policial que te mantiene pegado.', 5, 18),
(4, 'Hermosa historia de amor, lloré un poco.', 5, 19),
-- Usuario 6 (5 comentarios)
(4, 'Terror puro y duro, los zombies dan miedo en serio.', 6, 20),
(4, 'Buen inicio de saga, esperando el siguiente.', 6, 1),
(5, 'Una obra de arte de la ciencia ficción.', 6, 2),
(3, 'Romance empalagoso pero se deja leer.', 6, 3),
(5, 'Datos históricos fascinantes.', 6, 4);

INSERT INTO ventas (fk_libro, fk_usuario, cantidad, total, metodoPago, estado) VALUES
-- Usuario 2 (5 ventas)
(1, 2, 1, 15.99, 'efectivo', 'pagado'),
(2, 2, 2, 25.00, 'efectivo', 'pagado'),
(3, 2, 1, 18.00, 'efectivo', 'pagado'),
(4, 2, 1, 22.00, 'efectivo', 'pagado'),
(5, 2, 1, 9.99, 'efectivo', 'pagado'),
-- Usuario 3 (5 ventas)
(4, 3, 1, 22.00, 'efectivo', 'pagado'),
(6, 3, 1, 14.25, 'efectivo', 'pagado'),
(7, 3, 2, 33.00, 'efectivo', 'pagado'),
(8, 3, 1, 25.00, 'efectivo', 'pagado'),
(9, 3, 1, 11.00, 'efectivo', 'pagado'),
-- Usuario 4 (5 ventas)
(10, 4, 1, 13.99, 'efectivo', 'pagado'),
(11, 4, 1, 17.30, 'efectivo', 'pagado'),
(12, 4, 1, 19.99, 'efectivo', 'pagado'),
(13, 4, 3, 25.50, 'efectivo', 'pagado'),
(14, 4, 1, 15.00, 'efectivo', 'pagado'),
-- Usuario 5 (5 ventas)
(15, 5, 1, 21.99, 'efectivo', 'pagado'),
(16, 5, 2, 21.00, 'efectivo', 'pagado'),
(17, 5, 1, 16.00, 'efectivo', 'pagado'),
(18, 5, 1, 14.99, 'efectivo', 'pagado'),
(19, 5, 1, 12.99, 'efectivo', 'pagado'),
-- Usuario 6 (5 ventas)
(20, 6, 1, 18.50, 'efectivo', 'pagado'),
(1, 6, 1, 15.99, 'efectivo', 'pagado'),
(2, 6, 1, 12.50, 'efectivo', 'pagado'),
(3, 6, 1, 18.00, 'efectivo', 'pagado'),
(4, 6, 1, 22.00, 'efectivo', 'pagado');

INSERT INTO clasificaciones (fk_usuario, fk_libro, clasificacion) VALUES
-- Usuario 2 (5 clasificaciones)
(2, 1, 4), (2, 2, 5), (2, 3, 3), (2, 4, 4), (2, 5, 2),
-- Usuario 3 (5 clasificaciones)
(3, 4, 5), (3, 6, 4), (3, 7, 5), (3, 8, 4), (3, 9, 3),
-- Usuario 4 (5 clasificaciones)
(4, 10, 5), (4, 11, 4), (4, 12, 3), (4, 13, 2), (4, 14, 4),
-- Usuario 5 (5 clasificaciones)
(5, 15, 5), (5, 16, 3), (5, 17, 5), (5, 18, 4), (5, 19, 4),
-- Usuario 6 (5 clasificaciones)
(6, 20, 4), (6, 1, 4), (6, 2, 5), (6, 3, 3), (6, 4, 5);

INSERT INTO historialLectoras (fk_usuario, fk_libro) VALUES
-- Usuario 2 (5 registros de lectura)
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
-- Usuario 3 (5 registros de lectura)
(3, 4), (3, 6), (3, 7), (3, 8), (3, 9),
-- Usuario 4 (5 registros de lectura)
(4, 10), (4, 11), (4, 12), (4, 13), (4, 14),
-- Usuario 5 (5 registros de lectura)
(5, 15), (5, 16), (5, 17), (5, 18), (5, 19),
-- Usuario 6 (5 registros de lectura)
(6, 20), (6, 1), (6, 2), (6, 3), (6, 4);

