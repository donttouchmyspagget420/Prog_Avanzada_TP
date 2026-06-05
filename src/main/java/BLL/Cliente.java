package BLL;

import DLL.ControllerCliente;
import DLL.ControllerEmpleado;
import GUI.StateManager;
import Utils.Hash;
import Utils.Validator;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente extends Usuario {

    private static ControllerCliente controller = new ControllerCliente();
    private static Cliente session = null;

    public Cliente(int id, String correo, String username, String contrasena) {
        super(id, correo, username, contrasena);
    }

    public static Cliente getSession() {
        return session;
    }

    public static void setSession(Cliente cliente) {
        session = cliente;
    }


    public Cliente(int id, String correo, String username, String contrasena, String pfp, String about) {
        super(id, correo, username, contrasena, pfp, about);
    }

    public static int iniciarSeccion(String correo, String username, String contrasena, String contrasenaRepetido) {
        if (!Validator.emailValidate(correo)) {
            JOptionPane.showMessageDialog(null, "correo no es valido");
            return -1;
        }

        if (!Validator.usernameValidate(username)) {
            JOptionPane.showMessageDialog(null, "nombre de usuario debe ser de 3 a 20 caracteres,debe contener");
            return -1;
        }

        if (!Validator.passwordValidate(contrasena)) {
            JOptionPane.showMessageDialog(null, "contraseña debe ser minimo 8 caracteres y contener una MAYUSCULA, una minuscula y un numero");
            return -1;
        }

        if (contrasenaRepetido != contrasena) {
            JOptionPane.showMessageDialog(null, "contrasenas no coinciden");
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
        if (!Validator.emailValidate(correo)) {
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

    public static Usuario getClienteById(int profileId, boolean empleado) {
        try {
            return controller.getClienteByIdBase(profileId, empleado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede obtener informacion, razon:\n" + e.getMessage());
            return null;
        }
    }


    public static ArrayList<Cliente> selectClientes() {
        try {
            return controller.selectClientesBase();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede obtener informacion sobre las clientes, razon: " + e.getMessage());
            return null;
        }
    }
}
