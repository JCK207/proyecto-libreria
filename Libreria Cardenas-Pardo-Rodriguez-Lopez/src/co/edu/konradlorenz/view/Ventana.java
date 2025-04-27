package co.edu.konradlorenz.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ventana {
    private static Scanner sc = new Scanner(System.in);

    public static void mostrarTexto(String txt) {
        System.out.println(txt);
    }

    public static String pedirTexto(String txt) {
        String s;
        do {
            System.out.print(txt);
            s = sc.nextLine();
            if (s.isBlank()) {
                System.out.println("El texto no puede estar en blanco.\n");
            }
        } while (s.isBlank());
        return s;
    }

    public static int pedirEntero(String txt) {
        while (true) {
            try {
                System.out.print(txt);
                int n = sc.nextInt();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Valor numérico inválido.\n");
                sc.nextLine();
            }
        }
    }
    
}