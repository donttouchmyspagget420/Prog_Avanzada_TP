package DLL;

import BLL.Venta;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerVenta {
    protected final static String TABLE = "ventas";

    public float calcularTotalBase(int fkLibro, int cantidad) throws SQLException {
        String sql = "SELECT precio FROM " + ControllerLibro.TABLE + " WHERE id = CAST(? AS iNT)";

        String[] vals = {String.valueOf(fkLibro)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) return -1;

        float precio = resultSet.getFloat("precio");

        return precio * cantidad;
    }

    public int procesarPago(int ventaId) throws SQLException {
        String sql = "UPDATE " + TABLE + " SET estado = ? WHERE id = CAST(? AS INT)";

        String[] vals = {"pagado", String.valueOf(ventaId)};

        return Database.getInstanse().update(sql, vals);
    }

    public ArrayList<Venta> verHistorialComprasBase(int userId) throws SQLException {
        String sql = "SELECT * FROM " + TABLE + " WHERE fk_usuario = CAST(? AS INT)";

        String[] vals = {String.valueOf(userId)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        ArrayList<Venta> res = new ArrayList<>();

        for (int i = 0; resultSet.next(); i++) {
            int id = resultSet.getInt("id");
            int fkLibro = resultSet.getInt("fk_libro");
            int fkUsuario = resultSet.getInt("fk_usuario");
            int cantidad = resultSet.getInt("cantidad");
            float total = resultSet.getFloat("total");
            String metodoPago = resultSet.getString("metodoPago");
            String estado = resultSet.getString("estado");
            Date fecha = (Date) resultSet.getObject("fecha");

            res.add(new Venta(id, cantidad, total, metodoPago, estado, fecha, fkLibro, fkUsuario));
        }

        return res;
    }


    public int comprarLibro(int userId, int libroId, int cantidad, String metodoPago) throws SQLException {
        float total = calcularTotalBase(libroId, cantidad);

        String sql = "INSERT INTO " + TABLE + "(fk_libro,fk_usuario,cantidad,total,metodoPago,estado) VALUES(CAST(? AS INT),CAST(? AS INT),CAST(? AS INT),CAST(? AS FLOAT),?,?)";

        String[] vals = new String[]{String.valueOf(libroId), String.valueOf(userId), String.valueOf(cantidad), String.valueOf(total), metodoPago, "procesando"};

        int res = Database.getInstanse().update(sql, vals);

        if (res <= 0) return -1;

        sql = "SELECT MAX(id) FROM " + TABLE;

        ResultSet resultSet = Database.getInstanse().query(sql);

        resultSet.next();

        return resultSet.getInt(1);
    }

    public String generarFacturaBase(int fkLibro, int fkCliente) throws SQLException {
        String sql = "SELECT titulo FROM " + ControllerLibro.TABLE + " WHERE id = CAST(? AS INT)";

        String[] vals = {String.valueOf(fkLibro)};

        ResultSet resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) return null;

        String titulo = resultSet.getString("titulo");

        sql = "SELECT correo FROM " + ControllerCliente.TABLE + " WHERE id = CAST(? AS INT)";

        vals = new String[]{String.valueOf(fkCliente)};

        resultSet = Database.getInstanse().query(sql, vals);

        if (!resultSet.next()) return null;

        String correo = resultSet.getString("correo");

        return "correo: " + correo + "\n" + "titulo: " + titulo + "\n";
    }
}
