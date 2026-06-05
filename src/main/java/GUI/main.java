package GUI;

import BLL.Cliente;
import BLL.Empleado;
import BLL.Libro;
import DLL.Database;
import Utils.Hash;
import Utils.PlatformManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        PlatformManager.getSystemInfo();
        Database.getEnv();

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF:");
            System.err.println(e.getMessage());
        }

        new Database();
        StateManager.setPagina(StateManager.paginas.START);
    }
}
