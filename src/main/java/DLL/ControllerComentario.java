package DLL;

import BLL.Cliente;
import BLL.Comentario;
import Utils.PlatformManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ControllerComentario {
    protected final static String TABLE = "comentarios";

    public int dejarComentarioBase(int userId, int libroId, int clasification, String contenido) throws SQLException {
        String sql = "INSERT INTO " + TABLE + "(clasificacion,contenido,fk_autor,fk_libro) VALUES(?,?,?,?)";

        String[] vals = {String.valueOf(clasification), contenido, String.valueOf(userId), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }

    public HashMap<String, String> getAuthorBase(int fkAuthor) throws SQLException {
        String sql = "SELECT username,pfp FROM " + ControllerCliente.TABLE + " WHERE id = CAST(? as INT)";

        String[] vals = {String.valueOf(fkAuthor)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        resultSet.next();

        HashMap map = new HashMap();

        String username = resultSet.getString("username");
        String pfp = resultSet.getString("pfp");

        map.put("username", username);
        map.put("pfp", PlatformManager.getPathImgs() + pfp);

        return map;
    }

    public ArrayList<Comentario> getComentariosBase(int libroId) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE fk_author = ?";

        String[] vals = {String.valueOf(libroId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        ArrayList<Comentario> res = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int clasificacion = resultSet.getInt("clasificacion");
            String contenido = resultSet.getString("contenido");
            int fkAuthor = resultSet.getInt("fk_author");

            res.add(new Comentario(id, clasificacion, contenido, fkAuthor));
        }

        return res;
    }
}
