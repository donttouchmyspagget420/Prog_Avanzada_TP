package GUI;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;
    private String path;

    protected ImagePanel(String path) {
        this.path = path;
        img = new ImageIcon(path).getImage();

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
    }

    protected String getPath() {
        return path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
