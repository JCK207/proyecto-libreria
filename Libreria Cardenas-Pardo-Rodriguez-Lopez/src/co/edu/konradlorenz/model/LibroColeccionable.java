package co.edu.konradlorenz.model;

public class LibroColeccionable extends Libro {
    private double peso;
    private int edAct, edTot;
    
    public LibroColeccionable(String isbn, String titulo, double precio, double peso, int edAct, int edTot) {
        super(isbn, titulo, precio);
        this.peso = peso;
        this.edAct = edAct;
        this.edTot = edTot;
    }

    @Override
    protected double calcularDescuento() {
        double descuento = 0;
        razonDescuento = "sin descuento";
        
        /*
        implementación del condicional para modificar descuento y razonDescuento
        */
        
        return descuento;
    }
    
    @Override
    public String obtenerInfoCreacion() {
        return "[COLECCIONABLE] ISBN: "+isbn+" | Título: "+titulo
                +"\nPrecio base: $"+String.format("%.2f", precio)+"| Peso: "+peso+"kg | Edición: "+edAct+"/"+edTot
                +"\nUnidades: "+cantidadDisponible+" | Precio final: $"+String.format("%.2f", precioDescuento)+" ("+razonDescuento+")";
    }
}
