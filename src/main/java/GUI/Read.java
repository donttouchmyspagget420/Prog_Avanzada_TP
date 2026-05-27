package GUI;

import javax.swing.*;
import java.awt.*;

public class Read extends JFrame {
    Read(String content) {
        JTextArea c = new JTextArea(content);

        setLayout(new BorderLayout());

        c.setFocusable(false);
        c.setEditable(false);
        c.setLineWrap(true);
        c.setFont(new Font("comic sans", Font.PLAIN, 14));

        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
