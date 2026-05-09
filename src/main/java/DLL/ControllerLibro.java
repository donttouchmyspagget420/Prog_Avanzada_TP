package DLL;

import BLL.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLibro {
    protected final static String TABLE = "libros";

    public int actualizarStockBase(int libroId, int newStock) throws SQLException {
        String sql = "UPDATE " + TABLE + " SET stock = ? WHERE id = ?";

        String[] vals = {String.valueOf(newStock), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public int calcularPopularidadBase(int libroId, String categoria) throws SQLException {
        String sql = "SELECT id FROM " + TABLE + " WHERE fk_categoria = (SELECT id FROM categorias WHERE nombre = ?) ORDER BY clasificacion DESC";

        String[] vals = {categoria};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len <= 0) return -1;

        for (int i = 0; i < len; i++) {
            resultSet.next();
            if (resultSet.getInt("id") == libroId) {
                return i;
            }
        }

        return -1;
    }

    public Libro[] verCatalogoBase(int cantidad) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " LIMIT " + cantidad;

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
        String sql = "SELECT * FROM " + TABLE + " WHERE title LIKE(%?%)" + " OR descripcion LIKE(%?%)";

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
        String sql = "SELECT id,precio FROM " + TABLE + " WHERE titulo = ?";

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

    public int dejarClasificacion(int userId, String title, int clasification, String contenido) throws SQLException {
        String sql = "SELECT id,clasificacion,cantidadDeClasificacion  FROM " + TABLE + " WHERE titulo = ?";

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

    private Libro[] verLibrosBaseHelper(int[] ids) throws SQLException {
        String vals = "";

        for (int id : ids) {
            vals = vals + "," + String.valueOf(id);
        }

        String sql = "SELECT * FROM " + ControllerLibro.TABLE + " WHERE id IN (" + vals + ")";

        ResultSet resultSet = Database.getInstanse().query(sql);

        Libro[] res = new Libro[ids.length];

        int len = resultSet.getMetaData().getColumnCount();

        if (len < 0) return null;

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
            int fkCategory = resultSet.getInt("fk_categoria");
            int fkAuthor = resultSet.getInt("fk_autor ");

            res[i] = new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor);
        }

        return res;
    }

    public Libro[] verHistorialLecturasBase(int userId) throws SQLException {
        String sql = "SELECT fk_libro FROM " + ControllerLibro.TABLE + " WHERE fk_usuario = ?";

        String[] vals = {String.valueOf(userId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len < 0) return null;

        int[] res = new int[len];

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("fk_libro");

            res[i] = id;
        }

        return verLibrosBaseHelper(res);
    }

    public String leerPaginasLibroBase(String titulo) throws SQLException {
        String sql = "SELECT contenido FROM " + ControllerLibro.TABLE + " WHERE titulo = ?";

        String[] vals = {titulo};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len < 0) return null;

        resultSet.next();

        return resultSet.getString("contenido");
    }

}
