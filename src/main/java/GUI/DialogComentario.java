package GUI;

import BLL.Empleado;

import javax.swing.*;

public class DialogComentario extends Dialog {
    private TextField clasificacionF, fkAutorF, fkLibroF;
    private JTextArea contenidoF;

    DialogComentario(JFrame parent) {
        super(parent);

        clasificacionF = new TextField("clasificacion");
        fkAutorF = new TextField("fk_author");
        fkLibroF = new TextField("fk_libro");

        contenidoF = new JTextArea("contenido");

        wrapper.add(clasificacionF);
        wrapper.add(fkAutorF);
        wrapper.add(fkLibroF);
        wrapper.add(contenidoF);
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
        int clasificacion, fkAuthor, fkLibro;
        String contenido;

        try {
            clasificacion = Integer.valueOf(clasificacionF.getText());
            fkAuthor = Integer.valueOf(fkAutorF.getText());
            fkLibro = Integer.valueOf(fkLibroF.getText());

            contenido = contenidoF.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        if (Empleado.getSession().crearComentario(clasificacion, contenido, fkAuthor, fkLibro) > 0)
            StateManager.setPagina(StateManager.paginasEmpleo.COMENTARIOS);
    }
}
