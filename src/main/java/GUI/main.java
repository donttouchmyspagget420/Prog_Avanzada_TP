package GUI;

import com.formdev.flatlaf.FlatDarculaLaf;

public class main {
    public static void main(String[] args) {
        try {
            FlatDarculaLaf.setup();
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF:");
            System.err.println(e.getMessage());
        }

        StartFrame startFrame = new StartFrame();
    }
}
