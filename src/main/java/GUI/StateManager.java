package GUI;

import javax.swing.*;

public abstract class StateManager {
    private static JFrame currentPage;

    public enum paginas {
        START("StartFrame"),
        HOME("HomeFrame");

        private String frame;

        paginas(String frame) {
            this.frame = frame;
        }

        protected String getFrameName() {
            return frame;
        }
    }

    public static void setPagina(paginas pagina) {
        if (currentPage != null) currentPage.setVisible(false);
        switch (pagina) {
            case paginas.START -> currentPage = new StartFrame();
            case paginas.HOME -> currentPage = new HomeFrame();
        }
    }

}
