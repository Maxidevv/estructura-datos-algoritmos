/*
Prompt inicial utilizado:
Quiero implementar en Java una COLA (queue) usando una lista enlazada simple de enteros.

Estructura necesaria: lista enlazada simple con DOS referencias: head (frente) y tail
(final).

Por qué corresponde usar una cola: la cola es FIFO (First In, First Out): el primero que
llega es el primero que es atendido. Se encola por un extremo (final) y se desencola por el
otro (frente).

Por qué la cola necesita head y tail:
  - Se encola al FINAL: si solo tuviéramos head, habría que recorrer toda la lista para
    llegar al último nodo en cada encolado -> O(n).
  - Se desencola del FRENTE: con head es O(1).
Con tail (referencia al último nodo) el encolado es O(1): tail.siguiente = nuevo; tail = nuevo.
Problema si SOLO se usara head: encolar costaría O(n) porque siempre hay que recorrer hasta
el final. tail evita ese recorrido.

Cómo debe funcionar internamente:
  - encolar(dato): si está vacía, head = tail = nuevo; si no, tail.siguiente = nuevo;
    tail = nuevo. O(1).
  - desencolar(): se guarda head.dato, head = head.siguiente; si head queda null, tail = null.
    O(1). Hay que actualizar tail a null cuando la cola se vacía para no quedar con una
    referencia colgada.

Operaciones: encolar, desencolar, consultarFrente, estaVacia, buscar, imprimir.

Casos especiales: desencolar/consultar en cola vacía (excepción o null); al desencolar el
último elemento, actualizar tail.

Complejidad: encolar O(1), desencolar O(1), frente O(1), buscar O(n), imprimir O(n).

Prueba: encolar 10,20,30; desencolar; verificar que sale 10 primero; probar cola vacía.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión no reseteaba tail cuando la cola quedaba vacía, dejando una referencia
obsoleta. Se corrigió y se agregaron pruebas de cola vacía.
*/

/**
 * Ejercicio 2 - Cola enlazada de enteros.
 *
 * <p>Cola FIFO con lista enlazada simple usando head (frente) y tail (final).</p>
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
        }
    }

    /** Cola de enteros con head (frente) y tail (final). */
    static class Cola {
        private Nodo head; // frente
        private Nodo tail; // final
        private int size;

        /** Encola al final. O(1). */
        void encolar(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.siguiente = nuevo;
                tail = nuevo;
            }
            size++;
        }

        /** Desencola del frente. O(1). */
        int desencolar() {
            if (estaVacia()) {
                throw new IllegalStateException("No se puede desencolar: la cola está vacía");
            }
            int dato = head.dato;
            head = head.siguiente;
            if (head == null) {
                tail = null; // se vació: evitamos referencia colgada
            }
            size--;
            return dato;
        }

        /** Consulta el frente sin quitarlo. O(1). */
        int frente() {
            if (estaVacia()) {
                throw new IllegalStateException("No se puede consultar: la cola está vacía");
            }
            return head.dato;
        }

        boolean estaVacia() {
            return head == null;
        }

        int getSize() {
            return size;
        }

        /** Busca un valor en la cola. O(n). */
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

        /** Imprime del frente al final. */
        void imprimir() {
            System.out.print("Cola (frente -> final): ");
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
        System.out.println("========== EJERCICIO 2 - COLA ENLAZADA ==========");

        Cola cola = new Cola();
        System.out.println("estaVacia al inicio: " + cola.estaVacia());

        cola.encolar(10);
        cola.encolar(20);
        cola.encolar(30);
        cola.imprimir();
        System.out.println("frente: " + cola.frente());
        System.out.println("buscar(20): " + cola.buscar(20) + " | buscar(99): " + cola.buscar(99));

        System.out.println("desencolar: " + cola.desencolar());
        System.out.println("desencolar: " + cola.desencolar());
        cola.imprimir();

        System.out.println("desencolar: " + cola.desencolar());
        System.out.println("estaVacia ahora: " + cola.estaVacia());

        try {
            cola.desencolar();
        } catch (IllegalStateException e) {
            System.out.println("desencolar en cola vacía -> " + e.getMessage());
        }
        try {
            cola.frente();
        } catch (IllegalStateException e) {
            System.out.println("frente en cola vacía -> " + e.getMessage());
        }
    }
}