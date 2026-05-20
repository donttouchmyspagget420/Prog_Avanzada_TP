package GUI;

import javax.swing.border.Border;
import java.awt.*;

public class ButtonLink extends Button {
    ButtonLink(String text, Color bgParent) {
        super(text);

        setBackground(bgParent);
    }

    ButtonLink(String text, Color bgParent, Border border) {
        super(text);

        setBorder(border);
        setBackground(bgParent);
    }
}
