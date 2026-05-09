package DLL;

import BLL.Venta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControllerVenta {
    protected final static String TABLE = "ventas";

    public float calcularTotalBase(int fkLibro, int cantidad) throws SQLException {
        String sql = "SELECT precio FROM " + ControllerLibro.TABLE + " WHERE id = ?";

        String[] vals = {String.valueOf(fkLibro)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) return -1;

        float precio = resultSet.getFloat("precio");

        return precio * cantidad;
    }

    public int procesarPago(int ventaId) throws SQLException {
        String sql = "UPDATE " + TABLE + " SET estado = ? WHERE id = ?";

        String[] vals = {"pagado", String.valueOf(ventaId)};

        return Database.getInstanse().update(sql, vals);
    }

    public Venta[] verHistorialComprasBase(int userId) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE fk_usuario = ?";

        String[] vals = {String.valueOf(userId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        int len = resultSet.getMetaData().getColumnCount();

        if (len < 0) return null;

        Venta[] res = new Venta[len];

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("id");
            int fkLibro = resultSet.getInt("fk_libro");
            int fkUsuario = resultSet.getInt("fk_usuario");
            int cantidad = resultSet.getInt("cantidad");
            float total = resultSet.getFloat("total");
            String metodoPago = resultSet.getString("metodoPago");
            String estado = resultSet.getString("estado");
            LocalDate fecha = (LocalDate) resultSet.getObject("fecha");

            res[i] = new Venta(id, cantidad, total, metodoPago, estado, fecha, fkLibro, fkUsuario);
        }

        return res;
    }
}
