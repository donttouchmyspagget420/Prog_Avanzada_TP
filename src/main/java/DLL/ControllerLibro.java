package DLL;

import BLL.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerLibro {
    protected final static String TABLE = "libros";

    public int actualizarStockBase(int libroId, int newStock) throws SQLException {
        String sql = "UPDATE " + TABLE + " SET stock = CAST(? AS INT) WHERE id = CAST(? AS INT)";

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

    public int insertClasificacionBase(int userId, int libroId, int clasification) throws SQLException {
        String sql = "INSERT INTO clasificaciones(fk_usuario,fk_libro,clasificacion) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT))";

        String vals[] = {String.valueOf(userId), String.valueOf(libroId), String.valueOf(clasification)};

        return Database.getInstanse().update(sql, vals);
    }


    public int checkClasifiocacionBase(int userId, int libroId) throws SQLException {
        String sql = "SELECT clasificacion FROM clasificaciones WHERE fk_libro = CAST(? AS INT) AND fk_usuario = CAST(? AS INT)";

        String[] vals = {String.valueOf(libroId), String.valueOf(userId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) return -1;

        return resultSet.getInt(1);
    }

    public int dejarClasificacionBase(int userId, int libroId, int clasification) throws SQLException {
        if (checkClasifiocacionBase(userId, libroId) > 0) return -1;

        if (insertClasificacionBase(userId, libroId, clasification) <= 0) return -1;

        String sql = "SELECT count(*) FROM clasificaciones";

        ResultSet resultSet = Database.getInstanse().query(sql);

        if (!resultSet.next()) throw new SQLException("problemas con base de datos");

        int cantidad = resultSet.getInt(1);

        float newClasification = (float) (clasification * 2) / cantidad;

        sql = "UPDATE " + TABLE + " SET clasificacion = clasificacion + CAST(? AS FLOAT) WHERE id = CAST(? AS INT)";

        String[] vals = {String.valueOf(newClasification), String.valueOf(libroId)};

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
        String sql = "SELECT contenido FROM " + TABLE + " WHERE id = CAST(? AS INT)";

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
        String sql = "INSERT INTO historialLectoras(fk_usuario, fk_libro) VALUES(CAST(? AS INT),CAST(? AS INT))";

        String[] vals = {String.valueOf(userId), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public boolean checkCompradoBase(int fkUsuario, int fkLibro) throws SQLException {
        String sql = "SELECT EXISTS ( SELECT 1 FROM " + ControllerVenta.TABLE + " WHERE fk_usuario = CAST(? AS INT) AND fk_libro = CAST(? AS INT) )";

        String[] vals = {String.valueOf(fkUsuario), String.valueOf(fkLibro)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        resultSet.next();

        return resultSet.getBoolean(1);
    }

    public Libro getByIdBase(int libroId) throws SQLException {
        ArrayList<Libro> libros = verLibrosBaseHelper(new int[]{libroId});

        return libros.get(0);
    }
}
