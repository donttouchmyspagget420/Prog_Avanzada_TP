package GUI;

import javax.swing.*;
import java.awt.*;

class StartFrame extends JFrame {
    private static final String NAME = "StartFrame";

    private JPanel wrapper, btnWrapper;
    private TextField emailField, usernameField, passwordField, repeatPasswordField;
    private CheckBox empleado;

    private boolean isLoginShown;

    private static StartFrame frame;

    protected StartFrame() {
        wrapper = new JPanel(new GridLayout(0, 1));
        btnWrapper = new JPanel(new GridLayout(0, 2));

        empleado = new CheckBox("soy empleado", false);

        JPanel generalWrapper = new JPanel();
        JPanel nextWrapper = new JPanel(new GridLayout(0, 1));

        generalWrapper.setLayout(new BoxLayout(generalWrapper, BoxLayout.Y_AXIS));

        Button showLogin = new Button("Logearse");
        Button showSignUp = new Button("Registrarse");

        showLogin.addActionListener(EventManager.getInstanse());
        showSignUp.addActionListener(EventManager.getInstanse());

        Button next = new Button("próximo");

        next.addActionListener(EventManager.getInstanse());

        emailField = new TextField("correo");
        passwordField = new TextField("contraseña");

        repeatPasswordField = null;

        btnWrapper.add(showLogin);
        btnWrapper.add(showSignUp);

        wrapper.add(new JPanel());
        wrapper.add(emailField);
        wrapper.add(passwordField);
        wrapper.add(empleado);

        nextWrapper.add(next);

        generalWrapper.add(btnWrapper);
        generalWrapper.add(wrapper);
        generalWrapper.add(nextWrapper);

        this.setLayout(new GridBagLayout());

        this.add(generalWrapper);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        isLoginShown = true;

        setName(NAME);
        setSize(1000, 1000);
        setResizable(false);
    }

    public static StartFrame getFrame() {
        return frame;
    }

    protected void showLogin() {
        if (isLoginShown) return;

        wrapper.remove(usernameField);
        wrapper.remove(repeatPasswordField);

        empleado = new CheckBox("soy empleado", false);
        wrapper.add(empleado);

        isLoginShown = true;
        revalidate();
    }

    protected void showSignUp() {
        if (!isLoginShown || empleado.isSelected()) return;

        usernameField = new TextField("repetir la contraseña");
        repeatPasswordField = new TextField("nombre de usuario");

        wrapper.remove(passwordField);
        wrapper.remove(empleado);

        wrapper.add(usernameField);
        wrapper.add(passwordField);
        wrapper.add(repeatPasswordField);
        repeatPasswordField.setVisible(true);

        isLoginShown = false;
        revalidate();
    }

    protected String getEmail() {
        return emailField.getText();
    }

    protected String getUsername() {
        return usernameField.getText();
    }

    protected String getPassword() {
        return passwordField.getText();
    }

    protected String getRepeatPassword() {
        return repeatPasswordField.getText();
    }

    protected boolean getIsLoginShown() {
        return isLoginShown;
    }

    protected boolean getIsEmpleado() {
        return empleado.isSelected();
    }
}
