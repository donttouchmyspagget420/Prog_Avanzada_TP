package DLL;

import BLL.Categorias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerCategoria {
    static final String TABLE = "categorias";

    public ArrayList<String> getCategoriasBase() throws SQLException {
        String sql = "SELECT nombre FROM " + TABLE;

        ResultSet resultSet = Database.getInstanse().query(sql);

        ArrayList<String> res = new ArrayList<>();

        while (resultSet.next()) {
            String categoria = resultSet.getString("nombre");

            res.add(categoria);
        }

        return res;
    }

    public String getNombreBase(int fkCategoria) throws SQLException {
        String sql = "SELECT nombre FROM " + TABLE + " WHERE id = CAST(? AS INT)";

        String vals[] = {String.valueOf(fkCategoria)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) return null;

        return resultSet.getString("nombre");
    }

    public ArrayList<Categorias> selectCategoriasBase() throws SQLException {
        String sql = "SELECT * FROM " + TABLE;

        ResultSet resultSet = Database.getInstanse().query(sql);

        ArrayList<Categorias> res = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");

            res.add(new Categorias(id, nombre));
        }

        return res;
    }
}
