package DLL;

import BLL.Cliente;
import BLL.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerComentario {
    protected final static String TABLE = "comentarios";

    public int dejarComentario(int userId, int libroId, int clasification, String contenido) throws SQLException {
        String sql = "INSERT INTO " + TABLE + "(clasificacion,contenido,fk_autor,fk_libro) VALUES(?,?,?,?)";

        String[] vals = {String.valueOf(clasification), contenido, String.valueOf(userId), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public String getAuthor(int fkAuthor) throws SQLException {
        String sql = "SELECT username FROM " + ControllerCliente.TABLE + " WHERE id = ?";

        String[] vals = {String.valueOf(fkAuthor)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        String res = null;

        if (resultSet.next()) res = resultSet.getString("username");

        return res;
    }
}
