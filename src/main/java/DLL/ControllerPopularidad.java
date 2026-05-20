package DLL;

import BLL.Libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerPopularidad {
    public ArrayList<Libro> calcularPopularidadBase() throws SQLException {
        String sql = "SELECT * FROM " + ControllerLibro.TABLE + " ORDER BY clasificacion DESC LIMIT 10";

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
            int cantidadDeClasificacion = resultSet.getInt("cantidadDeClasificacion");
            int pages = resultSet.getInt("paginas");
            float clasification = resultSet.getFloat("clasificacion");
            int fkCategory = resultSet.getInt("fk_categoria");
            int fkAuthor = resultSet.getInt("fk_autor ");

            res.add(new Libro(id, cover, precio, stock, title, description, content, cantidadDeClasificacion, pages, clasification, fkCategory, fkAuthor));
        }

        return res;
    }
}
