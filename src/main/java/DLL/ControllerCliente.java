package DLL;

import BLL.Cliente;
import BLL.Libro;
import BLL.Venta;
import Utils.Hash;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerCliente {
    protected final static String TABLE = "usuarios";

    public Cliente iniciarSeccionBase(String email, String username, String password, String pfp, String about) throws SQLException {
        String sql = "INSERT INTO " + TABLE + " VALUES(?,?,?,?,?)";

        String hash = Hash.hash(password);

        String[] vals = {email, username, hash, pfp, about};

        int res = Database.getInstanse().update(sql, vals);

        if (res < 0) throw new SQLException(sql + ": failed");

        sql = "SELECT id FROM " + TABLE + " WHERE correo = ?";

        vals = new String[]{email};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) {
            throw new SQLException("results is wrong: iniciarSeccionBase");
        }
        int id = resultSet.getInt("id");

        return new Cliente(id, email, username, hash, pfp, about);
    }

    public Cliente loginBase(String email, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE correo = ?";

        String[] vals = {email};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) {
            throw new SQLException("results is wrong: loginBase");
        }

        String hash = resultSet.getString("contrasena");

        if (!Hash.verificar(password, hash)) {
            return null;
        }

        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String pfp = resultSet.getString("pfp");
        String about = resultSet.getString("sobre");

        return new Cliente(id, email, username, hash, pfp, about);
    }

    public Libro[] verCatalogoBase(int cantidad) throws SQLException {
        String sql = "SELECT * FROM " + ControllerLibro.TABLE + " LIMIT " + cantidad;

        ResultSet resultSet = Database.getInstanse().query(sql);

        Libro[] res = new Libro[cantidad];

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("id");
            String cover = resultSet.getString("portada");
            int stock = resultSet.getInt("stock");
            float precio = resultSet.getFloat("precio");
            String title = resultSet.getString("titulo");
            String description = resultSet.getString("descripcion");
            String content = resultSet.getString("contenido");
            int cantidadDeClasificacion = resultSet.getInt("cantidadDeClasificacion");
            int pages = resultSet.getInt("paginas");
            float clasification = resultSet.getFloat("clasificacion");
            int fkCategory = resultSet.getInt("fk_categoria ");
            int fkAuthor = resultSet.getInt("fk_autor ");

            res[i] = new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor);
        }

        return res;
    }

    public Libro[] buscarLibrosBase(String search) throws SQLException {
        String sql = "SELECT * FROM " + ControllerLibro.TABLE + " WHERE title LIKE(%?%)" + " OR descripcion LIKE(%?%)";

        String[] vals = {search, search};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len <= 0) return null;

        Libro[] res = new Libro[len];

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("id");
            String cover = resultSet.getString("portada");
            int stock = resultSet.getInt("stock");
            float precio = resultSet.getFloat("precio");
            String title = resultSet.getString("titulo");
            String description = resultSet.getString("descripcion");
            String content = resultSet.getString("contenido");
            int cantidadDeClasificacion = resultSet.getInt("cantidadDeClasificacion");
            float clasification = resultSet.getFloat("clasificacion");
            int pages = resultSet.getInt("paginas");
            int fkCategory = resultSet.getInt("fk_categoria ");
            int fkAuthor = resultSet.getInt("fk_autor ");

            res[i] = new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor);
        }

        return res;
    }

    public int comprarLibro(int userId, String title, int cantidad, String metodoPago) throws SQLException {
        String sql = "SELECT id,precio FROM " + ControllerLibro.TABLE + " WHERE titulo = ?";

        String[] vals = {title};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        resultSet.next();

        int bookId = resultSet.getInt("id");
        float price = resultSet.getFloat("precio");

        float total = price * cantidad;

        sql = "INSERT INTO " + ControllerLibro.TABLE + "(fk_libro,fk_usuario,cantidad,total,metodoPago,estado) VALUES(?,?,?,?,?,?)";

        vals = new String[]{String.valueOf(bookId), String.valueOf(userId), String.valueOf(cantidad), String.valueOf(total), metodoPago, "pagado"};

        return Database.getInstanse().update(sql, vals);
    }

    public int dejarComentario(int userId, String title, int clasification, String contenido) throws SQLException {
        String sql = "INSERT INTO " + ControllerComentario.TABLE + "(clasificacion,contenido,fk_autor) VALUES(?,?,?)";

        String[] vals = {String.valueOf(clasification), contenido, String.valueOf(userId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int dejarClasificacion(int userId, String title, int clasification, String contenido) throws SQLException {
        String sql = "SELECT id,clasificacion,cantidadDeClasificacion  FROM " + ControllerLibro.TABLE + " WHERE titulo = ?";

        String[] vals = {title};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        resultSet.next();
        int bookId = resultSet.getInt("id");
        float clasificationLibro = resultSet.getFloat("clasificacion");
        int cantidad = resultSet.getInt("cantidadDeClasificacion");

        float newClasification = (float) clasification / cantidad + clasificationLibro;

        sql = "UPDATE " + ControllerLibro.TABLE + " SET clasificacion = ?, cantidadDeClasificacion = ? WHERE id = ?";

        vals = new String[]{String.valueOf(newClasification), String.valueOf(cantidad + 1), String.valueOf(bookId)};

        return Database.getInstanse().update(sql, vals);
    }

    public Venta[] verHistorialComprasBase(int userId) throws SQLException {
        String sql = "SELECT * FROM " + ControllerVenta.TABLE + " WHERE fk_usuario = ?";

        String[] vals = {String.valueOf(userId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len < 0) return null;

        Venta[] res = new Venta[len];

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("id");
            int fkLibro = resultSet.getInt("fk_libro");
            int fkUsuario = resultSet.getInt("fk_usuario");
            int cantidad = resultSet.getInt("cantidad");
            float total = resultSet.getFloat("total");
            String metodoPago = resultSet.getString("metodoPago");
            String estado = resultSet.getString("estado");
            LocalDate fecha = (LocalDate) resultSet.getObject("fecha");

            res[i] = new Venta(id, cantidad, total, metodoPago, estado, fecha, fkLibro, fkUsuario);
        }

        return res;
    }
}
