package GUI;

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

        StateManager.setPagina(StateManager.paginas.START);
    }
}
