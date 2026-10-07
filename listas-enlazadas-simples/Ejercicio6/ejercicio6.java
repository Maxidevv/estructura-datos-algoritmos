/*
Prompt inicial utilizado:
Quiero implementar en Java el método:

  void eliminarEnPosicion(int posicion)

en una lista enlazada simple de enteros. Debe eliminar el nodo que ocupa esa posición y
actualizar size.

Validación:
  - Si posicion < 0 o posicion >= size -> lanzar IndexOutOfBoundsException.

Cómo encontrar el nodo ANTERIOR al que se quiere eliminar:
Para eliminar el nodo de la posición i, hay que llegar al nodo de la posición i - 1 (el
anterior) y hacer que su enlace salte por encima del eliminado:

  anterior.siguiente = anterior.siguiente.siguiente;

Así el nodo de la posición i queda desconectado de la cadena y el GC lo libera.

Casos:
  - posicion == 0 -> eliminar el primero: head = head.siguiente.
  - posicion > 0 -> recorrer hasta la posición i - 1 y reenlazar.
  - lista vacía -> posición inválida (no hay posiciones válidas).

Estructura de clases: Nodo y ListaEnlazada con head y size.

Prueba de ejecución: eliminar la primera, una del medio, la última y probar posiciones
inválidas.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión validaba posicion > size en lugar de posicion >= size, permitiendo
eliminar una posición que no existía (la posición size) y produciendo NullPointerException.
Se corrigió la validación a posicion >= size.
*/

/**
 * Ejercicio 6 - Eliminar un nodo por posición.
 *
 * <p>Elimina el nodo que ocupa una posición, validando índices y actualizando size.
 * Explica cómo ubicar el nodo anterior y reenlazarlo para saltar al eliminado.</p>
 *
 * <p>Complejidad: O(n) temporal, O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio6 {

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
         * Elimina el nodo de la posición indicada.
         *
         * @param posicion índice 0-based a eliminar
         * @throws IndexOutOfBoundsException si posicion es inválida
         */
        void eliminarEnPosicion(int posicion) {
            if (posicion < 0 || posicion >= size) {
                throw new IndexOutOfBoundsException(
                        "Posición inválida: " + posicion + " (size = " + size + ")");
            }

            // Caso: eliminar el primer nodo
            if (posicion == 0) {
                head = head.siguiente;
                size--;
                return;
            }

            // Caso: eliminar un nodo del medio o el último.
            // Se busca el nodo ANTERIOR a la posición a eliminar.
            Nodo anterior = head;
            for (int i = 0; i < posicion - 1; i++) {
                anterior = anterior.siguiente;
            }

            // El anterior salta por encima del nodo eliminado
            anterior.siguiente = anterior.siguiente.siguiente;
            size--;
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
        System.out.println("========== EJERCICIO 6 - ELIMINAR POR POSICIÓN ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        lista.imprimir();
        System.out.println();

        lista.eliminarEnPosicion(0); // elimina el primero (10)
        System.out.print("Tras eliminarEnPosicion(0): ");
        lista.imprimir();

        lista.eliminarEnPosicion(1); // elimina el del medio (30)
        System.out.print("Tras eliminarEnPosicion(1): ");
        lista.imprimir();

        lista.eliminarEnPosicion(1); // elimina el último (40)
        System.out.print("Tras eliminarEnPosicion(1): ");
        lista.imprimir();
        System.out.println("size = " + lista.size);

        System.out.println();
        try {
            lista.eliminarEnPosicion(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("eliminarEnPosicion(5) -> Error: " + e.getMessage());
        }
        try {
            lista.eliminarEnPosicion(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("eliminarEnPosicion(-1) -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("CÓMO SE ELIMINA:");
        System.out.println("- posicion 0: head = head.siguiente.");
        System.out.println("- posicion > 0: llegar al nodo anterior y hacer");
        System.out.println("  anterior.siguiente = anterior.siguiente.siguiente.");
    }
}