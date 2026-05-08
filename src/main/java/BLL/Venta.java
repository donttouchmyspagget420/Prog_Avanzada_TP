package BLL;

import java.time.LocalDate;

public class Venta {
    protected final static String TABLE = "ventas";

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
}
