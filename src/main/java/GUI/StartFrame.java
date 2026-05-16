package GUI;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    private JPanel wrapper, btnWrapper;
    private JTextField emailField, usernameField, passwordField, repeatPasswordField;
    private JCheckBox empleado;

    private boolean isLoginShown;

    StartFrame() {
        EventManager.getInstanse().startFrame = this;

        wrapper = new JPanel(new GridLayout(0, 1));
        btnWrapper = new JPanel(new GridLayout(0, 2));

        empleado = new JCheckBox("soy empleado", null, false);

        JPanel generalWrapper = new JPanel();
        JPanel nextWrapper = new JPanel(new GridLayout(0, 1));

        generalWrapper.setLayout(new BoxLayout(generalWrapper, BoxLayout.Y_AXIS));

        JButton showLogin = new JButton("Logearse");
        JButton showSignUp = new JButton("Registrarse");

        showLogin.addActionListener(EventManager.getInstanse());
        showSignUp.addActionListener(EventManager.getInstanse());

        JButton next = new JButton("próximo");

        next.addActionListener(EventManager.getInstanse());

        emailField = new JTextField(20);
        passwordField = new JTextField(20);

        emailField.putClientProperty("JTextField.placeholderText", "correo");
        passwordField.putClientProperty("JTextField.placeholderText", "contraseña");

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
    }

    protected void showLogin() {
        if (isLoginShown) return;

        wrapper.remove(usernameField);
        wrapper.remove(repeatPasswordField);
        if (empleado == null) {
            empleado = new JCheckBox("soy empleado", null, false);
            wrapper.add(empleado);
        }

        isLoginShown = true;
        revalidate();
    }

    protected void showSignUp() {
        if (!isLoginShown || empleado.isSelected()) return;

        usernameField = new JTextField(20);
        repeatPasswordField = new JTextField(20);

        wrapper.remove(passwordField);
        wrapper.remove(empleado);
        empleado = null;

        repeatPasswordField.putClientProperty("JTextField.placeholderText", "repetir la contraseña");
        usernameField.putClientProperty("JTextField.placeholderText", "nombre de usuario");

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
