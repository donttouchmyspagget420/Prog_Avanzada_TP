package BLL;

public class Empleado extends Usuario {
    protected Empleado(int id, String correo, String username, String contrasena, String pfp, String sobre) {
        super(id, correo, username, contrasena, pfp, sobre);
    }
}
