/*
Prompt inicial utilizado:
Quiero implementar en Java una LISTA DOBLEMENTE ENLAZADA GENÉRICA.

Debe permitir:
  - insertar al inicio;
  - insertar al final;
  - eliminar un elemento;
  - imprimir hacia adelante;
  - imprimir hacia atrás.

Qué referencias tiene cada nodo y por qué se necesita anterior y siguiente:
Cada nodo tiene TRES partes: el dato, una referencia 'siguiente' al nodo posterior y una
referencia 'anterior' al nodo previo. La lista mantiene head (primer nodo) y tail (último
nodo).
  - 'siguiente' permite recorrer hacia adelante.
  - 'anterior' permite recorrer hacia atrás (imprimir al revés y volver desde tail a head).
En una lista simplemente enlazada no se puede retroceder sin recorrerla desde el inicio;
con 'anterior' cada nodo conoce a ambos vecinos, lo que hace simétrico el recorrido.

Cómo funciona internamente:
  - insertarAlInicio: nuevo.siguiente = head; si head != null, head.anterior = nuevo;
    head = nuevo; si tail == null (lista vacía), tail = nuevo.
  - insertarAlFinal: nuevo.anterior = tail; si tail != null, tail.siguiente = nuevo;
    tail = nuevo; si head == null (lista vacía), head = nuevo.
  - eliminar(dato): se localiza el nodo y se reenganchan sus vecinos:
      nodo.anterior.siguiente = nodo.siguiente  y  nodo.siguiente.anterior = nodo.anterior.
  Las operaciones en los extremos son O(1); insertar/eliminar buscando por valor es O(n).

Casos especiales al eliminar:
  1) Lista vacía -> nada que eliminar.
  2) Eliminar el ÚNICO nodo -> head = tail = null (no dejar referencias colgadas).
  3) Eliminar el PRIMER nodo (head) -> head = head.siguiente; si head != null,
     head.anterior = null (ya no hay anterior).
  4) Eliminar el ÚLTIMO nodo (tail) -> tail = tail.anterior; si tail != null,
     tail.siguiente = null.
  5) Eliminar un nodo del MEDIO -> reenganchar anterior y siguiente sin tocar head/tail.
  6) Actualizar siempre size y usar equals() para comparar en la versión genérica.

Complejidad: insertar al inicio/final O(1); eliminar O(n) (hay que buscar el valor);
imprimir adelante/atrás O(n).

Prueba: insertar al inicio/final, imprimir en ambos sentidos, eliminar primero, último,
único, del medio e inexistente; probar con Integer y String.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión fallaba al eliminar el único nodo y al eliminar head/tail porque no
actualizaba las referencias de los extremos. Se corrigieron esos casos especiales y se usó
equals() con chequeo de null para la búsqueda genérica.
*/

/**
 * Ejercicio 10 - Lista doblemente enlazada genérica.
 *
 * <p>Cada nodo tiene 'anterior' y 'siguiente'; soporta inserción al inicio/final,
 * eliminación por valor, e impresión hacia adelante y hacia atrás.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio10 {

    /**
     * Nodo doblemente enlazado.
     *
     * @param <T> tipo del dato
     */
    static class Nodo<T> {
        T dato;
        Nodo<T> anterior;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    /**
     * Lista doblemente enlazada genérica.
     *
     * @param <T> tipo de los elementos
     */
    static class ListaDoble<T> {
        private Nodo<T> head;
        private Nodo<T> tail;
        private int size;

        /** Inserta al inicio. O(1). */
        void insertarAlInicio(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            if (head == null) {            // lista vacía
                head = nuevo;
                tail = nuevo;
            } else {
                nuevo.siguiente = head;
                head.anterior = nuevo;
                head = nuevo;
            }
            size++;
        }

        /** Inserta al final. O(1). */
        void insertarAlFinal(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            if (tail == null) {            // lista vacía
                head = nuevo;
                tail = nuevo;
            } else {
                nuevo.anterior = tail;
                tail.siguiente = nuevo;
                tail = nuevo;
            }
            size++;
        }

        /** Elimina la primera aparición de un valor. O(n). */
        boolean eliminar(T dato) {
            Nodo<T> actual = head;
            while (actual != null && !iguales(actual.dato, dato)) {
                actual = actual.siguiente;
            }
            if (actual == null) {
                return false; // no está
            }

            if (actual == head && actual == tail) {      // único nodo
                head = null;
                tail = null;
            } else if (actual == head) {                 // primer nodo
                head = actual.siguiente;
                head.anterior = null;
            } else if (actual == tail) {                 // último nodo
                tail = actual.anterior;
                tail.siguiente = null;
            } else {                                     // nodo del medio
                actual.anterior.siguiente = actual.siguiente;
                actual.siguiente.anterior = actual.anterior;
            }
            size--;
            return true;
        }

        private boolean iguales(T a, T b) {
            if (a == null) {
                return b == null;
            }
            return a.equals(b);
        }

        int getSize() {
            return size;
        }

        boolean estaVacia() {
            return head == null;
        }

        /** Imprime de head a tail. O(n). */
        void imprimirAdelante() {
            System.out.print("Adelante: ");
            if (head == null) {
                System.out.println("vacía");
                return;
            }
            Nodo<T> actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " <-> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }

        /** Imprime de tail a head (aprovecha 'anterior'). O(n). */
        void imprimirAtras() {
            System.out.print("Atrás:    ");
            if (tail == null) {
                System.out.println("vacía");
                return;
            }
            Nodo<T> actual = tail;
            while (actual != null) {
                System.out.print(actual.dato + " <-> ");
                actual = actual.anterior;
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
        System.out.println("========== EJERCICIO 10 - LISTA DOBLEMENTE ENLAZADA ==========");

        ListaDoble<Integer> lista = new ListaDoble<>();
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(40);
        lista.imprimirAdelante();
        lista.imprimirAtras();
        System.out.println("size: " + lista.getSize());

        System.out.println("\n-- Eliminar primer nodo (10) --");
        System.out.println("eliminar(10): " + lista.eliminar(10));
        lista.imprimirAdelante();
        lista.imprimirAtras();

        System.out.println("\n-- Eliminar último nodo (40) --");
        System.out.println("eliminar(40): " + lista.eliminar(40));
        lista.imprimirAdelante();
        lista.imprimirAtras();

        System.out.println("\n-- Eliminar nodo del medio (30) --");
        System.out.println("eliminar(30): " + lista.eliminar(30));
        lista.imprimirAdelante();
        lista.imprimirAtras();

        System.out.println("\n-- Eliminar el único nodo (20) --");
        System.out.println("eliminar(20): " + lista.eliminar(20));
        lista.imprimirAdelante();
        System.out.println("estaVacia: " + lista.estaVacia());

        System.out.println("\n-- Eliminar inexistente en lista vacía --");
        System.out.println("eliminar(99): " + lista.eliminar(99));

        System.out.println("\n-- Prueba con String --");
        ListaDoble<String> nombres = new ListaDoble<>();
        nombres.insertarAlFinal("Ana");
        nombres.insertarAlInicio("Beto");
        nombres.insertarAlFinal("Carla");
        nombres.imprimirAdelante();
        nombres.imprimirAtras();
        nombres.eliminar("Ana");
        System.out.println("tras eliminar Ana:");
        nombres.imprimirAdelante();
        nombres.imprimirAtras();
    }
}