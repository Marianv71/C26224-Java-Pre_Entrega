package ui;

import java.util.List;
import java.util.Scanner;
import model.Plato;
import service.PlatoService;
import util.Validador;

/*
    Manejar la interacción con el usuario: mostrar el menú, leer datos, mostrar mensajes de error o éxito.

    Responsable de:
        -Mostrar el menú al usuario
        -Pedirle los datos
        -Mostrar los resultados
    
    No contiene la lógica del negocio
    No controla el flujo del programa    
*/

public class MenuPlatos {

/* Atributo : el scanner y el service se reciben por constructor ( no se crean acá adentro )
Para quien instancie la clase tenga el control sobre que usar
 */
    private final Scanner sc;
    private final PlatoService service;

    // "Inyección por constructor" patrón utilizado en Spring Boot
    public MenuPlatos(Scanner sc, PlatoService service) {
        this.sc = sc;
        this.service = service;
    }

    // Menu principal

    public void mostrarMenu() {
        System.out.println("======= Como en Casa - Gestión de Platos =======");
        System.out.println("1) Agregar Platos al Menú");
        System.out.println("2) Listar Platos");
        System.out.println("3) Buscar Plato por ID");
        System.out.println("4) Actualizar Platos");
        System.out.println("5) Eliminar Plato");
        System.out.println("6) Salir");
        System.out.println("==============================================");
    }

    // Operaciones del CRUD
    // Cada método corresponde a una opción del menú

    public void agregarPlato() {
        System.out.println("--- Nuevo Plato ---");
        String nombre = Validador.leerTexto(sc, "Nombre del Plato: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría (Salón / Delivery): ");

        // Construimos el Plato
        Plato p = new Plato(nombre, precio, stock, categoria);
        // lo enviamos al servicio
        Plato guardado = service.guardar(p); // El servicio se encarga de validar y asignar el ID
        
        System.out.println("Plato agregado al Menú, con el ID " + guardado.getId());

    }

    public void listarPlatos(){
        // Recibimos un List ( ver PlatoService )
        List<Plato> lista = service.listarTodos();
        if (lista.isEmpty()){
            System.out.println("No hay Platos cargados.");
            return;
        }
    
        System.out.println("--- Platos en el Menú ---");
        for(Plato p : lista){
            //System.out.println(p);
            p.mostrar();
        }
    }

    public void buscarPlato(){
        int id = Validador.leerEntero(sc, "Ingrese el ID del Plato a buscar: ");

        Plato p = service.obtenerPorId(id);
        System.out.println("Plato Encontrado: " + p);
    }

    public void actualizarPlato() {
        int id = Validador.leerEntero(sc, "Ingrese el ID del Plato a actualizar: ");

        // Mostramos primero los datos actuales para que el usuario sepa qué está modificando.
        Plato actual = service.obtenerPorId(id);
        System.out.println("Datos actuales del Plato: " + actual);

        System.out.println("--- Ingrese los nuevos datos ---");
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        double precio = Validador.leerDouble(sc, "Precio: ");
        int stock = Validador.leerEntero(sc, "Stock: ");
        String categoria = Validador.leerTexto(sc, "Categoría (Salón / Delivery): ");

        Plato datos = new Plato(nombre, precio, stock, categoria);
        Plato actualizado = service.actualizar(id, datos);

        System.out.println("Plato actualizado: " + actualizado);
    }

    public void eliminarPlato() {
        int id = Validador.leerEntero(sc, "Ingrese el ID del Plato a eliminar: ");
        service.eliminar(id);
        System.out.println("Plato eliminado del Menú.");
    }


}


