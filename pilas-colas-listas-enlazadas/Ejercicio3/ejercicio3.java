/*
Prompt inicial utilizado:
Quiero convertir la pila enlazada de enteros en una PILA GENÉRICA que pueda almacenar
cualquier tipo de dato.

Qué significa usar genéricos en Java:
Los genéricos permiten declarar una clase con un parámetro de tipo (por ejemplo <T>). Así la
misma clase sirve para Integer, String, objetos propios, etc., y el compilador garantiza la
seguridad de tipos (no hay que hacer casts manuales). En tiempo de ejecución la información
de T se borra (type erasure), por lo que la comparación de valores debe hacerse con
equals(), no con ==, cuando T es un objeto.

Estructura necesaria: Pila<T> y Nodo<T> con lista enlazada simple; head es la cima.

Por qué la lógica NO depende del tipo de dato:
La pila solo manipula REFERENCIAS a los nodos (enlaces .siguiente) y mueve el head. No
realiza operaciones aritméticas ni conoce el contenido del dato. Por eso push/pop/peek/
isEmpty funcionan igual sin importar T. Solo la búsqueda/comparación debe usar equals().

Operaciones: apilar/push, desapilar/pop, tope/peek, estaVacia, buscar, imprimir.

Casos especiales: pila vacía en pop/peek; buscar con equals y manejo de null.

Complejidad: push O(1), pop O(1), peek O(1), buscar O(n).

Prueba: Pila<Integer>, Pila<String> y Pila<Persona> (objeto propio con equals).

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión comparaba con == y fallaba con String/objetos propios. Se cambió a
equals() con chequeo de null y se implementó equals()/hashCode() en Persona.
*/

/**
 * Ejercicio 3 - Pila genérica.
 *
 * <p>Pila enlazada genérica (LIFO) que funciona con Integer, String y objetos propios.</p>
 *
 * @param <T> tipo de los elementos almacenados (declarado por método/clase interna)
 * @author maxidev
 * @version 1.0
 */
class Ejercicio3 {

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
     * Pila genérica basada en lista enlazada simple.
     *
     * @param <T> tipo de los elementos
     */
    static class Pila<T> {
        private Nodo<T> head; // cima
        private int size;

        void push(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            nuevo.siguiente = head;
            head = nuevo;
            size++;
        }

        T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("No se puede hacer pop: la pila está vacía");
            }
            T dato = head.dato;
            head = head.siguiente;
            size--;
            return dato;
        }

        T peek() {
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

        /** Busca con equals (no con ==) para tipos objeto. */
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
            System.out.print("Pila (cima -> base): ");
            Nodo<T> actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
    }

    /** Objeto propio de prueba. */
    static class Persona {
        String nombre;
        int edad;

        Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Persona persona = (Persona) o;
            return edad == persona.edad && nombre.equals(persona.nombre);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(nombre, edad);
        }

        @Override
        public String toString() {
            return nombre + "(" + edad + ")";
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 3 - PILA GENÉRICA ==========");

        Pila<Integer> enteros = new Pila<>();
        enteros.push(1);
        enteros.push(2);
        enteros.push(3);
        System.out.print("[Integer] ");
        enteros.imprimir();
        System.out.println("pop: " + enteros.pop() + " | peek: " + enteros.peek()
                + " | buscar(1): " + enteros.buscar(1));

        Pila<String> strings = new Pila<>();
        strings.push("uno");
        strings.push("dos");
        strings.push("tres");
        System.out.print("[String] ");
        strings.imprimir();
        System.out.println("buscar(\"dos\"): " + strings.buscar("dos"));

        Pila<Persona> personas = new Pila<>();
        personas.push(new Persona("Ana", 30));
        personas.push(new Persona("Luis", 25));
        System.out.print("[Persona] ");
        personas.imprimir();
        System.out.println("buscar(Ana,30): " + personas.buscar(new Persona("Ana", 30)));

        System.out.println("\nLa lógica de push/pop/peek no cambia según T:");
        System.out.println("solo se mueven referencias; la búsqueda sí usa equals().");
    }
}