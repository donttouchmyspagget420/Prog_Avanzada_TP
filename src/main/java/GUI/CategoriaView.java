package GUI;

import BLL.Categorias;
import BLL.Libro;

import java.util.ArrayList;

public class CategoriaView extends DataView {
    CategoriaView() {
        super("Categoria");

        crear.addActionListener(e -> {
            new DialogCategoria(this);
        });
    }

    @Override
    void showContent(String viewname) {
        cols = new String[]{"ID", "Nombre", "Modificar " + viewname, "Eliminar " + viewname};
        ArrayList<Categorias> arr = Categorias.selectCategorias();

        if (arr == null || arr.size() == 0) return;

        data = new Object[arr.size()][cols.length];

        for (int i = 0; i < arr.size(); i++) {
            Categorias c = arr.get(i);
            Object[] obj = {c.getId(), c.getNombre(), "Modificar " + viewname, "Eliminar " + viewname};

            data[i] = obj;
        }
    }
}
