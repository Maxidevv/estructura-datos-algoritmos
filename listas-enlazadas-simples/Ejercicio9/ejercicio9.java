/*
Prompt inicial utilizado:
Quiero implementar en Java el método:

  void invertir()

en una lista enlazada simple de enteros, que invierta el orden de los nodos.

Ejemplo:
  10 -> 20 -> 30 -> 40 -> null
Después de invertir:
  40 -> 30 -> 20 -> 10 -> null

Qué referencias auxiliares hacen falta (y por qué):
Invertir una lista simple sin perder nodos requiere TRES referencias auxiliares:
  - anterior: el nodo que en la lista original venía antes (al inicio es null, porque el
    primer nodo pasará a ser el último).
  - actual: el nodo que estamos procesando.
  - siguiente: un puntero temporal para NO perder el resto de la lista cuando cambiamos
    el enlace de actual.

Algoritmo (recorrido iterativo):
  anterior = null;
  actual = head;
  while (actual != null) {
      siguiente = actual.siguiente; // 1) guardar el resto
      actual.siguiente = anterior;  // 2) invertir el enlace
      anterior = actual;            // 3) avanzar anterior
      actual = siguiente;           // 4) avanzar actual
  }
  head = anterior; // el último nodo procesado es el nuevo primero

Por qué el orden de actualización es fundamental:
Primero SIEMPRE hay que guardar siguiente = actual.siguiente ANTES de cambiar
actual.siguiente. Si se invierte el enlace antes de guardar el siguiente, se pierde la
referencia al resto de la lista y los nodos restantes quedan inaccesibles (se "corta" la
lista). Con el orden correcto, ningún nodo se pierde y la lista queda invertida.

Estructura de clases: Nodo y ListaEnlazada con head y size (size no cambia al invertir).

Prueba de ejecución: invertir 10 -> 20 -> 30 -> 40 y verificar 40 -> 30 -> 20 -> 10.

Ajustes realizados luego de la primera respuesta de OpenCode:
No fue necesario ajustar el prompt. La primera versión usó correctamente las tres
referencias y actualizó head al final. Se agregó una impresión del estado intermedio de
los enlaces para evidenciar el proceso.
*/

/**
 * Ejercicio 9 - Invertir una lista enlazada simple.
 *
 * <p>Invierte el orden de los nodos de la lista mediante tres referencias auxiliares
 * (anterior, actual, siguiente), explicando por qué el orden de actualización es
 * fundamental para no perder nodos.</p>
 *
 * <p>Complejidad: O(n) temporal, O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio9 {

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

        /** Invierte el orden de los nodos de la lista. */
        void invertir() {
            Nodo anterior = null;   // el futuro último nodo
            Nodo actual = head;     // nodo que se procesa
            Nodo siguiente;         // referencia temporal para no perder el resto

            while (actual != null) {
                siguiente = actual.siguiente; // 1) guardar el resto ANTES de tocar el enlace
                actual.siguiente = anterior;  // 2) invertir el enlace del nodo actual
                anterior = actual;            // 3) avanzar anterior
                actual = siguiente;           // 4) avanzar actual
            }

            head = anterior; // el último procesado pasa a ser el nuevo primero
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
        System.out.println("========== EJERCICIO 9 - INVERTIR LISTA ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        System.out.print("Antes:  ");
        lista.imprimir();

        lista.invertir();

        System.out.print("Después:");
        // pequeño ajuste de alineación
        System.out.print(" ");
        lista.imprimir();
        System.out.println("size (no cambia): " + lista.size);

        System.out.println();
        System.out.println("REFERENCIAS AUXILIARES Y ORDEN:");
        System.out.println("1) siguiente = actual.siguiente;  // guardar resto PRIMERO");
        System.out.println("2) actual.siguiente = anterior;   // invertir enlace");
        System.out.println("3) anterior = actual;             // avanzar");
        System.out.println("4) actual = siguiente;            // avanzar");
        System.out.println("Si se invierte el enlace antes de guardar 'siguiente',");
        System.out.println("se pierde el resto de la lista (nodos inaccesibles).");
    }
}