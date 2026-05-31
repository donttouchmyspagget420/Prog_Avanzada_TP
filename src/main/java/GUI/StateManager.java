package GUI;

import BLL.Cliente;
import BLL.Empleado;

import javax.swing.*;

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

    public static void setPagina(paginas pagina) {
        if (currentPage != null) currentPage.dispose();
        if (pagina == null) return;

        switch (pagina) {
            case paginas.PROFILE -> currentPage = new ProfileFrame();
            case paginas.START -> {
                Cliente.setSession(null);
                Empleado.setSession(null);
                currentPage = new StartFrame();
            }
            case paginas.HOME -> currentPage = new HomeFrame();
            case paginas.CATALOG -> currentPage = new CatalogFrame();
        }
    }

    public static void setVisible(boolean b) {
        currentPage.setVisible(b);
    }

}
