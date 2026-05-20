package GUI;

import javax.swing.*;

public class TextField extends JTextField {
    protected TextField(String text) {
        super(20);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 1));
        putClientProperty("JTextField.placeholderText", text);
    }
}
