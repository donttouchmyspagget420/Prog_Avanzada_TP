package GUI;

import BLL.Libro;
import BLL.Popularidad;
import Utils.PlatformManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class HomeFrame extends JFrame {
    private static final String NAME = "HomeFrame";

    private SideBar sidebar;
    private JPanel imgsWrapper;

    private int columns = 3;

    protected HomeFrame() {
        JPanel wrapper = new JPanel();
        JLabel label = new JLabel("Los Libros mas populares");
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        sidebar = new SideBar();

        imgsWrapper = new JPanel(new GridLayout(0, columns));

        setLayout(new BorderLayout());

        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        showImages();

        wrapper.add(label);
        wrapper.add(imgsWrapper);

        add(sidebar, BorderLayout.LINE_START);
        add(wrapper, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setName(NAME);

        setSize(1000, 1000);
        setResizable(false);
    }


    private void showImages() {
        /*ArrayList<Libro> libros = Popularidad.getLibros();

        if (libros == null || libros.size() == 0) {
            imgsWrapper.add(new JPanel());
            imgsWrapper.add(new JLabel("No hay libros"));
            imgsWrapper.add(new JPanel());
            return;
        }

        for (Libro libro : libros) {
            imgsWrapper.add(new BookCover(libro.getPortada()));
        }
         */
        Libro libro = new Libro(0, "img.jpg", 69.99F, 10, "aura monster", "jomama", "", 69, 69, 6.9F, 69, 69);
        for (int i = 0; i < 9; i++) {
            imgsWrapper.add(new BookCover(libro));
        }

    }

}