/*
Prompt inicial utilizado:
Quiero implementar en Java el método:

  boolean eliminar(int dato)

en una lista enlazada simple de enteros. Debe eliminar la primera aparición del dato y
devolver true si lo eliminó, false si el dato no existe.

Casos a contemplar:
  - Lista vacía: no hay nada que eliminar -> false.
  - Eliminar el primer nodo: head debe pasar a head.siguiente.
  - Eliminar un nodo del medio: el nodo anterior debe saltar por encima del eliminado,
    es decir anterior.siguiente = eliminado.siguiente.
  - Eliminar el último nodo: el nuevo último queda con siguiente == null.
  - Dato inexistente: se recorre toda la lista y se devuelve false.

Para el caso del medio y del final se usan dos referencias: "anterior" y "actual". Se
recorre hasta que actual.dato == dato; entonces anterior.siguiente = actual.siguiente.

En Java no se borra manualmente el nodo:
No existe free() ni delete como en C/C++. Lo único que se hace es quitar la referencia
al nodo (desenlazarlo) para que quede inaccesible. A partir de ese momento, si no hay
ninguna otra referencia hacia él, el Garbage Collector puede reclamar su memoria
automáticamente. Por eso se dice que el GC se encarga de la liberación.

Estructura de clases: Nodo y ListaEnlazada con head y size.

Prueba de ejecución: eliminar primero, del medio, último, inexistente y en lista vacía.

Ajustes realizados luego de la primera respuesta de OpenCode:
No fue necesario ajustar el prompt. La primera versión ya manejaba el caso del primer
nodo por separado (cambio de head) y usaba anterior/actual para el resto. Se agregó el
decremento de size para mantenerlo consistente.
*/

/**
 * Ejercicio 5 - Eliminar un nodo por valor.
 *
 * <p>Elimina la primera aparición de un dato en la lista enlazada simple, contemplando
 * los casos de lista vacía, primer nodo, nodo del medio, último nodo y dato inexistente.
 * Explica que en Java el nodo se deja inaccesible para el Garbage Collector.</p>
 *
 * <p>Complejidad: O(n) temporal, O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio5 {

    /** Nodo de la lista enlazada simple. */
    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /** Lista enlazada simple de enteros. */
    static class ListaEnlazada {
        Nodo head;
        int size;

        void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) {
                head = nuevo;
            } else {
                Nodo actual = head;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
            size++;
        }

        /**
         * Elimina la primera aparición de un dato.
         *
         * @param dato el valor a eliminar
         * @return true si se eliminó, false si el dato no existe o la lista está vacía
         */
        boolean eliminar(int dato) {
            // Caso: lista vacía
            if (head == null) {
                return false;
            }

            // Caso: eliminar el primer nodo -> head pasa al siguiente
            if (head.dato == dato) {
                head = head.siguiente;
                size--;
                // El nodo eliminado queda inaccesible; el GC libera su memoria.
                return true;
            }

            // Caso: nodo del medio o último. Se usan anterior y actual.
            Nodo anterior = head;
            Nodo actual = head.siguiente;

            while (actual != null) {
                if (actual.dato == dato) {
                    // El anterior "salta" por encima del nodo eliminado
                    anterior.siguiente = actual.siguiente;
                    size--;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }

            // Se recorrió toda la lista sin encontrarlo
            return false;
        }

        void imprimir() {
            System.out.print("Lista: ");
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 5 - ELIMINAR POR VALOR ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        lista.imprimir();
        System.out.println();

        System.out.println("eliminar(10) [primero] -> " + lista.eliminar(10));
        lista.imprimir();
        System.out.println("eliminar(30) [medio]   -> " + lista.eliminar(30));
        lista.imprimir();
        System.out.println("eliminar(40) [último]  -> " + lista.eliminar(40));
        lista.imprimir();
        System.out.println("eliminar(99) [inexistente] -> " + lista.eliminar(99));
        lista.imprimir();
        System.out.println("eliminar(20) [queda uno]   -> " + lista.eliminar(20));
        lista.imprimir();

        System.out.println("eliminar(1) en lista vacía -> " + lista.eliminar(1));

        System.out.println();
        System.out.println("SOBRE LA MEMORIA EN JAVA:");
        System.out.println("- No se borra el nodo manualmente (no hay free/delete).");
        System.out.println("- Solo se quita la referencia (desenlazar) y el Garbage Collector");
        System.out.println("  reclama la memoria cuando el nodo queda inaccesible.");
    }
}