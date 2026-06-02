package GUI;

import BLL.Cliente;
import BLL.Empleado;
import Utils.Hash;
import Utils.LayoutUtils;
import Utils.PlatformManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ProfileEditFrame extends JFrame {
    private JPanel profileContent;
    private Cliente cliente;

    private JTextField username, correo, password, newPassword;
    private JTextArea sobre;
    private ImagePanel img;
    private JPanel imgWrapper;

    private final int COLUMNS = 3;

    protected ProfileEditFrame() {
        cliente = Cliente.getSession();

        SideBar sideBar = new SideBar();
        JPanel profileWrapper = new JPanel();

        JPanel profileHead = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        JButton upload = new JButton("Subir");
        JPanel textWeapper = new JPanel();

        imgWrapper = new JPanel(new BorderLayout());
        img = new ImagePanel(cliente.getPfp());
        username = new JTextField(cliente.getUsername());
        correo = new JTextField(cliente.getCorreo());
        password = new JTextField("contraseña vieja");
        newPassword = new JTextField("contraseña nueva");

        profileContent = new JPanel();

        JScrollPane scrollPane = new JScrollPane(profileContent);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        profileWrapper.setLayout(new BorderLayout(0, 20));
        textWeapper.setLayout(new BoxLayout(textWeapper, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());

        username.setFont(new Font("comic sans", Font.BOLD, 35));
        correo.setFont(new Font("comic sans", Font.BOLD, 25));

        scrollPane.setBorder(null);

        sobre();

        imgWrapper.add(img, BorderLayout.NORTH);
        imgWrapper.add(upload, BorderLayout.CENTER);

        profileHead.add(imgWrapper);
        profileHead.add(textWeapper);

        textWeapper.add(username);
        textWeapper.add(correo);
        textWeapper.add(password);
        textWeapper.add(newPassword);

        profileWrapper.add(profileHead, BorderLayout.NORTH);
        profileWrapper.add(scrollPane, BorderLayout.CENTER);

        add(sideBar, BorderLayout.LINE_START);
        add(profileWrapper, BorderLayout.CENTER);

        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        img.setPreferredSize(new Dimension(200, 200));

        upload.addActionListener(e -> {
            img.setImg(PlatformManager.uploadImg());
        });
    }

    private void sobre() {
        LayoutUtils.removeAllComponents(profileContent);

        JLabel title = new JLabel("Sobre Mí");
        sobre = new JTextArea(cliente.getSobre());
        JButton enviar = new JButton("enviar");

        title.setFont(new Font("comic sans", Font.BOLD, 25));
        sobre.setFont(new Font("comic sans", Font.PLAIN, 16));

        sobre.setLineWrap(true);

        title.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));

        profileContent.setLayout(new BorderLayout());

        profileContent.add(title, BorderLayout.NORTH);
        profileContent.add(sobre, BorderLayout.CENTER);
        profileContent.add(enviar, BorderLayout.SOUTH);

        enviar.addActionListener(e -> {
            int res = cliente.cambiarProfile(password.getText(), correo.getText(), username.getText(), newPassword.getText(), img.getPath(), sobre.getText());

            if (res == 0) {
                StateManager.setPagina(StateManager.paginas.PROFILE);
                this.dispose();
            }
        });
    }
}
