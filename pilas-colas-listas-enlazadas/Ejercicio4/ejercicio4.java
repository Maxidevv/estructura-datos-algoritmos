/*
Prompt inicial utilizado:
Quiero convertir la cola enlazada de enteros en una COLA GENÉRICA que pueda almacenar
cualquier tipo de dato, y probarla con una cola de nombres (String) y una cola de objetos
Cliente.

Estructura necesaria: Cola<T> y Nodo<T> con lista enlazada simple y dos referencias: head
(frente) y tail (final).

Cómo se mantiene el comportamiento FIFO independientemente del tipo de dato:
El orden FIFO lo garantiza la ESTRUCTURA (dónde se inserta y de dónde se saca): se encola
siempre por tail y se desencola siempre por head. Eso es independiente de qué haya dentro
del nodo. Los genéricos solo cambian el tipo del dato almacenado; el flujo
"primero encolado = primero desencolado" se mantiene siempre.

Por qué head y tail: encolar por el final con tail es O(1); desencolar por el frente con
head es O(1). Con solo head, encolar costaría O(n).

Operaciones: encolar, desencolar, frente, estaVacia, buscar (con equals) e imprimir.

Casos especiales: cola vacía; actualizar tail = null al vaciarse; comparar con equals.

Complejidad: encolar O(1), desencolar O(1), frente O(1), buscar O(n).

Prueba: Cola<String> con nombres y Cola<Cliente> con objetos Cliente.

Ajustes realizados luego de la primera respuesta de OpenCode:
Se reemplazó la comparación == por equals() con chequeo de null, y se implementó
equals()/hashCode() en Cliente para que la búsqueda funcione por valor.
*/

/**
 * Ejercicio 4 - Cola genérica.
 *
 * <p>Cola FIFO genérica que funciona con nombres (String) y objetos Cliente.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio4 {

    /**
     * Nodo genérico.
     *
     * @param <T> tipo del dato
     */
    static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    /**
     * Cola genérica con head (frente) y tail (final).
     *
     * @param <T> tipo de los elementos
     */
    static class Cola<T> {
        private Nodo<T> head; // frente
        private Nodo<T> tail; // final
        private int size;

        void encolar(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            if (head == null) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.siguiente = nuevo;
                tail = nuevo;
            }
            size++;
        }

        T desencolar() {
            if (estaVacia()) {
                throw new IllegalStateException("No se puede desencolar: la cola está vacía");
            }
            T dato = head.dato;
            head = head.siguiente;
            if (head == null) {
                tail = null;
            }
            size--;
            return dato;
        }

        T frente() {
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

        boolean buscar(T dato) {
            Nodo<T> actual = head;
            while (actual != null) {
                if (iguales(actual.dato, dato)) {
                    return true;
                }
                actual = actual.siguiente;
            }
            return false;
        }

        private boolean iguales(T a, T b) {
            if (a == null) {
                return b == null;
            }
            return a.equals(b);
        }

        void imprimir() {
            System.out.print("Cola (frente -> final): ");
            Nodo<T> actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
    }

    /** Objeto propio de prueba. */
    static class Cliente {
        String nombre;
        int id;

        Cliente(String nombre, int id) {
            this.nombre = nombre;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cliente cliente = (Cliente) o;
            return id == cliente.id && nombre.equals(cliente.nombre);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(nombre, id);
        }

        @Override
        public String toString() {
            return nombre + "#" + id;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 4 - COLA GENÉRICA ==========");

        Cola<String> nombres = new Cola<>();
        nombres.encolar("Ana");
        nombres.encolar("Beto");
        nombres.encolar("Carla");
        System.out.print("[String] ");
        nombres.imprimir();
        System.out.println("frente: " + nombres.frente());
        System.out.println("desencolar: " + nombres.desencolar() + " (debe salir Ana primero)");
        System.out.print("[String] ");
        nombres.imprimir();

        Cola<Cliente> clientes = new Cola<>();
        clientes.encolar(new Cliente("Juan", 1));
        clientes.encolar(new Cliente("Maria", 2));
        clientes.encolar(new Cliente("Pedro", 3));
        System.out.print("\n[Cliente] ");
        clientes.imprimir();
        System.out.println("buscar(Juan#1): " + clientes.buscar(new Cliente("Juan", 1)));
        System.out.println("desencolar: " + clientes.desencolar() + " (debe salir Juan#1 primero)");
        System.out.print("[Cliente] ");
        clientes.imprimir();

        System.out.println("\nEl FIFO lo garantiza la estructura (entra por tail, sale por head),");
        System.out.println("no el tipo de dato almacenado.");
    }
}