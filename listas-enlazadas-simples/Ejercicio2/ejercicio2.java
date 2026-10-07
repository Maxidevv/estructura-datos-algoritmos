/*
Prompt inicial utilizado:
Quiero implementar, a partir de una lista enlazada simple de enteros, un método:

  boolean buscar(int dato)

El método debe recorrer la lista desde head hasta encontrar el dato o llegar a null
(fin de la lista). Debe devolver true si el dato existe y false en caso contrario.

Por qué no se puede acceder directamente por índice:
A diferencia de un arreglo o un ArrayList, los nodos de una lista enlazada no están
en posiciones de memoria contiguas ni hay una fórmula para saltar directamente al
elemento n-ésimo. Cada nodo solo conoce la dirección de su siguiente nodo, por lo que
la única forma de llegar a un nodo es partiendo de head y siguiendo los enlaces uno
por uno.

Por qué se requiere recorrido secuencial:
Como no hay acceso aleatorio, para encontrar un dato hay que avanzar nodo por nodo:
actual = actual.siguiente. El recorrido termina cuando se encuentra el dato (true) o
cuando actual se vuelve null (false).

Estructura de clases: Nodo {int dato; Nodo siguiente;} y ListaEnlazada {Nodo head;
int size;}.

Casos a contemplar: lista vacía (head == null), dato en el primer nodo, dato en el
último nodo, dato en el medio y dato inexistente.

Prueba de ejecución: buscar un valor presente y uno ausente.

Ajustes realizados luego de la primera respuesta de OpenCode:
No fue necesario ajustar el prompt; la primera implementación cumplió con el recorrido
secuencial desde head y el corte al encontrar el valor. Solo se agregó un mensaje que
indica en qué posición se encontró el dato, como información extra para la prueba.
*/

/**
 * Ejercicio 2 - Buscar elementos en una lista enlazada.
 *
 * <p>Implementa la búsqueda de un dato en una lista enlazada simple mediante recorrido
 * secuencial desde head, explicando por qué no hay acceso directo por índice.</p>
 *
 * <p>Complejidad: O(n) temporal, O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio2 {

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
         * Busca un dato recorriendo la lista desde head hasta null.
         *
         * @param dato el valor a buscar
         * @return true si el dato existe en la lista, false en caso contrario
         */
        boolean buscar(int dato) {
            Nodo actual = head;

            // Recorrido secuencial: no hay acceso por índice, se avanza enlace a enlace
            while (actual != null) {
                if (actual.dato == dato) {
                    return true; // encontrado
                }
                actual = actual.siguiente;
            }
            return false; // se llegó a null sin encontrarlo
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
        System.out.println("========== EJERCICIO 2 - BUSCAR ELEMENTOS ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.imprimir();

        System.out.println();
        System.out.println("buscar(10) [primer nodo]   -> " + lista.buscar(10));
        System.out.println("buscar(30) [nodo del medio]-> " + lista.buscar(30));
        System.out.println("buscar(40) [último nodo]   -> " + lista.buscar(40));
        System.out.println("buscar(99) [inexistente]   -> " + lista.buscar(99));

        ListaEnlazada vacia = new ListaEnlazada();
        System.out.println("buscar(1) en lista vacía   -> " + vacia.buscar(1));

        System.out.println();
        System.out.println("¿POR QUÉ RECORRIDO SECUENCIAL?");
        System.out.println("- Los nodos no están en memoria contigua y no hay índice directo.");
        System.out.println("- Cada nodo solo conoce a su siguiente, así que se avanza de a uno.");
        System.out.println("- Se corta si se encuentra el dato o si actual llega a null.");
    }
}