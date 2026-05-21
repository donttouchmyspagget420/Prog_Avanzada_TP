package GUI;

import BLL.Libro;
import BLL.Popularidad;
import Utils.PlatformManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class HomeFrame extends JFrame {
    private static final String NAME = "HomeFrame";

    private static SideBar sidebar;
    private static JPanel imgsWrapper;

    protected HomeFrame() {
        JPanel wrapper = new JPanel();
        JLabel label = new JLabel("Los Libros mas populares");
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        sidebar = new SideBar();

        imgsWrapper = new JPanel(new GridLayout(0, 3));

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
        for (int i = 0; i < 9; i++) {
            imgsWrapper.add(new BookCover(PlatformManager.getPathImgs() + "img.jpg"));
        }
        System.out.println(PlatformManager.getPathImgs() + "img.jpg");
    }
}
