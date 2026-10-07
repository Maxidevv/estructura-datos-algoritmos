/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una clase PilaEnteros que represente una pila de números
 * enteros usando un arreglo de tamaño fijo.
 *
 * Estructura necesaria:
 *   - Un arreglo de enteros de tamaño fijo como espacio de almacenamiento.
 *   - Un índice llamado "top" que indica la posición del último elemento apilado.
 *   - Un atributo capacidad con el tamaño máximo permitido.
 *
 * Operaciones que debe tener:
 *   - push(int valor): aplica un elemento en el tope.
 *   - pop(): retira y devuelve el elemento del tope.
 *   - peek(): consulta el elemento del tope sin retirarlo.
 *   - isEmpty(): indica si la pila está vacía.
 *   - isFull(): indica si la pila está llena.
 *   - size(): devuelve la cantidad de elementos almacenados.
 *
 * Índice top:
 *   - Inicialmente debe valer -1, porque no hay ningún elemento apilado y el primer push
 *     coloca el valor en la posición 0 al hacer ++top.
 *
 * Casos límite:
 *   - Cuando la pila está VACÍA: pop() y peek() deben lanzar un error (underflow) porque
 *     no hay elemento que devolver.
 *   - Cuando la pila está LLENA: push() debe lanzar un error (overflow) porque no queda
 *     espacio en el arreglo de tamaño fijo.
 *
 * Comportamiento esperado: operar como una pila LIFO (último en entrar, primero en salir).
 */

/**
 * Ejercicio 1 - Implementar una pila de enteros.
 *
 * <p>Implementa la clase PilaEnteros con un arreglo de tamaño fijo y las operaciones
 * push, pop, peek, isEmpty, isFull y size, explicando el uso del índice top.</p>
 *
 * <p>Complejidad de operaciones:</p>
 * <ul>
 *   <li>push, pop, peek, isEmpty, isFull, size: O(1).</li>
 *   <li>Espacial: O(capacidad) por el arreglo fijo.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio1 {

    /**
     * Pila de enteros con arreglo de tamaño fijo.
     *
     * <p>El índice top apunta siempre al último elemento almacenado. Inicialmente vale
     * -1 (pila vacía); con cada push se incrementa antes de escribir, y con cada pop se
     * decrementa después de leer.</p>
     */
    static class PilaEnteros {
        private final int[] datos;
        private final int capacidad;
        private int top; // -1 cuando la pila está vacía

        /**
         * Crea una pila vacía con la capacidad indicada.
         *
         * @param capacidad tamaño fijo del arreglo interno
         */
        PilaEnteros(int capacidad) {
            this.capacidad = capacidad;
            this.datos = new int[capacidad];
            this.top = -1; // valor inicial: no hay elementos apilados
        }

        /**
         * Apila un elemento en el tope.
         *
         * @param valor el entero a apilar
         * @throws IllegalStateException si la pila está llena (overflow)
         */
        void push(int valor) {
            if (isFull()) {
                throw new IllegalStateException("Pila llena: no se puede apilar " + valor);
            }
            datos[++top] = valor; // se avanza top y se escribe
        }

        /**
         * Retira y devuelve el elemento del tope.
         *
         * @return el valor del tope
         * @throws IllegalStateException si la pila está vacía (underflow)
         */
        int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Pila vacía: no se puede desapilar");
            }
            return datos[top--]; // se lee y luego se retrocede top
        }

        /**
         * Devuelve el elemento del tope sin retirarlo.
         *
         * @return el valor del tope
         * @throws IllegalStateException si la pila está vacía
         */
        int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Pila vacía: no hay tope que consultar");
            }
            return datos[top];
        }

        /** @return true si la pila no tiene elementos */
        boolean isEmpty() {
            return top == -1;
        }

        /** @return true si el arreglo fijo ya no admite más elementos */
        boolean isFull() {
            return top == capacidad - 1;
        }

        /** @return la cantidad de elementos almacenados */
        int size() {
            return top + 1; // top vale -1 cuando está vacía, entonces 0 elementos
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 1 - PILA DE ENTEROS ==========");

        PilaEnteros pila = new PilaEnteros(5);

        System.out.println("Pila creada con capacidad 5. top inicial = -1");
        System.out.println("isEmpty: " + pila.isEmpty() + " | size: " + pila.size());

        // Push de elementos de ejemplo
        pila.push(10);
        pila.push(20);
        pila.push(30);
        System.out.println();
        System.out.println("Tras push(10), push(20), push(30):");
        System.out.println("   peek: " + pila.peek() + " | size: " + pila.size()
                + " | isFull: " + pila.isFull());

        // Pop del elemento superior
        int retirado = pila.pop();
        System.out.println();
        System.out.println("pop(): " + retirado + " -> se retira el tope (LIFO)");
        System.out.println("   peek: " + pila.peek() + " | size: " + pila.size());

        // Llenar la pila hasta desbordar: faltan 3 push para llegar a la capacidad 5
        pila.push(40);
        pila.push(50);
        pila.push(60);
        System.out.println();
        System.out.println("Tras push(40), push(50), push(60): isFull = " + pila.isFull());
        try {
            pila.push(70);
        } catch (IllegalStateException e) {
            System.out.println("push(70) -> Error: " + e.getMessage());
        }

        // Vaciar la pila por completo (5 pops) y verificar underflow
        System.out.println();
        System.out.print("Vaciamos la pila: ");
        pila.pop();
        pila.pop();
        pila.pop();
        pila.pop();
        pila.pop();
        System.out.println("isEmpty = " + pila.isEmpty());
        try {
            pila.pop();
        } catch (IllegalStateException e) {
            System.out.println("pop()  -> Error: " + e.getMessage());
        }
        try {
            pila.peek();
        } catch (IllegalStateException e) {
            System.out.println("peek() -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("RESUMEN DEL ÍNDICE TOP:");
        System.out.println("- Inicial: -1 (pila vacía).");
        System.out.println("- push: ++top antes de guardar.");
        System.out.println("- pop: leer y luego --top.");
        System.out.println("- Pila llena: top == capacidad - 1 (overflow).");
        System.out.println("- Pila vacía: top == -1 (underflow).");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// El prompt original se mantuvo en esencia. Se agregó el tamaño de la pila en cada
// operación y el manejo explícito de errores de overflow/underflow mediante
// excepciones, ya que la consigna pedía contemplar el caso de pila llena y vacía.
// ---------------------------------------------------------------------------