package Utils;

import BLL.Cliente;
import BLL.Empleado;
import DLL.Database;
import GUI.StateManager;
import com.formdev.flatlaf.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

public abstract class PlatformManager {
    private static String os, separator, pathImgs;

    public static void getSystemInfo() {
        try {
            os = System.getProperty("os.name"); //telemetry(TODO: vender datos a MICROSOFT)
            separator = System.getProperty("file.separator");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(-1);
        }
        pathImgs = "imgs" + separator; //no sabía por seguro como exactamente hacer las rutas cross platform
    }

    public static String getPathImgs() {
        return pathImgs;
    }

    public static String uploadImg() {
        JFileChooser fileChooser = new JFileChooser();
        int res = fileChooser.showOpenDialog(null);

        if (res != JFileChooser.APPROVE_OPTION) return null;

        Path file = fileChooser.getSelectedFile().toPath();

        Path save = Paths.get(PlatformManager.getPathImgs());

        Path dest = save.resolve(file.getFileName());

        try {
            Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "no puede subir la imagen, razon:" + ex.getMessage());
            return null;
        }

        JOptionPane.showMessageDialog(null, "subido exictosamente!");

        return dest.toAbsolutePath().getFileName().toString();
    }

    public static HashMap<String, String> parseEnv() {
        Path dotenv = Path.of(".env");
        List<String> lns;

        try {
            lns = Files.readAllLines(dotenv);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "no puede leer el config,razon: " + e.getMessage() + "\n la sistema usara config por defecto");
            return null;
        }

        HashMap<String, String> res = new HashMap<>();

        for (String ln : lns) {
            if (ln.isBlank()) continue;
            ln = ln.replaceAll("\\s+", "");

            if (ln.startsWith("#")) continue;
            String[] kv = ln.split("=");

            res.put(kv[0], kv[1]);
        }

        return res;
    }

    public static void toggleMode(boolean dark) {
        if (dark) {
            try {
                FlatDarkLaf.setup();
            } catch (Exception e) {
                System.err.println("Failed to initialize LaF:");
                System.err.println(e.getMessage());
            }
        } else {
            try {
                FlatLightLaf.setup();
            } catch (Exception e) {
                System.err.println("Failed to initialize LaF:");
                System.err.println(e.getMessage());
            }
        }

        FlatLaf.updateUI();
    }

}
