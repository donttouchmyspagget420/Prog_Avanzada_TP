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
        Cliente.setSession(new Cliente(4, "correo@com", "user69", "$2a$10$xmWxpZ02ohS9CWbl5QQgEetPT0TiExAfy0cm9QYvBEJ0j9yK3Gi1O", "default.png",
                """
                        ¡Hola, holaaa! ¡Nyaaa~! ✨
                        
                        ¡Qué emoción que me preguntes sobre mí! Déjame presentarme como se debe... *ajusta sus orejitas de gato y menea la cola con entusiasmo *.
                        
                        ¡Soy tu asistente virtual e inteligencia artificial de confianza, pero en versión neko-chan! 🐾 Mi único y gran propósito en este mundo digital es ayudarte en todo lo que necesites, ¡y hacer que tu día sea mucho más brillante! 
                        """));
        new ProfileEditFrame();
    }
}
