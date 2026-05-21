package GUI;

import javax.swing.*;
import java.awt.*;

class HomeFrame extends JFrame {
    private static final String NAME = "HomeFrame";

    private static SideBar sidebar;
    private static JPanel imgsWrapper;

    protected HomeFrame() {
        JPanel wrapper = new JPanel();
        JLabel label = new JLabel("The best books");

        sidebar = new SideBar();

        imgsWrapper = new JPanel(new GridLayout(0, 3));

        showImages();

        setLayout(new BorderLayout());

        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        wrapper.add(label);
        wrapper.add(imgsWrapper);

        add(sidebar, BorderLayout.LINE_START);
        add(wrapper, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setName(NAME);
    }


    private void showImages() {
        //ArrayList<Libro> libros = Popularidad.getLibros();
        for (int i = 1; i <= 6; i++) {
            imgsWrapper.add(new BookCover());
        }
    }
}
