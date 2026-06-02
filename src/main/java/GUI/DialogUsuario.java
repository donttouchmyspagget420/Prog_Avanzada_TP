package GUI;

import BLL.Empleado;
import Utils.PlatformManager;

import javax.swing.*;

public class DialogUsuario extends Dialog {
    private TextField correoF, usernameF, contrasenaF;
    private JTextArea sobreA;
    private JButton uploadB;
    private ImagePanel img;

    DialogUsuario(JFrame parent) {
        super(parent);

        correoF = new TextField("correo");
        usernameF = new TextField("username");
        contrasenaF = new TextField("contrasena");
        sobreA = new JTextArea("sobre");
        uploadB = new JButton("subir la imagen");
        img = new ImagePanel(null);

        wrapper.add(correoF);
        wrapper.add(usernameF);
        wrapper.add(contrasenaF);
        wrapper.add(sobreA);
        wrapper.add(uploadB);
        wrapper.add(img);
        wrapper.add(close);
        wrapper.add(submit);

        add(wrapper);

        uploadB.addActionListener(e -> {
            img.setImg(PlatformManager.uploadImg());
        });

        img.setVisible(false);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    @Override
    protected void action() {
        String correo, username, contrasena, sobre, path;

        correo = correoF.getText();
        username = usernameF.getText();
        contrasena = contrasenaF.getText();
        sobre = sobreA.getText();
        path = img.getPath();


        Empleado.getSession().crearCliente(correo, username, contrasena, path, sobre);

        StateManager.setPagina(StateManager.paginasEmpleo.USUARIOS);
    }
}
