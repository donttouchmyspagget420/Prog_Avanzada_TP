package GUI;

import BLL.Empleado;
import BLL.Venta;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VerVentasFrame extends JFrame {
    private JScrollPane scrollPane;

    VerVentasFrame() {
        SideBar sidebar = new SideBar();
        JPanel wrapper = new JPanel();
        ButtonLink crear = new ButtonLink("Crear una Venta", getBackground());

        scrollPane = new JScrollPane();

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        setLayout(new BorderLayout());
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        scrollPane.setBorder(null);

        showContent();

        wrapper.add(scrollPane);
        wrapper.add(crear);

        add(sidebar, BorderLayout.LINE_START);
        add(wrapper, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 1000);
        setResizable(false);

        crear.addActionListener(e -> {
            Empleado.getSession().crearVenta();

            StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);
        });
    }

    private void showContent() {
        ArrayList<Venta> arr = Venta.selectVenta();

        if (arr == null || arr.size() == 0) {
            this.add(new JLabel("no hay ventas"));
            return;
        }

        String[] cols = {"ID", "Cantidad", "Total", "Estado", "MetodoPago", "Fecha", "IDLibro", "IDUsuario", "Modificar la Venta", "Eliminar la Venta"};

        Object[][] data = new Object[arr.size()][cols.length];

        for (int i = 0; i < arr.size(); i++) {
            Venta v = arr.get(i);
            Object[] obj = {v.getId(), v.getCantidad(), v.getTotal(), v.getEstado(), v.getMetodoPago(), v.getFecha(), v.getFkLibro(), v.getFkUsuario(), "Modificar la Venta", "Eliminar la Venta"};

            data[i] = obj;
        }

        JTable table = new JTable(data, cols);

        table.getColumn("Modificar la Venta").setCellRenderer(new ButtonRenderer());
        table.getColumn("Modificar la Venta").setCellEditor(new ButtonEditor(new JCheckBox()));

        table.getColumn("Eliminar la Venta").setCellRenderer(new ButtonRenderer());
        table.getColumn("Eliminar la Venta").setCellEditor(new ButtonEditor(new JCheckBox()));
        scrollPane.setViewportView(table);
    }
}
