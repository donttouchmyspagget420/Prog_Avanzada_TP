package GUI;

import BLL.Venta;

import javax.swing.*;
import java.util.ArrayList;

public class VentaView extends DataView {
    VentaView() {
        super("Venta");

        crear.addActionListener(e -> {
            Dialog dialog = new DialogVenta(this);

            StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);
        });
    }

    @Override
    protected void showContent(String viewname) {
        cols = new String[]{"ID", "Cantidad", "Total", "Estado", "MetodoPago", "Fecha", "IDLibro", "IDUsuario", "Modificar la " + viewname, "Eliminar la " + viewname};
        ArrayList<Venta> arr = Venta.selectVenta();

        if (arr == null || arr.size() == 0) {
            this.add(new JLabel("no hay ventas"));
            return;
        }

        data = new Object[arr.size()][cols.length];

        for (int i = 0; i < arr.size(); i++) {
            Venta v = arr.get(i);
            Object[] obj = {v.getId(), v.getCantidad(), v.getTotal(), v.getEstado(), v.getMetodoPago(), v.getFecha(), v.getFkLibro(), v.getFkUsuario(), "Modificar la " + viewname, "Eliminar la " + viewname};

            data[i] = obj;
        }

    }
}
