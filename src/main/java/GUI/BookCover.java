package GUI;

import javax.swing.*;
import java.awt.*;

public class BookCover extends JPanel {
    protected BookCover() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        ImageIcon image = new ImageIcon("src/main/resources/imgs/img.jpg");

        Image scaled = image.getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT);

        image = new ImageIcon(scaled);

        JLabel label = new JLabel(image);
        JLabel title = new JLabel("aura monster");


        this.add(label);
        this.add(title);
    }
}
