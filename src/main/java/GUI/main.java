package GUI;

import BLL.Cliente;
import BLL.Empleado;
import BLL.Libro;
import Utils.Hash;
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
        Empleado.setSession(new Empleado(3, "empleo@yenny.work", "empleo67", "$2a$10$nLWxB4Bcf2YQ90/9YLT2tOo0W70Af.DqxzdN3jLKwV7d68B8gxeCS+", "default.png", ""));
        StateManager.setPagina(StateManager.paginasEmpleo.VENTAS);


    }
}
