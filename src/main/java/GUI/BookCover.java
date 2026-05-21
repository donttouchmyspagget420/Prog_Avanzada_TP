package GUI;

import javax.swing.*;

public class BookCover extends JPanel {
    protected BookCover(String path) {
        ImagePanel cover = new ImagePanel(path);

        JLabel title = new JLabel("aura monster");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.add(cover);
        this.add(title);
    }
}
