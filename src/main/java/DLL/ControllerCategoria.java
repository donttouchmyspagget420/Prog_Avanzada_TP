package DLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerCategoria {
    private static final String TABLE = "categorias";

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
}
