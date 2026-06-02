package GUI;

import BLL.Empleado;

import javax.swing.*;
import java.sql.Date;

public class DialogVenta extends Dialog {
    private TextField cantidadF, totalF, fechaF, fkLibroF, fkUsuarioF;
    private JComboBox<String> estadoD, pagoD;

    DialogVenta(JFrame parent) {
        super(parent);

        String[] estados = {"procesando", "pagado"};
        String[] pagos = {"mercado pago", "efectivo"};

        cantidadF = new TextField("cantidad");
        totalF = new TextField("total");
        estadoD = new JComboBox<>(estados);
        pagoD = new JComboBox<>(pagos);
        fechaF = new TextField("fecha");
        fkLibroF = new TextField("fkLibro");
        fkUsuarioF = new TextField("fkUsuario");

        wrapper.add(cantidadF);
        wrapper.add(totalF);
        wrapper.add(estadoD);
        wrapper.add(pagoD);
        wrapper.add(fechaF);
        wrapper.add(fkLibroF);
        wrapper.add(fkUsuarioF);
        wrapper.add(new JPanel());
        wrapper.add(close);
        wrapper.add(submit);

        add(wrapper);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    @Override
    void action() {
        int cantidad;
        float total;
        String estado;
        String pago;
        Date fecha;
        int fkLibro;
        int fkUsuario;

        try {
            cantidad = Integer.valueOf(cantidadF.getText());
            total = Float.valueOf(totalF.getText());
            estado = estadoD.getSelectedItem().toString();
            pago = pagoD.getSelectedItem().toString();
            fecha = Date.valueOf(fechaF.getText());
            fkLibro = Integer.valueOf(fkLibroF.getText());
            fkUsuario = Integer.valueOf(fkUsuarioF.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        if (Empleado.getSession().crearVenta(cantidad, total, estado, pago, fecha, fkLibro, fkUsuario) > 0)
            StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);
    }
}
