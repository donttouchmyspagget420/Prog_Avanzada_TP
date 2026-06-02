package GUI;

import BLL.Empleado;
import Utils.PlatformManager;

import javax.swing.*;

public class DialogLibro extends Dialog {
    TextField precioF, stockF, tituloF, paginaF, clasificacionF, fkCategoriaF;
    JTextArea descripcionA, contenidoA;
    ImagePanel portadaP;
    JButton uploadB;

    DialogLibro(JFrame parent) {
        super(parent);

        precioF = new TextField("precio");
        stockF = new TextField("stock");
        tituloF = new TextField("titulo");
        paginaF = new TextField("pagina");
        clasificacionF = new TextField("clasificacion");
        fkCategoriaF = new TextField("ID categoria");

        descripcionA = new JTextArea("descripcion");
        contenidoA = new JTextArea("contenido");

        portadaP = new ImagePanel(null);

        uploadB = new JButton("subir la imagen");

        wrapper.add(precioF);
        wrapper.add(stockF);
        wrapper.add(tituloF);
        wrapper.add(paginaF);
        wrapper.add(clasificacionF);
        wrapper.add(fkCategoriaF);
        wrapper.add(descripcionA);
        wrapper.add(contenidoA);
        wrapper.add(portadaP);
        wrapper.add(uploadB);
        wrapper.add(close);
        wrapper.add(submit);

        add(wrapper);

        uploadB.addActionListener(e -> {
            portadaP.setImg(PlatformManager.uploadImg());
        });


        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    @Override
    void action() {
        int stock, pagina, fkCat;
        String titulo, descripcionm, contenido, portada;
        float precio, clasificacion;

        try {
            stock = Integer.valueOf(stockF.getText());
            pagina = Integer.valueOf(paginaF.getText());
            fkCat = Integer.valueOf(fkCategoriaF.getText());

            titulo = tituloF.getText();
            descripcionm = descripcionA.getText();
            contenido = descripcionA.getText();
            portada = portadaP.getPath();

            precio = Float.valueOf(precioF.getText());
            clasificacion = Float.valueOf(clasificacionF.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        if (Empleado.getSession().crearLibro(portada, precio, stock, titulo, descripcionm, contenido, pagina, clasificacion, fkCat) > 0)
            StateManager.setPagina(StateManager.paginasEmpleo.LIBROS);
    }
}
