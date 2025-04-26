package co.edu.konradlorenz.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Libro {
    protected String isbn, titulo, razonDescuento;
    protected double precio, precioDescuento;
    protected int cantidadDisponible = 0, cantidadVendida = 0;

    public Libro(String isbn, String titulo, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.precio = precio;
        precioDescuento = precio -precio*calcularDescuento();
        cantidadDisponible++;
    }
    
    public String getIsbn() {
        return isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public double getPrecio() {
        return precio;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", precio=" + precio + '}';
    }

    public void agregarUnidades(int cantidad) {
        if (cantidad > 0) {
            cantidadDisponible += cantidad;
        } else {
            System.out.println("La cantidad debe ser mayor a 0.");
        }
    }
    //implemetación propia en cada subclase
    protected double calcularDescuento() {
        razonDescuento = "sin descuento";
        return 0;
    }
    
    public void vender() {
        /*
        implementación del código;
        */
        
        cantidadDisponible--;
        cantidadVendida++;
    }
    
    public abstract String obtenerInfoCreacion();
}
