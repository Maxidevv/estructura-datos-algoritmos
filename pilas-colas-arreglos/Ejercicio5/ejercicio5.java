/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una clase Pila<T> (genérica) usando arreglos, que pueda
 * almacenar distintos tipos de datos: Integer, String u objetos simples.
 *
 * Estructura necesaria:
 *   - Un arreglo genérico de tipo T de tamaño fijo como almacenamiento.
 *   - Un índice top igual que en la pila de enteros (inicia en -1).
 *
 * Operaciones que debe tener:
 *   - push(T valor)
 *   - pop(): T
 *   - peek(): T
 *   - isEmpty(), isFull(), size()
 *
 * Qué problema resuelve el uso de genéricos:
 *   - Sin genéricos, para cada tipo de dato habría que escribir una pila distinta
 *     (PilaDeEnteros, PilaDeStrings, PilaDeObjetos...). Con Pila<T> se escribe UNA sola
 *     clase y se reutiliza para todos los tipos.
 *   - Se diferencia de una pila hecha solamente con int porque esa pila solo acepta
 *     primitivos de tipo int, mientras que Pila<T> acepta Integer, String o cualquier
 *     objeto, y da seguridad de tipos en compilación.
 *
 * Casos límite:
 *   - Pila llena -> push() lanza error.
 *   - Pila vacía -> pop() y peek() lanzan error.
 *
 * Comportamiento esperado: una pila LIFO genérica.
 */

/**
 * Ejercicio 5 - Implementar una pila genérica.
 *
 * <p>Implementa la clase Pila&lt;T&gt; con arreglos, que permite almacenar cualquier tipo
 * de dato (Integer, String u objetos simples). Explica el problema que resuelven los
 * genéricos frente a una pila hecha solo con int.</p>
 *
 * <p>Complejidad: todas las operaciones son O(1).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio5 {

    /**
     * Pila genérica con arreglo de tamaño fijo.
     *
     * <p>El arreglo interno se crea con Object y se castea a T[]: en Java no se permite
     * crear arreglos genéricos directamente, pero el cast es seguro porque solo se
     * insertan elementos T a través de push().</p>
     *
     * @param <T> tipo de los elementos almacenados
     */
    static class Pila<T> {
        private final T[] datos;
        private final int capacidad;
        private int top; // -1 cuando está vacía

        @SuppressWarnings("unchecked")
        Pila(int capacidad) {
            this.capacidad = capacidad;
            this.datos = (T[]) new Object[capacidad];
            this.top = -1;
        }

        void push(T valor) {
            if (isFull()) {
                throw new IllegalStateException("Pila llena: no se puede apilar " + valor);
            }
            datos[++top] = valor;
        }

        T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Pila vacía: no se puede desapilar");
            }
            T valor = datos[top];
            datos[top] = null; // se libera la referencia
            top--;
            return valor;
        }

        T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Pila vacía: no hay tope que consultar");
            }
            return datos[top];
        }

        boolean isEmpty() {
            return top == -1;
        }

        boolean isFull() {
            return top == capacidad - 1;
        }

        int size() {
            return top + 1;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 5 - PILA GENÉRICA ==========");

        // Pila de Integer
        Pila<Integer> pilaEnteros = new Pila<>(3);
        pilaEnteros.push(100);
        pilaEnteros.push(200);
        System.out.println("Pila<Integer>: peek = " + pilaEnteros.peek()
                + " | pop = " + pilaEnteros.pop() + " | size = " + pilaEnteros.size());

        // Pila de String
        Pila<String> pilaStrings = new Pila<>(3);
        pilaStrings.push("hola");
        pilaStrings.push("mundo");
        pilaStrings.push("!");
        System.out.println("Pila<String>: peek = " + pilaStrings.peek()
                + " | isFull = " + pilaStrings.isFull() + " | size = " + pilaStrings.size());

        // Pila de un objeto simple
        Pila<Point> pilaObjetos = new Pila<>(2);
        pilaObjetos.push(new Point(1, 2));
        pilaObjetos.push(new Point(3, 4));
        System.out.println("Pila<Point>: peek = " + pilaObjetos.peek()
                + " | pop = " + pilaObjetos.pop() + " | size = " + pilaObjetos.size());

        System.out.println();
        System.out.println("QUÉ PROBLEMA RESUELVEN LOS GENÉRICOS:");
        System.out.println("- Con UNA sola clase Pila<T> se almacenan Integer, String y objetos.");
        System.out.println("- Sin genéricos habría que repetir una clase para cada tipo.");
        System.out.println("- A diferencia de una pila solo de int, no se limita a primitivos");
        System.out.println("  y el compilador valida el tipo usado (seguridad en compilación).");
    }

    /** Clase auxiliar mínima para demostrar el uso de genéricos con objetos. */
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// El prompt original se mantuvo en esencia. Se aclaró en el código que en Java no se
// pueden crear arreglos genéricos directamente ((T[]) new Object[capacidad]) y se
// documentó ese detalle como parte del manejo del arreglo interno.
// ---------------------------------------------------------------------------