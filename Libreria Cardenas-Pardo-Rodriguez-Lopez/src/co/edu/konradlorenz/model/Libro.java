package co.edu.konradlorenz.model;

public abstract class Libro {
    protected String isbn, titulo, razonPrecioFinal;
    protected double precio, precioFinal;
    protected int cantidadDisponible = 1, cantidadVendida = 0;

    public Libro(String isbn, String titulo, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.precio = precio;
        precioFinal = calcularPrecioFinal();
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
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    public int getCantidadVendida() {
        return cantidadVendida;
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
    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", precio=" + precio + ", cantidadDisponible=" + cantidadDisponible + ", cantidadVendida=" + cantidadVendida + '}';
    }

    //implemetación propia en cada subclase
    protected double calcularPrecioFinal() {
        razonPrecioFinal = "sin descuento";
        return precio;
    }
    
    public void agregarUnidades(int unidades) {
        cantidadDisponible += unidades;
    }
    
    public void vender(int unidades) {
        cantidadDisponible -= unidades;
        cantidadVendida += unidades;
    }
    
    public abstract String obtenerInfoCreacion();
}
