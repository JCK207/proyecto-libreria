package co.edu.konradlorenz.controller;

import co.edu.konradlorenz.model.*;
import co.edu.konradlorenz.view.*;

public class Control {
    private Libreria libreria = new Libreria();
    
    public void run() {
        String opcion;
        do {
            opcion = Ventana.pedirTexto("Menu de usuario."
                    +"\n  Agregar libro."
                    +"\n  Agregar unidades a un libro."
                    +"\n  Retirar libro."
                    +"\n  Cambiar libro de posición."
                    +"\n  Vender libro."
                    +"\n  Salir."
                    +"\nSeleccione una opción: ");
            switch (opcion) {
                case "1":
                //case "Agregar libro":
                    agregarLibroMenu();
                    break;
                case "2":
                //case "":
                    if (!agregarUnidadesLibroMenu()) {
                        Ventana.mostrarTexto("Cancelación de Agregar unidades a un libro.");
                    }
                    break;
                case "3":
                //case "Retirar libro":
                    if (!retirarLibroMenu()) {
                        Ventana.mostrarTexto("Cancelación de Retirar libro.");
                    }
                    break;
                case "4":
                //case "Cambiar libro de posición":
                    if (!cambiarPosicionLibroMenu()) {
                        Ventana.mostrarTexto("Cancelación de Cambiar libro de posición.");
                    }
                    break;
                case "5":
                //case "Vender libro":
                    if (!venderLibroMenu()) {
                        Ventana.mostrarTexto("Cancelación de Vender libro.");
                    }
                    break;
                case "0":
                //case "Salir":
                    Ventana.mostrarTexto("Saliendo.");
                    break;
                default: {
                    Ventana.mostrarTexto("Opción inválida, ingrésela textualmente.");
                }
            }
        Ventana.mostrarTexto("");
        } while (!opcion.equalsIgnoreCase("Salir"));
    }
    
