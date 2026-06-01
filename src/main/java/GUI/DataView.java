package GUI;

import BLL.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public abstract class DataView extends JFrame {
    private JScrollPane scrollPane;

    protected String[] cols;
    protected Object[][] data;
    protected ButtonLink crear;
    protected JTable table;

    DataView(String viewName) {
        showContent(viewName);

        SideBar sidebar = new SideBar();
        JPanel wrapper = new JPanel();
        JPanel btnWrapper = new JPanel();

        crear = new ButtonLink("Crear una " + viewName, getBackground());
        if (data != null) table = new JTable(data, cols);
        else {
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            table = new JTable(model);
        }

        scrollPane = new JScrollPane(table);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        setLayout(new BorderLayout());
        wrapper.setLayout(new BorderLayout());

        scrollPane.setBorder(null);

        table.getColumn("Modificar la " + viewName).setCellRenderer(new ButtonRenderer());
        table.getColumn("Modificar la " + viewName).setCellEditor(new ButtonEditor(new JCheckBox()));

        table.getColumn("Eliminar la " + viewName).setCellRenderer(new ButtonRenderer());
        table.getColumn("Eliminar la " + viewName).setCellEditor(new ButtonEditor(new JCheckBox()));

        btnWrapper.add(crear);

        wrapper.add(scrollPane, BorderLayout.CENTER);
        wrapper.add(btnWrapper, BorderLayout.SOUTH);

        add(sidebar, BorderLayout.LINE_START);
        add(wrapper, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 1000);
        setResizable(false);
    }

    abstract void showContent(String viewname);
}
