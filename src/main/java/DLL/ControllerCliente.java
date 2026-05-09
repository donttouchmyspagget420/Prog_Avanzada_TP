package DLL;

import BLL.Cliente;
import BLL.Libro;
import BLL.Venta;
import Utils.Hash;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerCliente {
    protected final static String TABLE = "usuarios";

    public Cliente iniciarSeccionBase(String email, String username, String password, String pfp, String about) throws SQLException {
        String sql = "INSERT INTO " + TABLE + " VALUES(?,?,?,?,?)";

        String hash = Hash.hash(password);

        String[] vals = {email, username, hash, pfp, about};

        int res = Database.getInstanse().update(sql, vals);

        if (res < 0) throw new SQLException(sql + ": failed");

        sql = "SELECT id FROM " + TABLE + " WHERE correo = ?";

        vals = new String[]{email};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) {
            throw new SQLException("results is wrong: iniciarSeccionBase");
        }
        int id = resultSet.getInt("id");

        return new Cliente(id, email, username, hash, pfp, about);
    }

    public Cliente loginBase(String email, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE correo = ?";

        String[] vals = {email};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) throw new SQLException("results is wrong: loginBase");

        String hash = resultSet.getString("contrasena");

        if (!Hash.verificar(password, hash)) {
            return null;
        }

        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String pfp = resultSet.getString("pfp");
        String about = resultSet.getString("sobre");

        return new Cliente(id, email, username, hash, pfp, about);
    }

}
