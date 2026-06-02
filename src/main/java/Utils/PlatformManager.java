package Utils;

import GUI.ImagePanel;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public abstract class PlatformManager {
    private static String os, separator, pathImgs;

    public static void getSystemInfo() {
        try {
            os = System.getProperty("os.name");
            separator = System.getProperty("file.separator");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(-1);
        }
        pathImgs = "src" + separator + "main" + separator + "resources" + separator + "imgs" + separator;
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
}
