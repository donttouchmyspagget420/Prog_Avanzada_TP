package GUI;

import BLL.Empleado;

import javax.swing.*;
import java.sql.Date;

public class DialogVenta extends Dialog {
    private TextField cantidadF, totalF, estadoF, pagoF, fechaF, fkLibroF, fkUsuarioF;

    DialogVenta(JFrame parent) {
        super(parent);

        cantidadF = new TextField("cantidad");
        totalF = new TextField("total");
        estadoF = new TextField("estado");
        pagoF = new TextField("metodo de pago");
        fechaF = new TextField("fecha");
        fkLibroF = new TextField("fkLibro");
        fkUsuarioF = new TextField("fkUsuario");

        wrapper.add(cantidadF);
        wrapper.add(totalF);
        wrapper.add(estadoF);
        wrapper.add(pagoF);
        wrapper.add(fechaF);
        wrapper.add(fkLibroF);
        wrapper.add(fkUsuarioF);
        wrapper.add(new JPanel());
        wrapper.add(close);
        wrapper.add(submit);

        add(wrapper);

        submit.addActionListener(e -> {
            action();
        });

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    private void action() {
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
            estado = estadoF.getText();
            pago = pagoF.getText();
            fecha = Date.valueOf(fechaF.getText());
            fkLibro = Integer.valueOf(fkLibroF.getText());
            fkUsuario = Integer.valueOf(fkUsuarioF.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        Empleado.getSession().crearVenta(cantidad, total, estado, pago, fecha, fkLibro, fkUsuario);

        StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);
    }
}
