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
        new BookFrame(new Libro(0, "img.jpg", 69.99F, 10, "Classroom of the Elite", "The cutthroat school drama light novels that inspired a manga adaptation and a spin-off series starring fan-favorite character Horikita Suzune (both also from Seven Seas)–and don’t miss the anime! Students of the prestigious Tokyo Metropolitan Advanced Nurturing High School are given remarkable freedom—if they can win, barter, or save enough points to work their way up the ranks! Ayanokouji Kiyotaka has landed at the bottom in the scorned Class D, where he meets Horikita Suzune, who’s determined to rise up the ladder to Class A. Can they beat the system in a school where cutthroat competition is the name of the game?", "", 69, 69, 6.9F, 69, 69));
    }
}
