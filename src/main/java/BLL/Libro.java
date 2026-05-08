package BLL;

public class Libro {
    protected final static String TABLE = "libros";

    private int id;
    private String portada;
    private float precio;
    private int stock;
    private String titulo;
    private String descripcion;
    private String contenido;
    private int cantidadDeClasificacion;
    private int paginas;
    private float clasificacion;
    private int fkCategoria;
    private int fkAutor;

    public Libro(int id, String portada, float precio, int stock, String titulo, String descripcion, String contenido, int cantidadDeClasificacion, int paginas, float clasificacion, int fkCategoria, int fkAutor) {
        this.id = id;
        this.portada = portada;
        this.precio = precio;
        this.stock = stock;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.cantidadDeClasificacion = cantidadDeClasificacion;
        this.paginas = paginas;
        this.clasificacion = clasificacion;
        this.fkCategoria = fkCategoria;
        this.fkAutor = fkAutor;
    }
}
