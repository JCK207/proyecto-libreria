package co.edu.konradlorenz.controller;

import co.edu.konradlorenz.model.*;
import co.edu.konradlorenz.view.*;

public class Control {
    private Libro[] librosAgregados = new Libro[0];
    private Ventana objV = new Ventana();
    
    public void run() {
        String opcion;
        do {
            opcion = objV.pedirTexto("Menu de usuario."
                    +"\n  Agregar libro."
                    +"\n  Agregar unidades a un libro."
                    +"\n  Retirar libro."
                    +"\n  Cambiar libro de posición."
                    +"\n  Vender libro."
                    +"\n  Salir."
                    +"\nSeleccione una opción: ");
            switch (opcion) {
                case "Agregar libro":
                    agregarLibroMenu();
                    break;
                case "Agregar unidades a un libro":
                    agregarCantidadUnidadesDeLibro();
                    break;
                case "Cambiar libro de posición":
                    cambiarPosicionLibro();
                    break;
                case "Retirar libro":
                    retirarLibro();
                    break;
                case "Vender libro":
                    venderLibro();
                    break;
                case "Salir":
                    
                    break;
                default: {
                    objV.mostrarTexto("Opción inválida, ingrésela textualmente.");
                }
            }
        objV.mostrarTexto("");
        } while (!opcion.equalsIgnoreCase("Salir"));
    }
    private void agregarCantidadUnidadesDeLibro() {
        objV.mostrarTexto("\nIngresó a la opción 2: Agregar unidades a un libro");
        String isbn = objV.pedirTexto("Ingrese el ISBN del libro: ");
        int cantidad = objV.pedirEntero("Ingrese la cantidad de unidades a agregar: ");
        if (cantidad<=0) {
            objV.mostrarTexto("La cantidad debe ser mayor a 0.");
            return;
        }
        
        for (Libro libro : librosAgregados) {
            if (libro!=null && libro.getIsbn().equals(isbn)) {
                libro.agregarUnidades(cantidad);
                objV.mostrarTexto("Se agregaron "+cantidad+" unidades al libro con ISBN "+isbn);
                return;
            }
        }
        
        objV.mostrarTexto("No se encontró el libro con ISBN "+isbn);
    }
    
    private void agregarLibroMenu() {
        objV.mostrarTexto("\nIngresó a la opción 1: Agregar libro");
        int opcionB = objV.pedirEntero("Seleccione el tipo de libro:"
                +"\n1. Libro Físico"
                +"\n2. Libro Digital"
                +"\n3. Libro Coleccionable"
                +"\nOpción: ");
        String tipo;
        Libro libro;
        switch (opcionB) {
            case 1:
                tipo = "físico";
                libro = crearLibroFisico();
                break;
            case 2:
                tipo = "digital";
                libro = crearLibroDigital();
                break;
            case 3:
                tipo = "coleccionable";
                libro = crearLibroColeccionable();
                break;
            default:
                objV.mostrarTexto("Opción inválida.");
                return;
        }
        if (libro==null) {
            objV.mostrarTexto("Cancelación de Agregar libro.");
            return;
        }
        
        agregarLibro(libro);
        objV.mostrarTexto("\nSe agregó el libro "+tipo+":"
                +"\n"+libro.obtenerInfoCreacion());
    }
    
    private void agregarLibro(Libro libro) {
        int primerEspacio = verificarEspacio();
        if (primerEspacio==-1) {
            Libro[] aux = librosAgregados;
            librosAgregados = new Libro[aux.length+1];
            for (int i=0; i<aux.length; i++) {
                librosAgregados[i] = aux[i];
            }
            
            librosAgregados[librosAgregados.length-1] = libro;
        } else {
            librosAgregados[primerEspacio] = libro;
        }
    }
    private void cambiarPosicionLibro() {
        objV.mostrarTexto("\nIngresó a la opción 3: Cambiar libro de posición");
        String isbn = objV.pedirTexto("Ingrese el ISBN del libro: ");
        int nuevaPos = objV.pedirEntero("Ingrese la nueva posición (0-"+(librosAgregados.length-1)+"): ");
        if (nuevaPos<0 || nuevaPos>=librosAgregados.length) {
            objV.mostrarTexto("La posición debe ser un número entre 0 y "+(librosAgregados.length-1));
            return;
        }
        
        for (int i=0; i<librosAgregados.length; i++) {
            if (librosAgregados[i]!=null && librosAgregados[i].getIsbn().equals(isbn)) {
                Libro aux = librosAgregados[nuevaPos];
                librosAgregados[nuevaPos] = librosAgregados[i];
                librosAgregados[i] = aux;
                objV.mostrarTexto("Se cambió el libro con ISBN "+isbn+" a la posición "+nuevaPos);
                return;
            }
        }
        
        objV.mostrarTexto("No se encontró el libro con ISBN "+isbn);
    }
    private void retirarLibro() {
        objV.mostrarTexto("\nIngresó a la opción 4: Retirar libro");
        String isbn = objV.pedirTexto("Ingrese el ISBN del libro: ");
        
        for (int i=0; i<librosAgregados.length; i++) {
            if (librosAgregados[i]!=null && librosAgregados[i].getIsbn().equals(isbn)) {
                librosAgregados[i] = null;
                objV.mostrarTexto("Se retiró el libro con ISBN "+isbn);
                return;
            }
        }
        
        objV.mostrarTexto("No se encontró el libro con ISBN "+isbn);
    }
    private void venderLibro() {
        objV.mostrarTexto("\nIngresó a la opción 5: Vender libro");
        String isbn = objV.pedirTexto("Ingrese el ISBN del libro: ");
        
        for (int i=0; i<librosAgregados.length; i++) {
            if (librosAgregados[i]!=null && librosAgregados[i].getIsbn().equals(isbn)) {
                librosAgregados[i].vender();
                objV.mostrarTexto("Se vendió el libro con ISBN "+isbn);
                return;
            }
        }
        
        objV.mostrarTexto("No se encontró el libro con ISBN "+isbn);
    }
    
