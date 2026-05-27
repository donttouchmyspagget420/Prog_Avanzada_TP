package GUI;

import BLL.Cliente;
import BLL.Empleado;
import BLL.Libro;
import BLL.Venta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventManager implements ActionListener {
    private static EventManager eventManager;

    protected static EventManager getInstanse() {
        if (eventManager == null) eventManager = new EventManager();
        return eventManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Logearse" -> StartFrame.getFrame().showLogin();
            case "Registrarse" -> StartFrame.getFrame().showSignUp();
            case "próximo" -> {
                if (StartFrame.getFrame().getIsEmpleado()) {
                    Empleado.login(StartFrame.getFrame().getEmail(), StartFrame.getFrame().getPassword());
                }

                if (!StartFrame.getFrame().getIsLoginShown())
                    Cliente.iniciarSeccion(StartFrame.getFrame().getEmail(), StartFrame.getFrame().getUsername(), StartFrame.getFrame().getPassword(), StartFrame.getFrame().getRepeatPassword());
                else {
                    Cliente.login(StartFrame.getFrame().getEmail(), StartFrame.getFrame().getPassword());
                }
            }
            case "Quitar" -> System.exit(0);
            case "Quitar la cuenta" -> StateManager.setPagina(StateManager.paginas.START);
            case "Home" -> StateManager.setPagina(StateManager.paginas.HOME);
            case "Catálogo" -> StateManager.setPagina(StateManager.paginas.CATALOG);
            case "Comprar" -> {
                Libro libro = BookFrame.getLibroStatic();

                if (libro == null) return;

                Venta.comprarLibro(libro.getId());
                BookFrame.getFrame().dispose();
                new BookFrame(Libro.getById(libro.getId()));
            }
            case "Leer el principio" -> {
                Libro libro = BookFrame.getLibroStatic();

                if (libro == null) return;

                libro.leerPaginasLibro(libro.getId());

                new Read(libro.getContenido());
            }
            case "Atrás" -> {
                StateManager.setVisible(true);
                BookFrame.getFrame().dispose();
            }
        }
    }
}