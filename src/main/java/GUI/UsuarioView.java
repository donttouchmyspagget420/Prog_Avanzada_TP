package GUI;

import BLL.Cliente;
import BLL.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UsuarioView extends DataView {
    UsuarioView() {
        super("Usuario");

        crear.addActionListener(e -> {
            new DialogUsuario(this);

            StateManager.setPagina(StateManager.paginasEmpleo.USUARIOS);
        });

        table.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
        table.setRowHeight(100);

        table.revalidate();
        table.repaint();
    }

    void showContent(String viewname) {
        cols = new String[]{"ID", "Correo", "Username", "Contrasena", "pfp", "Sobre", "Modificar " + viewname, "Eliminar " + viewname};
        ArrayList<Cliente> arr = Cliente.selectClientes();

        if (arr == null || arr.size() == 0) {
            this.add(new JLabel("no hay ventas"));
            return;
        }

        data = new Object[arr.size()][cols.length];


        for (int i = 0; i < arr.size(); i++) {
            Cliente c = arr.get(i);
            Object[] obj = {c.getId(), c.getCorreo(), c.getUsername(), c.getContrasena(), c.getPfp(), c.getSobre(), "Modificar " + viewname, "Eliminar " + viewname};

            data[i] = obj;
        }
    }
}
