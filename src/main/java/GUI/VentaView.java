package GUI;

import BLL.Venta;

import javax.swing.*;
import java.util.ArrayList;

public class VentaView extends DataView {
    VentaView() {
        super("Venta");

        crear.addActionListener(e -> {
            new DialogVenta(this);

            StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);
        });
    }

    @Override
    void showContent(String viewname) {
        cols = new String[]{"ID", "Cantidad", "Total", "Estado", "MetodoPago", "Fecha", "IDLibro", "IDUsuario", "Modificar " + viewname, "Eliminar " + viewname};
        ArrayList<Venta> arr = Venta.selectVenta();

        if (arr == null || arr.size() == 0) {
            this.add(new JLabel("no hay ventas"));
            return;
        }

        data = new Object[arr.size()][cols.length];

        for (int i = 0; i < arr.size(); i++) {
            Venta v = arr.get(i);
            Object[] obj = {v.getId(), v.getCantidad(), v.getTotal(), v.getEstado(), v.getMetodoPago(), v.getFecha(), v.getFkLibro(), v.getFkUsuario(), "Modificar " + viewname, "Eliminar " + viewname};

            data[i] = obj;
        }

    }
}
