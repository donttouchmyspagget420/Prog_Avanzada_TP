package GUI;

import Utils.PlatformManager;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class ImagePanel extends JPanel {
    private Image img;
    private String path;

    protected ImagePanel(String path) {
        this.path = path;
        ;
        img = new ImageIcon(PlatformManager.getPathImgs() + path).getImage();

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

    protected void setImg(String path) {
        this.path = path;

        img = new ImageIcon(PlatformManager.getPathImgs() + path).getImage();

        repaint();
    }
}
