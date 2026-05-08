package BLL;

public abstract class Rol {
    protected final static String TABLE = "roles";

    enum roles {
        Cliente("cliente"),
        Empleado("empleado");

        private String title;

        roles(String title) {
            this.title = title;
        }

        protected String getTitle() {
            return title;
        }
    }

    protected static String getRole(Usuario usuario) throws Exception {
        if (usuario instanceof Cliente) {
            return roles.Cliente.getTitle();
        }
        if (usuario instanceof Empleado) {
            return roles.Empleado.getTitle();
        }
        throw new Exception("algo es incorrecto");
    }
}
