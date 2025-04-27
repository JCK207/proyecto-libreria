package co.edu.konradlorenz.model;

public class Libreria {
    private Libro[] libros = new Libro[0];

    public Libro[] getLibros() {
        return libros;
    }
    
    public void agregarLibro(Libro libro) {
        int primerEspacio = verificarEspacio();
        if (primerEspacio==-1) {
            Libro[] aux = libros;
            libros = new Libro[aux.length+1];
            for (int i=0; i<aux.length; i++) {
                libros[i] = aux[i];
            }
            
            libros[libros.length-1] = libro;
        } else {
            libros[primerEspacio] = libro;
        }
    }
    
    public Libro agregarUnidadesLibro(int indice, int unidades) throws LibroNullException, CantidadDeUnidadesException {
        Libro libro = libros[indice];
        if (libro==null) {
            throw new LibroNullException("El libro seleccionado no existe.");
        }
        if (unidades<0) {
            throw new CantidadDeUnidadesException("Las unidades a ingresar deben ser un número mayor o igual a cero.");
        }
        libro.agregarUnidades(unidades);
        return libro;
    }
    
    public Libro retirarLibro(int indice) throws LibroNullException {
        Libro libro = libros[indice];
        if (libro==null) {
            throw new LibroNullException("No se puede retirar el libro porque no existe.");
        }
        Libro aux = libro;
        libros[indice] = null;
        return aux;
    }
    
    public void moverLibro(int origen, int destino) throws LibroNullException, EspacioOcupadoException {
        Libro libroOrigen = libros[origen];
        Libro libroDestino = libros[destino];
        if (libroOrigen==null) {
            throw new LibroNullException("El libro seleccionado no existe.");
        }
        if (libroDestino!=null) {
            throw new EspacioOcupadoException("No se puede mover el libro porque el espacio está ocupado.");
        }
        libros[destino] = libros[origen];
        libros[origen] = null;
    }

    public Libro venderLibro(int indice, int unidades) throws LibroNullException, CantidadDeUnidadesException {
        Libro libro = libros[indice];
        if (libro==null) {
            throw new LibroNullException("El libro seleccionado no existe.");
        }
        if (unidades<0 || unidades>libro.getCantidadDisponible()) {
            throw new CantidadDeUnidadesException("La cantidad no es correcta.");
        }
        libro.vender(unidades);
        return libro;
    }
    
    public int verificarEspacio() {
        for (int i=0; i<libros.length; i++) {
            if (libros[i]==null) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isEmpty() {
        for (Libro agregado : libros) {
            if (agregado!=null) {
                return false;
            }
        }
        return true;
    }
}
