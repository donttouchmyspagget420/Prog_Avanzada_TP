package BLL;

public class Comentario {
    protected final static String TABLE = "comentarios";

    private int id;
    private int clasificacion;
    private String contenido;
    private int fkAutor;

    public Comentario(int id, int clasificacion, String contenido, int fkAutor) {
        this.id = id;
        this.clasificacion = clasificacion;
        this.contenido = contenido;
        this.fkAutor = fkAutor;
    }
}
