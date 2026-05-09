package DLL;

import BLL.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerPopularidad {
    public Libro[] verPopularidad(String categoria) throws SQLException {
        String sql = "SELECT * FROM " + ControllerLibro.TABLE + " WHERE categoria = ? ORDER BY clasificacion DESC LIMIT 10";

        String[] vals = {categoria};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len < 0) return null;

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
            int pages = resultSet.getInt("paginas");
            float clasification = resultSet.getFloat("clasificacion");
            int fkCategory = resultSet.getInt("fk_categoria");
            int fkAuthor = resultSet.getInt("fk_autor ");

            res[i] = new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor);
        }

        return res;
    }
}
