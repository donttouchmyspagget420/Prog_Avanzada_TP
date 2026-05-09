package BLL;

import java.util.ArrayList;

public class Popularidad {
    private String categoria;
    private Libro[] topLibros = new Libro[10];

    public static ArrayList<Popularidad> popularidades;

    Popularidad(String categoria,Libro[] topLibros){
        this.categoria = categoria;
        this.topLibros = topLibros;
        popularidades.add(this);
    }

    public String getCat() {
        return categoria;
    }

    public Libro[] getLibros() {
        return topLibros;
    }
}
