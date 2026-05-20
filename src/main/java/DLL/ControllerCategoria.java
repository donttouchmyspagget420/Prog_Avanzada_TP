package DLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerCategoria {
    private static final String TABLE = "categorias";

    public ArrayList<String> getCategorias() throws SQLException {
        String sql = "SELECT nombre FROM " + TABLE;

        ResultSet resultSet = Database.getInstanse().query(sql);

        ArrayList<String> res = new ArrayList<>();

        while (resultSet.next()) {
            String categoria = resultSet.getString("nombre");

            res.add(categoria);
        }

        return res;
    }
}
