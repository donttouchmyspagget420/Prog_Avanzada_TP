package BLL;

import DLL.ControllerCliente;
import DLL.ControllerEmpleado;
import GUI.StateManager;
import Utils.Hash;
import Utils.Validator;

import javax.swing.JOptionPane;
import java.sql.SQLException;

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

    public static Cliente getClienteById(int profileId) {
        try {
            return controller.getClienteByIdBase(profileId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede obtener informacion, razon:\n" + e.getMessage());
            return null;
        }
    }

    public int cambiarProfile(String contrasena, String correo, String username, String nuevaContrasena, String pfp, String sobre) {
        try {
            if (!Validator.emailValidate(correo)) throw new Exception("correo no es valido");

            if (!Validator.usernameValidate(username))
                throw new Exception("nombre de usuario debe ser de 3 a 20 caracteres,debe contener");

            if (!Validator.passwordValidate(nuevaContrasena))
                throw new Exception("contraseña debe ser minimo 8 caracteres y contener una MAYUSCULA, una minuscula y un numero");

            if (!Hash.verificar(contrasena, this.getContrasena())) throw new Exception("la contraseña no es correcta");

            if (ControllerEmpleado.modificarClienteBase(session.getId(), correo, username, nuevaContrasena, pfp, sobre) <= 0)
                throw new Exception("Los datos son incorrectos");

            session = controller.getClienteByIdBase(session.getId());

            if (session == null) throw new Exception("Los datos son incorrectos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no puede cambiar el perfil, rezon: " + e.getMessage());
            return -1;
        }

        return 0;
    }
}