    private void agregarLibroMenu() {
        Ventana.mostrarTexto("\nIngresó a la opción 1: Agregar libro");
        int opcionB = Ventana.pedirEntero("Seleccione el tipo de libro:"
                +"\n1. Libro Físico"
                +"\n2. Libro Digital"
                +"\n3. Libro Coleccionable"
                +"\nOpción: ");
        String tipo;
        Libro libro;
        switch (opcionB) {
            case 1:
                tipo = "físico";
                Ventana.mostrarTexto("");
                libro = crearLibroFisico();
                break;
            case 2:
                tipo = "digital";
                Ventana.mostrarTexto("");
                libro = crearLibroDigital();
                break;
            case 3:
                tipo = "coleccionable";
                Ventana.mostrarTexto("");
                libro = crearLibroColeccionable();
                break;
            default:
                Ventana.mostrarTexto("Opción inválida.");
                return;
        }
        if (libro==null) {
            Ventana.mostrarTexto("Cancelación de Agregar libro.");
            return;
        }
        
        libreria.agregarLibro(libro);
        Ventana.mostrarTexto("\nSe agregó el libro "+tipo+":"
                +"\n"+libro.obtenerInfoCreacion());
    }
    
    
    private boolean agregarUnidadesLibroMenu() {
        boolean sinError = false;
        Ventana.mostrarTexto("\nIngresó a la opción 2: Agregar unidades a un libro");
        if (!mostrarLibrosAgregados()) {
            Ventana.mostrarTexto("No hay ningún libro agregado.");
            return sinError;
        }
        
        String[] info = Ventana.pedirTexto("Ingrese los datos separados por & (libro&unidades):\n").split("&");
        if (info.length!=2) {
            Ventana.mostrarTexto("La cadena debe de ser de 2 datos separados por & pero hay: "+info.length);
            return sinError;
        }
        int indice, unidades;
        Libro libro;
        
        try {
            indice = Integer.parseInt(info[0])-1;
            unidades = Integer.parseInt(info[1]);
            libro = libreria.agregarUnidadesLibro(indice, unidades);
            
            sinError = true;
            Ventana.mostrarTexto("Se agregaron "+unidades+" unidades al libro "+(indice+1)+":"
                    +"\n"+libro);
        } catch (NumberFormatException e) {
            Ventana.mostrarTexto("Algún valor numérico está con otro formato.");
        } catch (LibroNullException e) {
            Ventana.mostrarTexto(e.getMessage());
        } catch (CantidadDeUnidadesException e) {
            Ventana.mostrarTexto(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Ventana.mostrarTexto("El índice es inválido.");
        } finally {
            return sinError;
        }
    }
    
    private boolean retirarLibroMenu() {
        boolean sinError = false;
        Ventana.mostrarTexto("\nIngresó a la opción 3: Retirar libro");
        if (!mostrarLibrosAgregados()) {
            Ventana.mostrarTexto("No hay ningún libro agregado.");
            return sinError;
        }
        
        String info = Ventana.pedirTexto("Ingrese el número de libro que desea retirar:\n");
        int indice;
        Libro libro;
        
        try {
            indice = Integer.parseInt(info)-1;
            libro = libreria.retirarLibro(indice);
            
            sinError = true;
            Ventana.mostrarTexto("Se retiró el libro "+(indice+1)+":"
                    +"\n"+libro);
        } catch (NumberFormatException e) {
            Ventana.mostrarTexto("Algún valor numérico está con otro formato.");
        } catch (LibroNullException e) {
            Ventana.mostrarTexto(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Ventana.mostrarTexto("El índice es inválido.");
        } finally {
            return sinError;
        }
    }
    
    private boolean cambiarPosicionLibroMenu() {
        boolean sinError = false;
        Ventana.mostrarTexto("\nIngresó a la opción 4: Cambiar libro de posición");
        if (!mostrarLibrosAgregados()) {
            Ventana.mostrarTexto("No hay ningún libro agregado.");
            return sinError;
        }
        
        String[] info = Ventana.pedirTexto("Ingrese los datos separados por & (libro a cambiar&nuevo espacio):\n").split("&");
        if (info.length!=2) {
            Ventana.mostrarTexto("La cadena debe de ser de 2 datos separados por & pero hay: "+info.length);
            return sinError;
        }
        
        try {
            int origen = Integer.parseInt(info[0])-1;
            int destino = Integer.parseInt(info[1])-1;
            libreria.moverLibro(origen, destino);
            
            sinError = true;
            Ventana.mostrarTexto("Se cambió el libro de posición.");
        } catch (NumberFormatException e) {
            Ventana.mostrarTexto("Algún valor numérico está con otro formato.");
        } catch (LibroNullException e) {
            Ventana.mostrarTexto(e.getMessage());
        } catch (EspacioOcupadoException e) {
            Ventana.mostrarTexto(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Ventana.mostrarTexto("Algún índice es inválido.");
        } finally {
            return sinError;
        }
    }
    
    private boolean venderLibroMenu() {
        boolean sinError = false;
        Ventana.mostrarTexto("\nIngresó a la opción 5: Vender libro");
        if (!mostrarLibrosAgregados()) {
            Ventana.mostrarTexto("No hay ningún libro agregado.");
            return sinError;
        }
        
        String[] info = Ventana.pedirTexto("Ingrese los datos separados por & (libro&cantidad a comprar):\n").split("&");
        if (info.length!=2) {
            Ventana.mostrarTexto("La cadena debe de ser de 2 datos separados por & pero hay: "+info.length);
            return sinError;
        }
        int indice, unidades;
        Libro libro;
        
        try {
            indice = Integer.parseInt(info[0])-1;
            unidades = Integer.parseInt(info[1]);
            //libro = libreria.getLibros()[indice];//
            //Ventana.mostrarTexto(libro.toString());//
            libro = libreria.venderLibro(indice, unidades);
            //Ventana.mostrarTexto(libro.toString());//
            
            sinError = true;
            Ventana.mostrarTexto("Se vendieron "+unidades+" unidades del libro "+(indice+1)+":"
                +"\n"+libro);
        } catch (NumberFormatException e) {
            Ventana.mostrarTexto("Algún valor numérico está con otro formato.");
        } catch (LibroNullException e) {
            Ventana.mostrarTexto(e.getMessage());
        } catch (CantidadDeUnidadesException e) {
            Ventana.mostrarTexto(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            Ventana.mostrarTexto("El índice es inválido.");
        } finally {
            return sinError;
        }
        
    }
    
    
    private Libro crearLibroFisico() {
        String entrada = Ventana.pedirTexto("Ingrese los datos separados por & (isbn&título&precio&pesoKg)\n");
        String[] info = entrada.split("&");
        if (info.length!=4) {
            Ventana.mostrarTexto("La cadena debe ser de 4 datos separados por & pero hay: "+info.length);
            return null;
        }
        
        double precio;
        double peso;
        String isbn = info[0], titulo = info[1];
        if(isbn.isBlank() || titulo.isBlank()) {
            Ventana.mostrarTexto("Ningún texto puede estar en blanco.");
            return null;
        }
        
        try {
            precio = Double.parseDouble(info[2]);
            peso = Double.parseDouble(info[3]);
        } catch (NumberFormatException e) {
            Ventana.mostrarTexto("Algún valor numérico es inválido.");
            return null;
        }
        
        return new LibroFisico(isbn, titulo, precio, peso);
    }
    
    private Libro crearLibroDigital() {
        String entrada = Ventana.pedirTexto("Ingrese los datos separados por & (isbn&título&precio&formato&tamañoMB)\n");
        String[] info = entrada.split("&");
        if (info.length!=5) {
            Ventana.mostrarTexto("La cadena debe ser de 5 datos separados por & pero hay: "+info.length);
            return null;
        }
        
        double precio;
        double tamanno;
        String isbn = info[0], titulo = info[1], formato = info[3];
        if(isbn.isBlank() || titulo.isBlank() || formato.isBlank()) {
            Ventana.mostrarTexto("Ningún texto puede estar en blanco.");
            return null;
        }
        
        try {
            precio = Double.parseDouble(info[2]);
            tamanno = Double.parseDouble(info[4]);
        } catch (NumberFormatException e) {
            Ventana.mostrarTexto("Algún valor numérico es inválido.");
            return null;
        }
        
        return new LibroDigital(isbn, titulo, precio, formato, tamanno);
    }
    
    private Libro crearLibroColeccionable() {
        String entrada = Ventana.pedirTexto("Ingrese los datos separados por & (isbn&título&precio&pesoKg&edición(actual/total))\n");
        String[] info = entrada.split("&");
        if (info.length!=5) {
            Ventana.mostrarTexto("La cadena debe ser de 5 datos separados por & pero hay: "+info.length);
            return null;
        }
        
        double precio;
        double peso;
        String[] aux = info[4].split("/");
        if (aux.length!=2) {
            Ventana.mostrarTexto("La edición debe ser de 2 datos separados por / pero hay: "+aux.length);
            return null;
        }
        int edAct, edTot;
        String isbn = info[0], titulo = info[1];
        if(isbn.isBlank() || titulo.isBlank()) {
            Ventana.mostrarTexto("Ningún texto puede estar en blanco.");
            return null;
        }
        
        try {
            precio = Double.parseDouble(info[2]);
            peso = Double.parseDouble(info[3]);
            edAct = Integer.parseInt(aux[0]);
            edTot = Integer.parseInt(aux[1]);
            if (edAct<=0 || edTot<=0 || edAct>edTot) {
                Ventana.mostrarTexto("La edición debe ser de 2 números naturales, el segundo mayor al primero");
                return null;
            }
        } catch (NumberFormatException e) {
            Ventana.mostrarTexto("Algún valor numérico es inválido.");
            return null;
        }
        
        return new LibroColeccionable(isbn, titulo, precio, peso, edAct, edTot);
    }
    
    
    private boolean mostrarLibrosAgregados() {
        boolean sinError = false;
        if (libreria.isEmpty()) {
            return sinError;
        }
        
        Ventana.mostrarTexto("Libros:");
        for (Libro agregado : libreria.getLibros()) {
            if (agregado==null) {
                Ventana.mostrarTexto("Espacio disponible.");
            } else {
                Ventana.mostrarTexto(agregado.toString());
            }
        }
        Ventana.mostrarTexto("");
        
        sinError = true;
        return sinError;
    }
}
