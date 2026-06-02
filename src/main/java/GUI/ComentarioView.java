package GUI;

import BLL.Comentario;
import BLL.Libro;

import javax.swing.*;
import java.util.ArrayList;

public class ComentarioView extends DataView {
    ComentarioView() {
        super("Comentario");

        crear.addActionListener(e -> {
            new DialogComentario(this);
        });
    }

    @Override
    void showContent(String viewname) {
        cols = new String[]{"ID", "Clasificacion", "Contenido", "IDauthor", "IDlibro", "Modificar " + viewname, "Eliminar " + viewname};
        ArrayList<Comentario> arr = Comentario.selectComentarios();

        if (arr == null || arr.size() == 0) return;

        data = new Object[arr.size()][cols.length];


        for (int i = 0; i < arr.size(); i++) {
            Comentario c = arr.get(i);
            Object[] obj = {c.getId(), c.getClasificacion(), c.getContenido(), c.getFkAutor(), c.getFkLibro(), "Modificar " + viewname, "Eliminar " + viewname};

            data[i] = obj;
        }
    }
}
