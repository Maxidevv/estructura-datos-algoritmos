/*
Prompt inicial utilizado:
Quiero implementar en Java una PILA (stack) usando una lista enlazada simple de enteros.

Estructura necesaria: lista enlazada simple donde el nodo head representa la CIMA (tope)
de la pila.

Por qué corresponde usar una pila: la pila es LIFO (Last In, First Out): el último
elemento que entra es el primero que sale. Por eso insertamos y quitamos siempre por el
mismo extremo, la cima.

Por qué head = tope: si head apunta al último elemento insertado, entonces apilar y
desapilar operan en el extremo head, que es donde debe estar el tope.

Cómo debe funcionar internamente y por qué push/pop son O(1):
  - push(dato): se crea un nodo nuevo, se hace nuevo.siguiente = head y head = nuevo.
    Solo se tocan dos referencias -> O(1).
  - pop(): se guarda head.dato, se hace head = head.siguiente y se devuelve el dato.
    Solo se actualiza una referencia -> O(1).
No hay que recorrer la lista porque no usamos el final ni buscamos posiciones.

Operaciones a implementar: push, pop, peek (tope), isEmpty, buscar (contiene) e imprimir.

Casos especiales: pop/peek sobre pila vacía (devolver error/null), usar una excepción o
un centinela; buscar en pila vacía. La búsqueda es O(n) porque hay que recorrer.

Complejidad esperada: push O(1), pop O(1), peek O(1), isEmpty O(1), buscar O(n).

Prueba de ejecución: apilar, ver tope, buscar, desapilar y forzar el caso pila vacía.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión no contemplaba pop/peek en pila vacía y lanzaba NullPointerException.
Se agregó una excepción de pila vacía y un main que prueba overflow/underflow reales.
*/

/**
 * Ejercicio 1 - Pila enlazada de enteros.
 *
 * <p>Implementa una pila (LIFO) usando una lista enlazada simple, donde head es la cima.
 * push y pop son O(1) porque operan solo sobre head.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio1 {

    /** Nodo de la lista enlazada simple. */
    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    /** Pila de enteros basada en lista enlazada simple. */
    static class Pila {
        private Nodo head; // cima
        private int size;

        /** Apila un elemento en la cima. O(1). */
        void push(int dato) {
            Nodo nuevo = new Nodo(dato);
            nuevo.siguiente = head;
            head = nuevo;
            size++;
        }

        /** Desapila y devuelve la cima. O(1). */
        int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("No se puede hacer pop: la pila está vacía");
            }
            int dato = head.dato;
            head = head.siguiente;
            size--;
            return dato;
        }

        /** Devuelve la cima sin quitarla. O(1). */
        int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("No se puede hacer peek: la pila está vacía");
            }
            return head.dato;
        }

        boolean isEmpty() {
            return head == null;
        }

        int getSize() {
            return size;
        }

        /** Busca un valor recorriendo la pila. O(n). */
        boolean buscar(int dato) {
            Nodo actual = head;
            while (actual != null) {
                if (actual.dato == dato) {
                    return true;
                }
                actual = actual.siguiente;
            }
            return false;
        }

        /** Imprime de cima a base. */
        void imprimir() {
            System.out.print("Pila (cima -> base): ");
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 1 - PILA ENLAZADA ==========");

        Pila pila = new Pila();
        System.out.println("isEmpty al inicio: " + pila.isEmpty());

        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.imprimir();
        System.out.println("peek (tope): " + pila.peek());
        System.out.println("size: " + pila.getSize());
        System.out.println("buscar(20): " + pila.buscar(20) + " | buscar(99): " + pila.buscar(99));

        System.out.println("pop: " + pila.pop());
        System.out.println("pop: " + pila.pop());
        pila.imprimir();

        System.out.println("pop: " + pila.pop());
        System.out.println("isEmpty ahora: " + pila.isEmpty());

        try {
            pila.pop();
        } catch (IllegalStateException e) {
            System.out.println("pop en pila vacía -> " + e.getMessage());
        }
        try {
            pila.peek();
        } catch (IllegalStateException e) {
            System.out.println("peek en pila vacía -> " + e.getMessage());
        }
    }
}