package DLL;

import Utils.Hash;

import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerEmpleado {
    public int crearVentaBase(int cantidad, double total, String estado, String metodoPago, LocalDate fecha, int fkLibro, int fkUsuario) throws SQLException {
        String sql = "INSERT INTO " + ControllerVenta.TABLE + "(cantidad,total,estado,metodoPago,fecha,fk_libro,fk_usuario) VALUES(?,?,?,?,?,?,?)";

        String[] vals = {String.valueOf(cantidad), String.valueOf(total), estado, metodoPago, String.valueOf(fecha), String.valueOf(fkLibro), String.valueOf(fkUsuario)};

        return Database.getInstanse().update(sql, vals);
    }

    public int modificarVentaBase(int ventaId, int cantidad, double total, String estado, String metodoPago, LocalDate fecha, int fkLibro, int fkUsuario) throws SQLException {
        String sql = "UPDATE " + ControllerVenta.TABLE + " SET cantidad = ?,total = ?,estado = ?,metodoPago = ?,fecha = ?,fk_libro = ?,fk_usuario = ? WHERE id = ?";

        String[] vals = {String.valueOf(cantidad), String.valueOf(total), estado, metodoPago, String.valueOf(fecha), String.valueOf(fkLibro), String.valueOf(fkUsuario), String.valueOf(ventaId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int eliminarVentaBase(int ventaId) throws SQLException {
        String sql = "DELETE FROM " + ControllerVenta.TABLE + " WHERE id = ?";

        String[] vals = {String.valueOf(ventaId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int crearLibroBase(String portada, float precio, int stock, String titulo, String descripcion, String contenido, int cantidadDeClasificacion, int paginas, float clasificacion, int fkCategoria, int fkAutor) throws SQLException {
        String sql = "INSERT INTO " + ControllerLibro.TABLE + "(portada, precio, stock, titulo, descripcion, contenido, cantidadDeClasificacion, paginas, clasificacion, fk_categoria, fk_autor) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        String[] vals = {portada, String.valueOf(precio), String.valueOf(stock), titulo, descripcion, contenido, String.valueOf(cantidadDeClasificacion), String.valueOf(paginas), String.valueOf(clasificacion), String.valueOf(fkCategoria), String.valueOf(fkAutor)};

        return Database.getInstanse().update(sql, vals);
    }

    public int modificarLibroBase(int libroId, String portada, float precio, int stock, String titulo, String descripcion, String contenido, int cantidadDeClasificacion, int paginas, float clasificacion, int fkCategoria, int fkAutor) throws SQLException {
        String sql = "UPDATE " + ControllerLibro.TABLE + " SET portada = ?, precio = ?, stock = ?, titulo = ?, descripcion = ?, contenido = ?, cantidadDeClasificacion = ?, paginas = ?, clasificacion = ?, fk_categoria = ?, fk_autor = ? WHERE id = ?";

        String[] vals = {portada, String.valueOf(precio), String.valueOf(stock), titulo, descripcion, contenido, String.valueOf(cantidadDeClasificacion), String.valueOf(paginas), String.valueOf(clasificacion), String.valueOf(fkCategoria), String.valueOf(fkAutor), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int eliminarLibroBase(int libroId) throws SQLException {
        String sql = "DELETE FROM " + ControllerLibro.TABLE + " WHERE id = ?";

        String[] vals = {String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int crearClienteBase(String correo, String username, String contrasena, String pfp, String sobre) throws SQLException {
        String hash = Hash.hash(contrasena);

        String sql = "INSERT INTO " + ControllerLibro.TABLE + "(correo, username, contrasena, pfp, sobre) VALUES(?,?,?,?,?)";

        String[] vals = {correo, hash, contrasena, pfp, sobre};

        return Database.getInstanse().update(sql, vals);
    }

    public int modificarClienteBase(int userId, String correo, String username, String contrasena, String pfp, String sobre) throws SQLException {
        String sql = "UPDATE " + ControllerCliente.TABLE + " SET correo = ?, username = ?, contrasena = ?, pfp = ?, sobre = ? WHERE id = ?";

        String hash = Hash.hash(contrasena);

        String[] vals = {correo, hash, contrasena, pfp, sobre, String.valueOf(userId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int eliminarClienteBase(int userId) throws SQLException {
        String sql = "DELETE FROM " + ControllerCliente.TABLE + " WHERE id = ?";

        String[] vals = {String.valueOf(userId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int crearComentarioBase(int clasificacion, String contenido, int fkAutor, int fkLibro) throws SQLException {
        String sql = "INSERT INTO " + ControllerComentario.TABLE + "(clasificacion, contenido, fk_autor, fk_libro) VALUES(?,?,?,?)";

        String[] vals = {String.valueOf(clasificacion), contenido, String.valueOf(fkAutor), String.valueOf(fkLibro)};

        return Database.getInstanse().update(sql, vals);
    }

    public int modificarComentarioBase(int commId, int clasificacion, String contenido, int fkAutor, int fkLibro) throws SQLException {
        String sql = "UPDATE " + ControllerComentario.TABLE + " SET clasificacion = ?, contenido = ?, fk_autor = ?, fk_libro = ? WHERE id = ?";

        String[] vals = {String.valueOf(clasificacion), contenido, String.valueOf(fkAutor), String.valueOf(fkLibro), String.valueOf(commId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int eliminarComentarioBBase(int commId) throws SQLException {
        String sql = "DELETE FROM " + ControllerComentario.TABLE + " WHERE id = ?";

        String[] vals = {String.valueOf(commId)};

        return Database.getInstanse().update(sql, vals);
    }


}
