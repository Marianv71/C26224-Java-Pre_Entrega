import java.util.Scanner;

import exception.PlatoNoEncontrado;
import exception.PlatoSinStock;

import model.Plato;
import service.PlatoService;
import ui.MenuPlatos;
import util.Validador;

public class Main {
    public static void main(String[] args) {
        /* Creamos las dependencias una sola vez y las compartimos
           durante toda la ejecución del programa.
        */
        PlatoService service = new PlatoService();        
        Scanner sc = new Scanner(System.in);
        MenuPlatos menu = new MenuPlatos(sc, service);
        
        cargarDatosDePrueba(service);

        int opcion;

        /* Do-while garantiza que el menú se muestre al menos una vez.
           Se repite hasta que el usuario elige la opción 6. 
        */

        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opción: ");

            /* Cada opción del menú está envuelta en try/catch.
               Si una operación lanza una excepción (Plato no encontrado, Plato sin stock, datos
               inexistentes, datos inválidos, etc.), el programa NO
               se cae: muestra el mensaje y vuelve a mostrar el menú. 
            */

            try {
                switch (opcion) {
                    case 1 -> menu.agregarPlato();
                    case 2 -> menu.listarPlatos();
                    case 3 -> menu.buscarPlato();
                    case 4 -> menu.actualizarPlato();
                    case 5 -> menu.eliminarPlato();
                    case 6 -> System.out.println("¡Muchas gracias por usar nuestro sistema!");
                    default -> System.out.println("Opción inválida. Elija un número del 1 al 6.");
                }
            } catch (PlatoNoEncontrado | PlatoSinStock e) {
                // Capturamos las excepciones personalizadas
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                /* Validador de datos genéricos inválidos
                   (nombre en blanco,precio negativo,etc...) 
                */
                System.out.println(e.getMessage());
            }

        } while (opcion != 6);
        sc.close();
    }

    private static void cargarDatosDePrueba(PlatoService service) {
        service.guardar(new Plato("Milanesas a la napolitana", 3500, 10, "Delivery"));
        service.guardar(new Plato("Pollo al horno con papas", 3200, 10, "Delivery"));
        service.guardar(new Plato("Filet de merluza a la romana con fritas", 3500, 5, "Menú"));
        service.guardar(new Plato("Ravioles de verdura con salsa 4 quesos", 3000, 20, "Menú"));
        service.guardar(new Plato("Costillitas de cerdo a la riojana", 4500, 5, "Salón"));
        System.out.println("✔ Se cargaron 5 Platos de prueba.\n");
    }
}
