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
                case "Modificar Venta" -> {
                    Empleado.getSession().modificarVenta((int) jtable.getValueAt(clickedRow, 0), (int) jtable.getValueAt(clickedRow, 1), (float) jtable.getValueAt(clickedRow, 2), (String) jtable.getValueAt(clickedRow, 3), (String) jtable.getValueAt(clickedRow, 4), (Date) jtable.getValueAt(clickedRow, 5), (int) jtable.getValueAt(clickedRow, 6), (int) jtable.getValueAt(clickedRow, 7));
                    StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);
                }
                case "Eliminar Venta" -> {
                    Empleado.getSession().eliminarVenta((int) jtable.getValueAt(clickedRow, 0));
                    StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);
                }
                case "Modificar Usuario" -> {
                    Empleado.getSession().modificarCliente((int) jtable.getValueAt(clickedRow, 0), (String) jtable.getValueAt(clickedRow, 1), (String) jtable.getValueAt(clickedRow, 2), (String) jtable.getValueAt(clickedRow, 3), (String) jtable.getValueAt(clickedRow, 4), (String) jtable.getValueAt(clickedRow, 5));
                    StateManager.setPagina(StateManager.paginasEmpleo.USUARIOS);
                }
                case "Eliminar Usuario" -> {
                    Empleado.getSession().eliminarCliente((int) jtable.getValueAt(clickedRow, 0));
                    StateManager.setPagina(StateManager.paginasEmpleo.USUARIOS);
                }
                case "Modificar Libro" -> {
                    Empleado.getSession().modificarLibro((int) jtable.getValueAt(clickedRow, 0), (String) jtable.getValueAt(clickedRow, 1), (float) jtable.getValueAt(clickedRow, 2), (int) jtable.getValueAt(clickedRow, 3), (String) jtable.getValueAt(clickedRow, 4), (String) jtable.getValueAt(clickedRow, 5), (String) jtable.getValueAt(clickedRow, 6), (int) jtable.getValueAt(clickedRow, 7), (float) jtable.getValueAt(clickedRow, 8), (int) jtable.getValueAt(clickedRow, 9));
                    StateManager.setPagina(StateManager.paginasEmpleo.LIBROS);
                }
                case "Eliminar Libro" -> {
                    Empleado.getSession().eliminarLibro((int) jtable.getValueAt(clickedRow, 0));
                    StateManager.setPagina(StateManager.paginasEmpleo.LIBROS);
                }
                case "Modificar Comentario" -> {
                    Empleado.getSession().modificarComentario((int) jtable.getValueAt(clickedRow, 0), (int) jtable.getValueAt(clickedRow, 1), (String) jtable.getValueAt(clickedRow, 2), (int) jtable.getValueAt(clickedRow, 3), (int) jtable.getValueAt(clickedRow, 4));
                    StateManager.setPagina(StateManager.paginasEmpleo.COMENTARIOS);
                }
                case "Eliminar Comentario" -> {
                    Empleado.getSession().eliminarComentario((int) jtable.getValueAt(clickedRow, 0));
                    StateManager.setPagina(StateManager.paginasEmpleo.COMENTARIOS);
                }
                case "Modificar Categoria" -> {
                    Empleado.getSession().modificarCategoria((int) jtable.getValueAt(clickedRow, 0), (String) jtable.getValueAt(clickedRow, 1));
                    StateManager.setPagina(StateManager.paginasEmpleo.CATEGORIAS);
                }
                case "Eliminar Categoria" -> {
                    Empleado.getSession().eliminarCategoria((int) jtable.getValueAt(clickedRow, 0));
                    StateManager.setPagina(StateManager.paginasEmpleo.CATEGORIAS);
                }
            }

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
