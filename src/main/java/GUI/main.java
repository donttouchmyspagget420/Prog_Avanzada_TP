package GUI;

import BLL.Cliente;
import BLL.Empleado;
import BLL.Libro;
import Utils.PlatformManager;
import com.formdev.flatlaf.FlatDarculaLaf;

public class main {
    public static void main(String[] args) {
        PlatformManager.getSystemInfo();

        try {
            FlatDarculaLaf.setup();
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF:");
            System.err.println(e.getMessage());
        }

        // temperary dev option #DELETE BEFORE PROD
        Cliente.setSession(new Cliente(4, "correo@com", "user", "$2a$10$xmWxpZ02ohS9CWbl5QQgEetPT0TiExAfy0cm9QYvBEJ0j9yK3Gi1O", "default.png", ""));
        StateManager.setPagina(StateManager.paginas.CATALOG);
    }
}
