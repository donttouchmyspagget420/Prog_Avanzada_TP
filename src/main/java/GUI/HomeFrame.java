package GUI;

import javax.swing.*;
import java.awt.*;

class HomeFrame extends JFrame {
    private static final String NAME = "HomeFrame";

    private static SideBar sidebar;
    private static JPanel imgsWrapper;

    protected HomeFrame() {
        setLayout(new BorderLayout());

        sidebar = new SideBar();

        imgsWrapper = new JPanel(new GridLayout(0, 5));

        showImages();

        this.add(imgsWrapper, BorderLayout.CENTER);
        this.add(sidebar, BorderLayout.LINE_START);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        setName(NAME);
    }


    private void showImages() {
        //ArrayList<Libro> libros = Popularidad.getLibros();
        BookCover imgPanel0 = new BookCover();
        BookCover imgPanel1 = new BookCover();
        BookCover imgPanel2 = new BookCover();
        BookCover imgPanel3 = new BookCover();
        BookCover imgPanel4 = new BookCover();


        imgsWrapper.add(imgPanel0);
        imgsWrapper.add(imgPanel1);
        imgsWrapper.add(imgPanel2);
        imgsWrapper.add(imgPanel3);
        imgsWrapper.add(imgPanel4);
    }
}
