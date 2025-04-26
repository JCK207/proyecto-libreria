package co.edu.konradlorenz.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ventana {
    private Scanner sc = new Scanner(System.in);

    public void mostrarTexto(String txt) {
        System.out.println(txt);
    }

    public String pedirTexto(String txt) {
        System.out.print(txt);
        return sc.nextLine();
    }

    public int pedirEntero(String txt) {
        while (true) {
            try {
                System.out.print(txt);
                int n = sc.nextInt();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.println("Valor numérico inválido.");
                sc.nextLine();
            }
        }
    }
    
    public double pedirDouble(String txt) {
        while (true) {
            try {
                System.out.print(txt);
                double n = sc.nextDouble();
                sc.nextLine();
                return n;
            } catch(InputMismatchException e) {
                System.out.println("Valor numérico inválido.");
                sc.nextLine();
            }
        }
    }
}