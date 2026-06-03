package GUI;

import BLL.Cliente;
import BLL.Empleado;
import BLL.Usuario;
import Utils.PlatformManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class SideBar extends JPanel {
    private JPanel wrapper, userWrapper;

    protected SideBar() {
        wrapper = new JPanel();

        userWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        userWrapper.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));

        setSize(getWidth() / 3, getHeight());
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.LIGHT_GRAY));
        setLayout(new BorderLayout());

        if (Empleado.getSession() == null) itemsUpdate();
        else itemsUpdateEmpleo();

        this.add(wrapper, BorderLayout.CENTER);
        this.add(userWrapper, BorderLayout.NORTH);
    }

    private void itemsUpdate() {
        StateManager.paginas options[] = StateManager.paginas.values();

        Color bgColor = this.getBackground();

        for (StateManager.paginas option : options) {
            if (StateManager.paginas.PROFILE == option) {
                userGet();
                continue;
            }

            ButtonLink btn = new ButtonLink(option.getFrameName(), bgColor, BorderFactory.createEmptyBorder(10, 0, 10, 100));

            wrapper.add(btn);
        }

        ButtonLink btn = new ButtonLink("Quitar", bgColor, BorderFactory.createEmptyBorder(10, 0, 10, 100));
        wrapper.add(btn);
        JToggleButton mode = new JToggleButton("modo oscuro", true);

        mode.addActionListener(e -> {
            PlatformManager.toggleMode(mode.isSelected());
        });

        wrapper.add(mode);
    }


    private void itemsUpdateEmpleo() {
        StateManager.paginasEmpleo options[] = StateManager.paginasEmpleo.values();

        Color bgColor = this.getBackground();

        for (StateManager.paginasEmpleo option : options) {
            if (StateManager.paginasEmpleo.PROFILE == option) {
                userGet();
                continue;
            }

            ButtonLink btn = new ButtonLink(option.getFrameName(), bgColor, BorderFactory.createEmptyBorder(10, 0, 10, 100));

            wrapper.add(btn);
        }

        ButtonLink btn = new ButtonLink("Quitar", bgColor, BorderFactory.createEmptyBorder(10, 0, 10, 100));
        wrapper.add(btn);

        JToggleButton mode = new JToggleButton("modo oscuro", true);

        mode.addActionListener(e -> {
            PlatformManager.toggleMode(mode.isSelected());
        });

        wrapper.add(mode);
    }

    private void userGet() {
        Usuario user = null;

        if (Empleado.getSession() != null) user = Empleado.getSession();
        if (Cliente.getSession() != null) user = Cliente.getSession();

        ImagePanel pic = new ImagePanel(user.getPfp());
        ButtonLink name = new ButtonLink(user.getCorreo());

        pic.setPreferredSize(new Dimension(35, 35));

        userWrapper.add(pic);
        userWrapper.add(name);

        name.addActionListener(e -> {
            if (Empleado.getSession() != null) {
                StateManager.setPagina(StateManager.paginasEmpleo.PROFILE);
            } else {
                StateManager.setPagina(StateManager.paginas.PROFILE);
            }
        });
    }
}
