package BLL;

import DLL.ControllerEmpleado;
import GUI.StateManager;
import Utils.Validator;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Empleado extends Usuario {
    private static ControllerEmpleado controller = new ControllerEmpleado();
    private static Empleado session = null;

    public static Empleado getSession() {
        return session;
    }

    public static void setSession(Empleado empleado) {
        session = empleado;
    }

    public Empleado(int id, String correo, String username, String contrasena, String pfp, String sobre) {
        super(id, correo, username, contrasena, pfp, sobre);
    }

    public static int login(String correo, String contrasena) {
        if (!Validator.emailValidate(correo)) {
            JOptionPane.showMessageDialog(null, "correo no es valido");
            return -1;
        }

        try {
            session = controller.loginBase(correo, contrasena);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede loguearse, razon\n" + e.getMessage());
            return -1;
        }

        if (session == null) return -1;

        StateManager.setPagina(StateManager.paginas.HOME);
        JOptionPane.showMessageDialog(null, "iniciado seccion corectamente!");

        return 0;
    }

    public int crearVenta(int cantidad, float total, String estado, String metodoPago, Date fecha, int fkLibro, int fkUsuario) {
        int res;
        try {
            res = controller.crearVentaBase(cantidad, total, estado, metodoPago, fecha, fkLibro, fkUsuario);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede crear la venta, razon\n" + e.getMessage());
            return -1;
        }

        return res;
    }

    public int modificarVenta(int ventaId, int cantidad, float total, String estado, String metodoPago, Date fecha, int fkLibro, int fkUsuario) {
        int res;
        try {
            res = controller.modificarVentaBase(ventaId, cantidad, total, estado, metodoPago, fecha, fkLibro, fkUsuario);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede modificar la venta, razon\n" + e.getMessage());
            return -1;
        }

        return res;
    }

    public int eliminarVenta(int ventaId) {
        int res;
        try {
            res = controller.eliminarVentaBase(ventaId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede eliminar la venta, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int crearLibro(String portada, float precio, int stock, String titulo, String descripcion, String contenido, int paginas, float clasificacion, int fkCategoria) {
        int res;
        try {
            res = controller.crearLibroBase(portada, precio, stock, titulo, descripcion, contenido, paginas, clasificacion, fkCategoria);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede crear el libro, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int modificarLibro(int libroId, String portada, float precio, int stock, String titulo, String descripcion, String contenido, int paginas, float clasificacion, int fkCategoria) {
        int res;
        try {
            res = controller.modificarLibroBase(libroId, portada, precio, stock, titulo, descripcion, contenido, paginas, clasificacion, fkCategoria);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede modificar el libro, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int eliminarLibro(int libroId) {
        int res;
        try {
            res = controller.eliminarLibroBase(libroId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede eliminar el libro, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int crearCliente(String correo, String username, String contrasena, String pfp, String sobre) {
        int res;
        try {
            res = controller.crearClienteBase(correo, username, contrasena, pfp, sobre);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede crear el cliente, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int modificarCliente(int userId, String correo, String username, String contrasena, String pfp, String sobre) {
        int res;
        try {
            res = controller.modificarClienteBase(userId, correo, username, contrasena, pfp, sobre);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede modificar el cliente, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int eliminarCliente(int userId) {
        int res;
        try {
            res = controller.eliminarClienteBase(userId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede eliminar el cliente, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int crearComentario(int clasificacion, String contenido, int fkAutor, int fkLibro) {
        int res;
        try {
            res = controller.crearComentarioBase(clasificacion, contenido, fkAutor, fkLibro);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede crear el comentario, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int modificarComentario(int commId, int clasificacion, String contenido, int fkAutor, int fkLibro) {
        int res;
        try {
            res = controller.modificarComentarioBase(commId, clasificacion, contenido, fkAutor, fkLibro);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede modificar el comentario, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int eliminarComentario(int commId) {
        int res;
        try {
            res = controller.eliminarComentarioBBase(commId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede eliminar el comentario, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int crearCategoria(String nombre) {
        int res;
        try {
            res = controller.crearCategoriaBase(nombre);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede crear el categoria, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int modificarCategoria(int catId, String nombre) {
        int res;
        try {
            res = controller.modificarCategoriaBase(catId, nombre);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede modificar el categoria, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }

    public int eliminarCategoria(int catId) {
        int res;
        try {
            res = controller.eliminarCategoriaBBase(catId);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "no puede eliminar el categoria, razon\n" + e.getMessage());
            return -1;
        }
        return res;
    }
}