    private Libro crearLibroFisico() {
        String entrada = objV.pedirTexto("\nIngrese los datos separados por & (isbn&título&precio&pesoKg)\n");
        String[] info = entrada.split("&");
        if (info.length!=4) {
            objV.mostrarTexto("La cadena debe ser de 4 datos separados por & pero hay: "+info.length);
            return null;
        }
        
        double precio;
        double peso;
        String isbn = info[0], titulo = info[1];
        try {
            precio = Double.parseDouble(info[2]);
            peso = Double.parseDouble(info[3]);
        } catch (NumberFormatException e) {
            objV.mostrarTexto("Algún valor numérico es inválido.");
            return null;
        }
        
        return new LibroFisico(isbn, titulo, precio, peso);
    }
    
    private Libro crearLibroDigital() {
        String entrada = objV.pedirTexto("\nIngrese los datos separados por & (isbn&título&precio&formato&tamañoMB)\n");
        String[] info = entrada.split("&");
        if (info.length!=5) {
            objV.mostrarTexto("La cadena debe ser de 5 datos separados por & pero hay: "+info.length);
            return null;
        }
        
        double precio;
        double tamanno;
        String isbn = info[0], titulo = info[1], formato = info[3];
        try {
            precio = Double.parseDouble(info[2]);
            tamanno = Double.parseDouble(info[4]);
        } catch (NumberFormatException e) {
            objV.mostrarTexto("Algún valor numérico es inválido.");
            return null;
        }
        
        return new LibroDigital(isbn, titulo, precio, formato, tamanno);
    }
    
    private Libro crearLibroColeccionable() {
        String entrada = objV.pedirTexto("\nIngrese los datos separados por & (isbn&título&precio&pesoKg&edición(actual/total))\n");
        String[] info = entrada.split("&");
        if (info.length!=5) {
            objV.mostrarTexto("La cadena debe ser de 5 datos separados por & pero hay: "+info.length);
            return null;
        }
        
        double precio;
        double peso;
        
        String[] aux = info[4].split("/");
        if (aux.length!=2) {
            objV.mostrarTexto("La edición debe ser de 2 datos separados por / pero hay: "+aux.length);
            return null;
        }
        int edAct, edTot;
        String isbn = info[0], titulo = info[1];
        try {
            precio = Double.parseDouble(info[2]);
            peso = Double.parseDouble(info[3]);
            edAct = Integer.parseInt(aux[0]);
            edTot = Integer.parseInt(aux[1]);
            if (edAct<=0 || edTot<=0 || edAct>edTot) {
                objV.mostrarTexto("La edición debe ser de 2 números naturales, el segundo mayor al primero");
                return null;
            }
        } catch (NumberFormatException e) {
            objV.mostrarTexto("Algún valor numérico es inválido.");
            return null;
        }
        
        return new LibroColeccionable(isbn, titulo, precio, peso, edAct, edTot);
    }
    
    private int verificarEspacio() {
        int primerEspacio = -1;
        boolean hayEspacio = false;
        int i = 0;
        while (i<librosAgregados.length && !hayEspacio) {
            if (librosAgregados[i]==null) {
                hayEspacio = true;
                primerEspacio = i;
            }
            i++;
        }
        return primerEspacio;
    }
    
    private void mostrarLibrosAgregados() {
        for (Libro agregado : librosAgregados) {
            if (agregado==null) {
                objV.mostrarTexto("Espacio disponible.");
            } else {
                objV.mostrarTexto(agregado.toString());
            }
        }
    }
}
