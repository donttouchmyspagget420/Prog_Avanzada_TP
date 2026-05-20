package GUI;

import BLL.Categorias;

import javax.swing.*;

public class CategoriasFrame extends JFrame {

    private static JComboBox dropdown;
    private static String[] categorias;


    private void showCategorias() {
        categorias = Categorias.getCatagorias().toArray(new String[0]);

        if (categorias == null) return;

        dropdown = new JComboBox<>(categorias);

        dropdown.addActionListener(e -> {
            System.out.println(dropdown.getSelectedItem());
        });
    }
}
