package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class SideBar extends JPanel {
    private static JPanel wrapper;

    protected SideBar() {
        wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        setSize(getWidth() / 3, getHeight());
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.LIGHT_GRAY));

        this.add(wrapper);
        itemsUpdate();

    }

    private void itemsUpdate() {
        StateManager.paginas[] options = StateManager.paginas.values();
        Color bgColor = this.getBackground();

        for (StateManager.paginas option : options) {
            ButtonLink btn = new ButtonLink(option.getFrameName(), bgColor, BorderFactory.createEmptyBorder(10, 0, 10, 100));

            wrapper.add(btn);
        }

        ButtonLink btn = new ButtonLink("Quitar", bgColor, BorderFactory.createEmptyBorder(10, 0, 10, 100));
        wrapper.add(btn);
    }
}
