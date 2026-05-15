package DLL;

import BLL.Cliente;
import Utils.Hash;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerCliente {
    protected final static String TABLE = "usuarios";

    public Cliente iniciarSeccionBase(String correo, String username, String contrasena) throws SQLException {
        String sql = "INSERT INTO " + TABLE + "(correo,username,contrasena) VALUES(?,?,?)";

        String hash = Hash.hash(contrasena);

        String[] vals = {correo, username, hash};

        int res = Database.getInstanse().update(sql, vals);

        if (res < 0) throw new SQLException(sql + ": failed");

        sql = "SELECT id FROM " + TABLE + " WHERE correo = ?";

        vals = new String[]{correo};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) {
            throw new SQLException("results is wrong: iniciarSeccionBase");
        }
        int id = resultSet.getInt("id");

        return new Cliente(id, correo, username, hash);
    }

    public Cliente loginBase(String correo, String contrasena) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE correo = ?";

        String[] vals = {correo};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) throw new SQLException("results is wrong: loginBase");

        String hash = resultSet.getString("contrasena");

        if (!Hash.verificar(contrasena, hash)) {
            return null;
        }

        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String pfp = resultSet.getString("pfp");
        String about = resultSet.getString("sobre");

        return new Cliente(id, correo, username, hash, pfp, about);
    }

}
