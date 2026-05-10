package BLL;

import DLL.ControllerCliente;
import GUI.StateManager;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class Cliente extends Usuario {

    private static ControllerCliente controller = new ControllerCliente();
    public static Cliente session = null;

    public Cliente(int id, String correo, String username, String contrasena, String pfp, String sobre) {
        super(id, correo, username, contrasena, pfp, sobre);
    }

    public int iniciarSeccion(String correo, String username, String contrasena, String pfp, String sobre) {
        try {
            session = controller.iniciarSeccionBase(correo, username, contrasena, pfp, sobre);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede iniciar seccion, razon:\n" + e.getMessage());
            return -1;
        }

        StateManager.proximo();
        return 0;
    }

    public int login(String correo, String contrasena) {
        try {
            session = controller.loginBase(correo, contrasena);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No puede logearse, razon:\n" + e.getMessage());
            return -1;
        }

        StateManager.proximo();
        return 0;
    }


}
