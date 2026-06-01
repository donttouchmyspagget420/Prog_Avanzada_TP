package GUI;

import BLL.Empleado;

import javax.swing.*;
import java.awt.*;

public abstract class Dialog extends JDialog {
    protected JButton submit, close;
    protected JPanel wrapper;

    protected int cols = 2;

    Dialog(Frame parent) {
        super(parent, true);

        close = new JButton("Cerrar");
        wrapper = new JPanel(new GridLayout(0, cols, 20, 20));
        submit = new JButton("Enviar");

        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        close.addActionListener(e -> {
            this.dispose();
        });


    }
}
