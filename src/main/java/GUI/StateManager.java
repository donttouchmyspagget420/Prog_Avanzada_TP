package GUI;

import BLL.Cliente;
import BLL.Empleado;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public abstract class StateManager {
    private static JFrame currentPage;

    public enum paginas {
        PROFILE("Perfil"),
        HOME("Home"),
        CATALOG("Catálogo"),
        START("Quitar la cuenta");

        private String frame;

        paginas(String frame) {
            this.frame = frame;
        }

        public String getFrameName() {
            return frame;
        }
    }


    public enum paginasEmpleo {
        PROFILE("Perfil"),
        VENTAS("Ventas"),
        LIBROS("Libros"),
        USUARIOS("Usuarios"),
        COMENTARIOS("Comentarios"),
        CATEGORIAS("Categorias"),
        START("Quitar la cuenta");

        private String frame;

        paginasEmpleo(String frame) {
            this.frame = frame;
        }

        public String getFrameName() {
            return frame;
        }
    }


    public static void setPagina(paginas pagina) {
        if (currentPage != null) currentPage.dispose();
        if (pagina == null) return;

        switch (pagina) {
            case paginas.PROFILE -> currentPage = new ProfileFrame();
            case paginas.START -> {
                Cliente.setSession(null);
                currentPage = new StartFrame();
            }
            case paginas.HOME -> currentPage = new HomeFrame();
            case paginas.CATALOG -> currentPage = new CatalogFrame();
        }
    }


    public static void setPagina(paginasEmpleo pagina) {
        if (currentPage != null) currentPage.dispose();
        if (pagina == null) return;

        switch (pagina) {
            case paginasEmpleo.PROFILE -> currentPage = new ProfileFrame();
            case paginasEmpleo.START -> {
                Empleado.setSession(null);
                currentPage = new StartFrame();
            }
            case paginasEmpleo.COMENTARIOS -> currentPage = new ComentarioView();
            case paginasEmpleo.VENTAS -> currentPage = new VentaView();
            case paginasEmpleo.USUARIOS -> currentPage = new UsuarioView();
            case paginasEmpleo.LIBROS -> currentPage = new LibroView();
            case paginasEmpleo.CATEGORIAS -> currentPage = new CategoriaView();
        }
    }

    public static void setVisible(boolean b) {
        currentPage.setVisible(b);
    }


    public static void setPagina(String className) {
        JFrame obj;
        try {
            Class<?> frame = Class.forName(className);
            obj = (JFrame) frame.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        } catch (InvocationTargetException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        } catch (InstantiationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        } catch (NoSuchMethodException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        if (currentPage != null) currentPage.dispose();
        currentPage = obj;
    }

    public static JFrame getFrame() {
        return currentPage;
    }
}
