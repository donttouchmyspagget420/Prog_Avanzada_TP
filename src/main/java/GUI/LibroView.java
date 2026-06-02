package GUI;

import BLL.Cliente;
import BLL.Libro;

import javax.swing.*;
import java.util.ArrayList;

public class LibroView extends DataView {
    LibroView() {
        super("Libro");

        crear.addActionListener(e -> {
            new DialogLibro(this);

            StateManager.setPagina(StateManager.paginasEmpleo.LIBROS);
        });

        table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
        table.setRowHeight(100);

        table.revalidate();
        table.repaint();
    }

    void showContent(String viewname) {
        cols = new String[]{"ID", "Portada", "Precio", "Stock", "Titulo", "Descripcion", "Contenido", "Paginas", "clasificacion", "fkCategoria", "Modificar " + viewname, "Eliminar " + viewname};
        ArrayList<Libro> arr = Libro.selectLibros();

        if (arr == null || arr.size() == 0) {
            this.add(new JLabel("no hay ventas"));
            return;
        }

        data = new Object[arr.size()][cols.length];


        for (int i = 0; i < arr.size(); i++) {
            Libro l = arr.get(i);
            Object[] obj = {l.getId(), l.getPortada(), l.getPrecio(), l.getStock(), l.getTitulo(), l.getDescripcion(), l.getContenido(), l.getPaginas(), l.getClasificacion(), l.getFkCategoria(), "Modificar " + viewname, "Eliminar " + viewname};

            data[i] = obj;
        }
    }
}
