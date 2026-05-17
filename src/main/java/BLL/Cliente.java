package BLL;

import DLL.ControllerCliente;
import GUI.StateManager;
import Utils.Validator;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class Cliente extends Usuario {

    private static ControllerCliente controller = new ControllerCliente();
    public static Cliente session = null;

    public Cliente(int id, String correo, String username, String contrasena) {
        super(id, correo, username, contrasena);
    }


    public Cliente(int id, String correo, String username, String contrasena, String pfp, String about) {
        super(id, correo, username, contrasena, pfp, about);
    }

    public static int iniciarSeccion(String correo, String username, String contrasena, String contrasenaRepetido) {
        if (Validator.emailValidate(correo)) {
            JOptionPane.showMessageDialog(null, "correo no es valido");
            return -1;
        }

        if (Validator.usernameValidate(username)) {
            JOptionPane.showMessageDialog(null, "nombre de usuario debe ser de 3 a 20 caracteres,debe contener");
            return -1;
        }

        if (Validator.passwordValidate(contrasena)) {
            JOptionPane.showMessageDialog(null, "contraseña debe ser minimo 8 caracteres y contener una MAYUSCULA, una minuscula y un numero");
            return -1;
        }

        try {
            session = controller.iniciarSeccionBase(correo, username, contrasena);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede iniciar seccion, razon:\n" + e.getMessage());
            return -1;
        }

        if (session == null) return -1;

        StateManager.setPagina(StateManager.paginas.HOME);
        JOptionPane.showMessageDialog(null, "iniciado seccion corectamente!");

        return 0;
    }

    public static int login(String correo, String contrasena) {
        if (Validator.emailValidate(correo)) {
            JOptionPane.showMessageDialog(null, "correo no es valido");
            return -1;
        }

        try {
            session = controller.loginBase(correo, contrasena);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede logearse, razon:\n" + e.getMessage());
            return -1;
        }

        if (session == null) return -1;

        StateManager.setPagina(StateManager.paginas.HOME);
        JOptionPane.showMessageDialog(null, "logueado corectamente!");

        return 0;
    }


}
