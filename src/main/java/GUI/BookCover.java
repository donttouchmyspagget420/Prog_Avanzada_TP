package GUI;

import javax.swing.*;
import java.awt.*;

public class BookCover extends JPanel {
    protected BookCover() {
        ImagePanel cover = new ImagePanel("src/main/resources/imgs/img.jpg");

        JLabel title = new JLabel("aura monster");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(cover);
        this.add(title);
    }
}
