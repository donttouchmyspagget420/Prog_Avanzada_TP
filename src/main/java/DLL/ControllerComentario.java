package DLL;

import java.sql.SQLException;

public class ControllerComentario {
    protected final static String TABLE = "comentarios";

    public int dejarComentario(int userId, int libroId, int clasification, String contenido) throws SQLException {
        String sql = "INSERT INTO " + TABLE + "(clasificacion,contenido,fk_autor,fk_libro) VALUES(?,?,?,?)";

        String[] vals = {String.valueOf(clasification), contenido, String.valueOf(userId), String.valueOf(libroId)};

        return Database.getInstanse().update(sql, vals);
    }
}
