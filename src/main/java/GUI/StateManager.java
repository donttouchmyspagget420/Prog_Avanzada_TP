package GUI;

import BLL.Cliente;
import BLL.Empleado;

import javax.swing.*;

public abstract class StateManager {
    private static JFrame currentPage;

    public interface pagina {
        String frame = "";


        default String getFrameName() {
            return frame;
        }
    }

    public enum paginas implements pagina {
        PROFILE("Perfil"),
        HOME("Home"),
        CATALOG("Catálogo"),
        START("Quitar la cuenta");

        private String frame;

        paginas(String frame) {
            this.frame = frame;
        }

        @Override
        public String getFrameName() {
            return pagina.super.getFrameName();
        }
    }


    public enum paginasEmpleo implements pagina {
        PROFILE("Perfil"),
        VENTAS("Ventas"),
        LIBROS("Libros"),
        USUARIOS("Usuarios"),
        COMENTARIOS("Comentarios"),
        START("Quitar la cuenta");

        private String frame;

        paginasEmpleo(String frame) {
            this.frame = frame;
        }

        @Override
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
            case paginasEmpleo.COMENTARIOS -> currentPage = null;
            case paginasEmpleo.VENTAS -> currentPage = new VentaView();
            case paginasEmpleo.USUARIOS -> currentPage = new UsuarioView();
            case paginasEmpleo.LIBROS -> currentPage = new LibroView();
        }
    }

    public static void setVisible(boolean b) {
        currentPage.setVisible(b);
    }

    public static JFrame getCurrentPage() {
        return currentPage;
    }

}
