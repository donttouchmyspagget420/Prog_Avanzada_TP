package GUI;

import javax.swing.*;

public abstract class StateManager {
    private static JFrame pagina;

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
        switch (pagina) {
            case paginas.START -> new StartFrame();
            case paginas.HOME -> new HomeFrame();
        }
    }

}
