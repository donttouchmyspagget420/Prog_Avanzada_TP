-- GENERADO POR ESTUPIDEZ NATURAL BY AMANGELDIULY MADI

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
  pfp TEXT DEFAULT 'resources/imgs/users/default.png',
  sobre TEXT,
  fk_rol INTEGER,
  FOREIGN KEY(fk_rol) REFERENCES roles(id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS libros (
  id serial PRIMARY KEY,
  portada TEXT NOT NULL,
  precio float NOT NULL,
  stock INT NOT NULL,
  titulo TEXT UNIQUE NOT NULL,
  descripcion TEXT,
  contenido TEXT,
  paginas INT NOT NULL,
  clasificacion float NOT NULL,
  fk_categoria INT,
  fk_autor INT,
  FOREIGN KEY(fk_autor) REFERENCES usuarios(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,

  FOREIGN KEY(fk_categoria) REFERENCES categorias(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ventas (
  id serial PRIMARY KEY,
  fk_libro INT,
  fk_usuario INT,
  cantidad INT NOT NULL,
  total float NOT NULL,
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
  clasificacion INT,
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
  clasificacion INT,
  FOREIGN KEY(fk_usuario) REFERENCES usuarios(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE,

  FOREIGN KEY(fk_libro) REFERENCES libros(id)
  ON DELETE SET NULL
  ON UPDATE CASCADE
);
