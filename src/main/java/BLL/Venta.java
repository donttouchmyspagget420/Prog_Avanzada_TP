package BLL;

import DLL.ControllerVenta;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

public class Venta {
    private static ControllerVenta controller = new ControllerVenta();

    private int id;
    private int cantidad;
    private double total;
    private String estado;
    private String metodoPago;
    private Date fecha;
    private int fkLibro;
    private int fkUsuario;

    public Venta(int id, int cantidad, double total, String estado, String metodoPago, Date fecha, int fkLibro, int fkUsuario) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.fkLibro = fkLibro;
        this.fkUsuario = fkUsuario;
    }

    @Override
    public String toString() {
        try {
            return controller.generarFacturaBase(fkLibro, fkUsuario) + "cantidad: " + cantidad + "\n" + "total: " + total + "\n" + "estado: " + estado
                    + "\n" + "método de pago: " + metodoPago + "\n" + "fecha: " + fecha + "\n";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede factura, razon:\n" + e.getMessage());
            return null;
        }
    }

    public static ArrayList<String> verHistorialCompras() {
        int userId = Cliente.getSession().getId();
        ArrayList<Venta> ventas;
        ArrayList<String> res = new ArrayList<>();

        try {
            ventas = controller.verHistorialComprasBase(userId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede ver el historial de compras, razon:\n" + e.getMessage());
            return null;
        }

        if (ventas == null || ventas.size() == 0) return null;

        for (Venta venta : ventas) {
            res.add(venta.toString());
        }

        return res;
    }

    public static int comprarLibro(int libroId) {
        int userId = Cliente.getSession().getId();
        String[] metodos = {"mercado pago", "efectivo"};

        try {
            int option = JOptionPane.showOptionDialog(null, "metodo de pago", "elegir", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, metodos, metodos[0]);
            int cantidad = Integer.valueOf(JOptionPane.showInputDialog("cantidad?"));

            if ((option < 0 && option > metodos.length) || cantidad < 0)
                throw new Exception("no se puede comprar " + cantidad + " libros");

            if (Libro.actualizarStock(libroId, cantidad) < 0) return -1;

            int ventaId = controller.comprarLibro(userId, libroId, cantidad, metodos[option]);

            return controller.procesarPago(ventaId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no puede comprar el libro, razon:\n" + e.getMessage());
            return -1;
        }
    }

    public String generarFactura() {
        return toString();
    }
}
