package co.edu.konradlorenz.model;

public class LibroDigital extends Libro {
    private String formato;
    private double tamanno;
    
    public LibroDigital(String isbn, String titulo, double precio, String formato, double tamanno) {
        super(isbn, titulo, precio);
        this.formato = formato;
        this.tamanno = tamanno;
    }

    @Override
    protected double calcularPrecioFinal() {
        double descuento = 0;
        razonPrecioFinal = "sin descuento";
        
        //puede modificarse la condición
        if (true) {
            descuento = 0.1;
            razonPrecioFinal = descuento*100+"% descuento digital";
        }
        
        return precio -precio*descuento;
    }
    
    @Override
    public String obtenerInfoCreacion() {
        return "[LIBRO DIGITAL] ISBN: "+isbn+" | Título: "+titulo
                +"\nPrecio base: $"+String.format("%.2f", precio)+"| Formato: "+formato+" | Tamaño: "+tamanno+"MB"
                +"\nLicencias Disponibles: "+cantidadDisponible+" | Precio final: $"+String.format("%.2f", precioFinal)+" ("+razonPrecioFinal+")";
    }
}
