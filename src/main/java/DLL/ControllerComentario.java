package DLL;

import java.sql.SQLException;

public class ControllerComentario {
    protected final static String TABLE = "comentarios";

    public int dejarComentario(int userId, String title, int clasification, String contenido) throws SQLException {
        String sql = "INSERT INTO " + TABLE + "(clasificacion,contenido,fk_autor) VALUES(?,?,?)";

        String[] vals = {String.valueOf(clasification), contenido, String.valueOf(userId)};

        return Database.getInstanse().update(sql, vals);
    }
}
