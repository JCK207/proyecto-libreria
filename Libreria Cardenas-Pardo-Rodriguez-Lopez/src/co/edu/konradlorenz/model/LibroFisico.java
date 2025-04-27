package co.edu.konradlorenz.model;

public class LibroFisico extends Libro {
    private double peso;
    
    public LibroFisico(String isbn, String titulo, double precio, double peso) {
        super(isbn, titulo, precio);
        this.peso = peso;
    }

    @Override
    protected double calcularPrecioFinal() {
        double descuento = 0;
        razonPrecioFinal = "sin descuento";
        
        //puede modificarse la condición y su cuerpo
        if (true) {
            
        }
        
        return precio -precio*descuento;
    }
    
    @Override
    public String obtenerInfoCreacion() {
        return "[LIBRO FÍSICO] ISBN: "+isbn+" | Título: "+titulo
                +"\nPrecio base: $"+String.format("%.2f", precio)+"| Peso: "+peso+"kg"
                +"\nUnidades disponible: "+cantidadDisponible+" | Precio final: $"+String.format("%.2f", precioFinal)+" ("+razonPrecioFinal+")";
    }
}
