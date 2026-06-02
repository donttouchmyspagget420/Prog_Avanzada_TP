package GUI;

import BLL.Categorias;
import BLL.Empleado;

import javax.swing.*;

public class DialogCategoria extends Dialog {
    TextField nombreF;

    DialogCategoria(JFrame parent) {
        super(parent);
        nombreF = new TextField("nombre");

        wrapper.add(nombreF);
        wrapper.add(new JPanel());
        wrapper.add(close);
        wrapper.add(submit);

        add(wrapper);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    @Override
    void action() {
        String nombre;

        try {
            nombre = nombreF.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        if (Empleado.getSession().crearCategoria(nombre) > 0)
            StateManager.setPagina(StateManager.paginasEmpleo.CATEGORIAS);

    }
}
