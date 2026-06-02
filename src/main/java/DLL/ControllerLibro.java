package DLL;

import BLL.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerLibro {
    protected final static String TABLE = "libros";

    public int actualizarStockBase(int libroId, int cantidad) throws SQLException {
        String sql = "UPDATE " + TABLE + " SET stock = stock - CAST(? AS INT) WHERE id = CAST(? AS INT)";

        String[] vals = {String.valueOf(cantidad), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public ArrayList<Libro> verCatalogoBase(String categoria) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + (categoria.equals("ninguno") ? "" : " WHERE fk_categoria = (SELECT id FROM categorias WHERE nombre = ?)");

        ResultSet resultSet;

        if (!categoria.equals("ninguno")) {
            String[] vals = {categoria};
            resultSet = Database.getInstanse().query(sql, vals);
        } else {
            resultSet = Database.getInstanse().query(sql);
        }

        ArrayList<Libro> res = new ArrayList<>();

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("id");
            String cover = resultSet.getString("portada");
            int stock = resultSet.getInt("stock");
            float precio = resultSet.getFloat("precio");
            String title = resultSet.getString("titulo");
            String description = resultSet.getString("descripcion");
            String content = resultSet.getString("contenido");
            int pages = resultSet.getInt("paginas");
            float clasification = resultSet.getFloat("clasificacion");
            int fkCategory = resultSet.getInt("fk_categoria");

            res.add(new Libro(id, cover, precio, stock, title, description, content, pages, clasification, fkCategory));
        }

        return res;
    }

    public ArrayList<Libro> buscarLibrosBase(String categoria, String search) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE (titulo LIKE(?)" + " OR descripcion LIKE(?))" + ((categoria.equals("ninguno") ? "" : " AND fk_categoria = (SELECT id FROM categorias WHERE nombre = ?)"));

        String[] vals;

        if (categoria.equals("ninguno")) vals = new String[]{"%" + search + "%", "%" + search + "%"};
        else vals = new String[]{"%" + search + "%", "%" + search + "%", categoria};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        ArrayList<Libro> res = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String cover = resultSet.getString("portada");
            int stock = resultSet.getInt("stock");
            float precio = resultSet.getFloat("precio");
            String title = resultSet.getString("titulo");
            String description = resultSet.getString("descripcion");
            String content = resultSet.getString("contenido");
            float clasification = resultSet.getFloat("clasificacion");
            int pages = resultSet.getInt("paginas");
            int fkCategory = resultSet.getInt("fk_categoria");

            res.add(new Libro(id, cover, precio, stock, title, description, content, pages, clasification, fkCategory));
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

    private ArrayList<Libro> verLibrosBaseHelper(ArrayList<Integer> ids) throws SQLException {
        if (ids == null || ids.size() == 0) return null;

        String vals = ids.toString().substring(1, ids.toString().length() - 1);

        String sql = "SELECT * FROM " + ControllerLibro.TABLE + " WHERE id IN (" + vals + ")";

        ResultSet resultSet = Database.getInstanse().query(sql);

        ArrayList<Libro> res = new ArrayList<>(ids.size());

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
            int pages = resultSet.getInt("paginas");
            float clasification = resultSet.getFloat("clasificacion");
            int fkCategory = resultSet.getInt("fk_categoria");

            res.add(new Libro(id, cover, precio, stock, title, description, content, pages, clasification, fkCategory));
        }

        return res;
    }

    public ArrayList<Libro> verHistorialLecturasBase(int userId) throws SQLException {
        String sql = "SELECT fk_libro FROM historialLectoras WHERE fk_usuario = CAST(? AS INT)";

        String[] vals = {String.valueOf(userId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("fk_libro");

            res.add(id);
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
        ArrayList<Integer> ids = new ArrayList<>();

        ids.add(libroId);

        return verLibrosBaseHelper(ids).get(0);
    }

    public ArrayList<Libro> selectLibros() throws SQLException {
        String sql = "SELECT * FROM " + TABLE;

        ResultSet resultSet = Database.getInstanse().query(sql);

        ArrayList<Libro> res = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String cover = resultSet.getString("portada");
            int stock = resultSet.getInt("stock");
            float precio = resultSet.getFloat("precio");
            String title = resultSet.getString("titulo");
            String description = resultSet.getString("descripcion");
            String content = resultSet.getString("contenido");
            int pages = resultSet.getInt("paginas");
            float clasification = resultSet.getFloat("clasificacion");
            int fkCategory = resultSet.getInt("fk_categoria");

            res.add(new Libro(id, cover, precio, stock, title, description, content, pages, clasification, fkCategory));
        }

        return res;
    }
}
