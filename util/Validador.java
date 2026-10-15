package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.PlatoSinStock;

/*
    Clase con métodos de validación reutilizables.
    Todos los metodos son estáticos: no necesitamos crear una instancia de Validador para usarlos.
    Se invocan directamente.
*/

public class Validador {
    /* 
        Validaciones de datos de los Platos
        Estos métodos lanzan una excepción si los datos son inválidos
        No retornan nada: si terminan sin lanzar la excepción, el dato es válido. 
    */

    public static void validarNombre(String nombre){
        // Un nombre nulo o vacío no representa un Plato válido
        if (nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del Plato no puede estar vacío.");
        }
    }

    public static void validarPrecio(double precio){
        /* Que No sean negativos
           Aceptamos 0 
        */

        if ( precio < 0){
            throw new IllegalArgumentException("El precio del plato no puede ser negativo.");
        }
    }

    public static void validarStock( int stock){
        /* Stock negativo no es válido.
           Usamos nuestra excepción personalizada 
        */

        if (stock < 0){
            throw new PlatoSinStock("El stock no puede ser negativo.");
        }
    }

    public static void validarCategoria (String categoria){
        if(categoria == null || categoria.isBlank()){
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
    }

    // Lectura por consola
    
    public static int leerEntero(Scanner sc , String mensaje){
        // Bucle infinito que se rompe cuando el usuario ingresa un entero válido.

        while(true){
            System.out.println(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // limpia el salto de línea pendiente
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero. Intente nuevamente.");
                sc.nextLine(); // limpia el salto de línea pendiente
            }
        }
    }

    public static double leerDouble(Scanner sc , String mensaje){
        while(true){
            System.out.println(mensaje);
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.println("Debe ingresar un número decimal. Ingrese un número con coma.");
                sc.nextLine();
            }
        }
    }

    public static String leerTexto(Scanner sc , String mensaje){
        // Lectura simple de texto

        System.out.println(mensaje);
        return sc.nextLine();
    }
}
