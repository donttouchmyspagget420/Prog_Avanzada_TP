package Utils;

import javax.swing.*;

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
        pathImgs = "resources" + separator + "imgs";
    }

    public static String getOs() {
        return os;
    }

    public static String getSeparator() {
        return separator;
    }

    public static String getPathImgs() {
        return pathImgs;
    }
}
