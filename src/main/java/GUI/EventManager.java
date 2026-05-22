package GUI;

import BLL.Cliente;
import BLL.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventManager implements ActionListener {
    private static EventManager eventManager;

    protected StartFrame startFrame;
    protected HomeFrame homeFrame;
    protected SideBar sideBar;

    protected static EventManager getInstanse() {
        if (eventManager == null) eventManager = new EventManager();
        return eventManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Logearse" -> startFrame.showLogin();
            case "Registrarse" -> startFrame.showSignUp();
            case "próximo" -> {
                if (startFrame.getIsEmpleado()) {
                    Empleado.login(startFrame.getEmail(), startFrame.getPassword());
                }
                if (!startFrame.getIsLoginShown())
                    Cliente.iniciarSeccion(startFrame.getEmail(), startFrame.getUsername(), startFrame.getPassword(), startFrame.getRepeatPassword());
                else {
                    Cliente.login(startFrame.getEmail(), startFrame.getPassword());
                }
            }
            case "Quitar" -> System.exit(0);
            case "Quitar la cuenta" -> StateManager.setPagina(StateManager.paginas.START);
            case "Home" -> StateManager.setPagina(StateManager.paginas.HOME);
            case "Catálogo" -> StateManager.setPagina(StateManager.paginas.CATALOG);
        }
    }
}