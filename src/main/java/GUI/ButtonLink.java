package GUI;

import javax.swing.border.Border;
import java.awt.*;

public class ButtonLink extends Button {
    ButtonLink(String text) {
        super(text);

        setBackground(null);
    }

    ButtonLink(String text, Color bgParent, Border border) {
        super(text);

        setBorder(border);
        setBackground(bgParent);
    }
}
