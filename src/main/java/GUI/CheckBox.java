package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CheckBox extends JCheckBox {
    protected CheckBox(String text, Boolean selected) {
        super(text, selected);

        this.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
}
