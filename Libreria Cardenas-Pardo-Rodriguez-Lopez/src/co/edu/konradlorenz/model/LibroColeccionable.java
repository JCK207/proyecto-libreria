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
    public double calcularPrecioFinal() {
        double prima = 0;
        razonPrecioFinal = "sin prima coleccionable";
        
        //puede modificarse la condición
        if (true) {
            prima = 0.1;
            razonPrecioFinal = "incluye "+prima*100+"% prima coleccionable";
        }
        
        return precio + precio*prima;
    }
    
    @Override
    public String obtenerInfoCreacion() {
        return "[COLECCIONABLE] ISBN: "+isbn+" | Título: "+titulo
                +"\nPrecio base: $"+String.format("%.2f", precio)+"| Peso: "+peso+"kg | Edición: "+edAct+"/"+edTot
                +"\nUnidades: "+cantidadDisponible+" | Precio final: $"+String.format("%.2f", precioFinal)+" ("+razonPrecioFinal+")";
    }
}
