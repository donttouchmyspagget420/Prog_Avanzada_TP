package BLL;

import DLL.ControllerVenta;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private static ControllerVenta controller = new ControllerVenta();

    private int id;
    private int cantidad;
    private double total;
    private String estado;
    private String metodoPago;
    private LocalDate fecha;
    private int fkLibro;
    private int fkUsuario;

    public Venta(int id, int cantidad, double total, String estado, String metodoPago, LocalDate fecha, int fkLibro, int fkUsuario) {
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

    public ArrayList<Venta> verHistorialCompras() {
        int userId = Cliente.getSession().getId();
        ArrayList<Venta> res;

        try {
            return controller.verHistorialComprasBase(userId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede ver el historial de compras, razon:\n" + e.getMessage());
            return null;
        }
    }

    public int comprarLibro(int libroId, int cantidad, String metodoPago) {
        int userId = Cliente.getSession().getId();

        try {
            int ventaId = controller.comprarLibro(userId, libroId, cantidad, metodoPago);
            return controller.procesarPago(ventaId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede comprar el libro, razon:\n" + e.getMessage());
            return -1;
        }
    }

    public String generarFactura() {
        return toString();
    }
}
