package GUI;

import BLL.Empleado;
import BLL.Venta;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;

public class ButtonEditor extends DefaultCellEditor {
    private JTable jtable;
    protected JButton button;
    private String label;
    private boolean isPushed;
    private int clickedRow;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        jtable = table;
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        clickedRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            String comm = button.getActionCommand();

            switch (comm) {
                case "Modificar la Venta" ->
                        Empleado.getSession().modificarVenta((int) jtable.getValueAt(clickedRow, 0), (int) jtable.getValueAt(clickedRow, 1), (float) jtable.getValueAt(clickedRow, 2), (String) jtable.getValueAt(clickedRow, 3), (String) jtable.getValueAt(clickedRow, 4), (Date) jtable.getValueAt(clickedRow, 5), (int) jtable.getValueAt(clickedRow, 6), (int) jtable.getValueAt(clickedRow, 7));
                case "Eliminar la Venta" -> Empleado.getSession().eliminarVenta((int) jtable.getValueAt(clickedRow, 0));
            }

            StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);

        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
