package GUI;

import javax.swing.*;

public class Button extends JButton {
    protected Button(String text) {
        super(text);

        addActionListener(EventManager.getInstanse());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
