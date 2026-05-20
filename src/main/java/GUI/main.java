package GUI;

import BLL.Cliente;
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
        Cliente.setSession(new Cliente(4, "correo@com", "user", "Qwerty69", "resources/imgs/users/default.png", ""));
        StateManager.setPagina(StateManager.paginas.HOME);
    }
}
