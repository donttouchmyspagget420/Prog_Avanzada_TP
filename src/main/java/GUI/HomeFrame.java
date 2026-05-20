package GUI;

import javax.swing.*;
import java.awt.*;

class HomeFrame extends JFrame {
    private static final String NAME = "HomeFrame";

    private static SideBar sidebar;

    protected HomeFrame() {
        setLayout(new BorderLayout());

        sidebar = new SideBar();

        this.add(sidebar, BorderLayout.LINE_START);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        setName(NAME);
    }
}
