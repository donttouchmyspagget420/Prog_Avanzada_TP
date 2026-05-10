package DLL;

import BLL.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerLibro {
    protected final static String TABLE = "libros";

    public int actualizarStockBase(int libroId, int newStock) throws SQLException {
        String sql = "UPDATE " + TABLE + " SET stock = ? WHERE id = ?";

        String[] vals = {String.valueOf(newStock), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public ArrayList<Libro> verCatalogoBase(String categoria, int cantidad) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + "WHERE categoria = ? LIMIT " + cantidad;

        String[] vals = {categoria};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        ArrayList<Libro> res = new ArrayList<>(cantidad);

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

            res.add(new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor));
        }

        return res;
    }

    public ArrayList<Libro> verCatalogoBase(int cantidad) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " LIMIT " + cantidad;

        ResultSet resultSet = Database.getInstanse().query(sql);

        ArrayList<Libro> res = new ArrayList<>(cantidad);

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

            res.add(new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor));
        }

        return res;
    }

    public ArrayList<Libro> buscarLibrosBase(String search) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE title LIKE(%?%)" + " OR descripcion LIKE(%?%)";

        String[] vals = {search, search};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len <= 0) return null;

        ArrayList<Libro> res = new ArrayList<>(len);

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

            res.add(new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor));
        }

        return res;
    }


    public int dejarClasificacionBase(int userId, int libroId, int clasification) throws SQLException {
        String sql = "SELECT EXISTS (SELECT 1 FROM " + TABLE + " WHERE fk_libro = ? AND fk_usuario = ?)";

        String[] vals = {String.valueOf(libroId), String.valueOf(userId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) return -1;

        boolean bool = resultSet.getBoolean(1);

        if (bool) return -1;

        sql = "SELECT count(*) FROM clasificaciones";

        resultSet = Database.getInstanse().query(sql);

        if (!resultSet.next()) throw new SQLException("problemas con base de datos");

        int cantidad = resultSet.getInt(1);

        float newClasification = (float) clasification / cantidad;

        sql = "UPDATE " + TABLE + " SET clasificacion = clasificacion + ? WHERE id = ?";

        vals = new String[]{String.valueOf(newClasification), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    private ArrayList<Libro> verLibrosBaseHelper(int[] ids) throws SQLException {
        String vals = "";

        for (int id : ids) {
            vals = vals + "," + String.valueOf(id);
        }

        String sql = "SELECT * FROM " + ControllerLibro.TABLE + " WHERE id IN (" + vals + ")";

        ResultSet resultSet = Database.getInstanse().query(sql);

        ArrayList<Libro> res = new ArrayList<>(ids.length);

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

            res.add(new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor));
        }

        return res;
    }

    public ArrayList<Libro> verHistorialLecturasBase(int userId) throws SQLException {
        String sql = "SELECT fk_libro FROM historialLectoras WHERE fk_usuario = ?";

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

    public String leerPaginasLibroBase(int userId, int libroId) throws SQLException {
        String sql = "SELECT contenido FROM " + TABLE + " WHERE id = ?";

        String[] vals = {String.valueOf(libroId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len < 0) return null;

        resultSet.next();

        if (ingresarHistorialLecturaBaseHelper(userId, libroId) <= 0)
            throw new SQLException("hay problemas con base de datos");

        return resultSet.getString("contenido");
    }

    private int ingresarHistorialLecturaBaseHelper(int userId, int libroId) throws SQLException {
        String sql = "INSERT INTO historialLectoras(fk_usuario, fk_libro) VALUES(?,?)";

        String[] vals = {String.valueOf(userId), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }
}
