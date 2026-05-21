package GUI;

import BLL.Cliente;
import BLL.Empleado;

import javax.swing.*;

public abstract class StateManager {
    private static JFrame currentPage;

    public enum paginas {
        START("Quitar la cuenta"),
        HOME("Home"),
        CATALOG("Catálogo");

        private String frame;

        paginas(String frame) {
            this.frame = frame;
        }

        public String getFrameName() {
            return frame;
        }
    }

    public static void setPagina(paginas pagina) {
        if (currentPage != null) currentPage.setVisible(false);
        switch (pagina) {
            case paginas.START -> {
                Cliente.setSession(null);
                Empleado.setSession(null);
                currentPage = new StartFrame();
            }
            case paginas.HOME -> currentPage = new HomeFrame();
            case paginas.CATALOG -> currentPage = new CatalogFrame();
        }
    }

}
