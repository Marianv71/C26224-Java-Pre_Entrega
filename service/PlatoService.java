package service;

import java.util.ArrayList;
import java.util.List;

import exception.PlatoNoEncontrado;
import model.Plato;
import util.Validador;


/* Capa de Servicio: contiene la lógica del negocio de nuestro sistema
    Es responsable de:
        - Mantener la coleccion de platos
        - Asignar el ID al guardar un nuevo plato
        - Validar los datos antes de guardar o actualizar
        - Buscar, modificar y eliminar platos por ID

    No tiene Scanner ni System.out : no interuactúa con el usuario.
    Quien quiera mostrar mensajes o leer datos lo hace por afuera ( lo hace la clase main )

    ******    
    SPOILER: esta separación nos va a permitir en clases siguientes , reemplazar el menu por una API REST sin tocar este archivo
    ******
*/

public class PlatoService {
// colección en memoria que guarda los platos del menú
    private List<Plato> platos = new ArrayList<>();


    /* Contador para asignar ID's únicos. Es static porque pertenece a la clase y no a una instancia
       garantiza que el ID sea único aunque hubiera varias instancias de PlatoService 
    */

    private static int contadorId = 1;

    // OPERACIONES CRUD ( Create, Read, Update, Delete )

    // CREATE :  agregar un nuevo plato
    public Plato guardar (Plato p) {
        /* Validamos antes de guardar. Si algo esta mal, se lanza la 
           excepción y el plato NO se agrega a la lista 
        */

        Validador.validarNombre(p.getNombre());
        Validador.validarPrecio(p.getPrecio());
        Validador.validarStock(p.getStock());
        Validador.validarCategoria(p.getCategoria());

        /* 
          El ID lo asigna el servicio, no el usuario. 
          Después de asignarlo, incrementamos el contador
        */

        p.setId(contadorId);
        contadorId++;

        // Guardo el plato
        
        platos.add(p);
        return p;

    }

    //Read: devuelve toda la lista de Platos

    public List<Plato> listarTodos(){
        return platos;
    }

    // Buscar un Plato por ID 

    public Plato obtenerPorId(int id) {
        for(Plato p : platos){
            if(p.getId() == id){
                return p;
            }
        }

        throw new PlatoNoEncontrado("No se encontró un plato con el ID " + id);
    }

    // Update: actualiza los datos de un plato existente

    public Plato actualizar(int id,Plato datos){
        // Reutilizamos obtenerPorId . Si lanza excepción la actualización se cancela
        Plato p = obtenerPorId(id);

        //Validamos los datos antes de aplicarlos
        Validador.validarNombre(datos.getNombre());
        Validador.validarPrecio(datos.getPrecio());
        Validador.validarStock(datos.getStock());
        Validador.validarCategoria(datos.getCategoria());

        /* Modificamos el plato encontrado
           Como Java pasa los objetos por referencia, los cambios se reflejan en la lista
           sin necesidad de hacer nada más.
         */

        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());

        return p;
    }

    // DELETE: eliminar un plato por ID

    public void eliminar(int id){
        Plato p = obtenerPorId(id);
        platos.remove(p);
    }    
}
