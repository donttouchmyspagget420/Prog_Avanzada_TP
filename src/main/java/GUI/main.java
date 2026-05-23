package GUI;

import BLL.Cliente;
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
        Cliente.setSession(new Cliente(4, "correo@com", "user", "Qwerty69", "resources/imgs/users/default.png", ""));
        new BookFrame(new Libro(0, "img.jpg", 69.99F, 10, "aura monster", "jomama", "", 69, 69, 6.9F, 69, 69));
    }
}
