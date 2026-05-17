package GUI;

import javax.swing.*;

public class SideBar extends JPanel {
    SideBar() {
        EventManager.getInstanse().sideBar = this;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        StateManager.paginas[] options = StateManager.paginas.values();

        for (StateManager.paginas option : options) {
            JButton btn = new JButton(option.getFrameName());

            btn.addActionListener(EventManager.getInstanse());

            this.add(new JPanel());
            this.add(btn);
        }
    }
}
