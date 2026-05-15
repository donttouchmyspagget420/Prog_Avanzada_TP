package BLL;

public abstract class Usuario {
    private int id;
    private String correo;
    private String username;
    private String contrasena;
    private String pfp;
    private String sobre;

    protected Usuario(int id, String correo, String username, String contrasena) {
        this.id = id;
        this.correo = correo;
        this.username = username;
        this.contrasena = contrasena;
        this.pfp = null;
        this.sobre = null;
    }

    protected Usuario(int id, String correo, String username, String contrasena, String pfp, String about) {
        this.id = id;
        this.correo = correo;
        this.username = username;
        this.contrasena = contrasena;
        this.pfp = pfp;
        this.sobre = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPfp() {
        return pfp;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }
}
