package exception;

/* Excepción personalizada que se lanza cuando se intenta asignar un Plato Sin Stock (ej: un valor negativo) 
   Si incorporamos un carrito de compras, podemos señalar el caso en que un cliente quiera comprar más unidades (Platos) de los disponibles.
   
*/

public class PlatoSinStock extends RuntimeException {
    public PlatoSinStock(String mensaje){
        super(mensaje);
    }
    
}
