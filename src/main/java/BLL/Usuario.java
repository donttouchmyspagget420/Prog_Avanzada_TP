package BLL;

import DLL.ControllerCliente;
import DLL.ControllerEmpleado;
import Utils.Hash;
import Utils.Validator;

import javax.swing.*;

public abstract class Usuario {
    private int id;
    private String correo;
    private String username;
    private String contrasena;
    private String pfp;
    private String sobre;

    protected Usuario(int id, String correo, String username, String contrasena) {
        this.id = id;
        this.correo = correo;
        this.username = username;
        this.contrasena = contrasena;
        this.pfp = null;
        this.sobre = null;
    }

    public Usuario(int id, String correo, String username, String contrasena, String pfp, String about) {
        this.id = id;
        this.correo = correo;
        this.username = username;
        this.contrasena = contrasena;
        this.pfp = pfp;
        this.sobre = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getPfp() {
        return pfp;
    }

    public String getSobre() {
        return sobre;
    }

    public int cambiarProfile(String contrasena, String correo, String username, String nuevaContrasena, String pfp, String sobre, int id, boolean empleado) {
        ControllerCliente controller = new ControllerCliente();
        try {
            if (!Validator.emailValidate(correo)) throw new Exception("correo no es valido");

            if (!Validator.usernameValidate(username))
                throw new Exception("nombre de usuario debe ser de 3 a 20 caracteres,debe contener");

            if (!Validator.passwordValidate(nuevaContrasena))
                throw new Exception("contraseña debe ser minimo 8 caracteres y contener una MAYUSCULA, una minuscula y un numero");

            if (!Hash.verificar(contrasena, this.getContrasena())) throw new Exception("la contraseña no es correcta");

            if (ControllerEmpleado.modificarClienteBase(id, correo, username, nuevaContrasena, pfp, sobre) <= 0)
                throw new Exception("Los datos son incorrectos");

            System.out.println(empleado);

            if (!empleado) {
                Cliente.setSession((Cliente) controller.getClienteByIdBase(id, false));
            } else {
                Empleado.setSession((Empleado) controller.getClienteByIdBase(id, true));
            }

            if (Empleado.getSession() == null && Cliente.getSession() == null)
                throw new Exception("Los datos son incorrectos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no puede cambiar el perfil, rezon: " + e.getMessage());
            return -1;
        }

        return 0;
    }
}
