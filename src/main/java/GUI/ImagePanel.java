package GUI;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;

    protected ImagePanel(String path) {
        img = new ImageIcon(path).getImage();

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, this);
    }
}
