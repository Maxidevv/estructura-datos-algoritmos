/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una clase ColaEnteros usando un arreglo (cola simple).
 *
 * Estructura necesaria:
 *   - Un arreglo de enteros de tamaño fijo.
 *   - Dos índices: "front" y "rear".
 *
 * Para qué sirven front y rear:
 *   - front apunta al primer elemento de la cola (quién debe salir primero, FIFO).
 *   - rear apunta al último elemento (dónde entra el próximo).
 *   - Al hacer enqueue (agregar): rear avanza (++rear) y en esa posición se escribe.
 *   - Al hacer dequeue (retirar): front avanza (++front); el valor que estaba en front
 *     ya no se considera parte de la cola.
 *
 * Operaciones que debe tener:
 *   - enqueue(int valor): agrega al final.
 *   - dequeue(): retira y devuelve el primero.
 *   - front(): consulta el primero sin retirarlo.
 *   - isEmpty(), isFull(), size().
 *
 * Casos límite:
 *   - Cola vacía: front > rear. dequeue() y front() lanzan error.
 *   - Cola llena: rear == capacidad - 1. enqueue() lanza error.
 *
 * Comportamiento esperado: operar como una cola FIFO (primero en entrar, primero en salir).
 */

/**
 * Ejercicio 6 - Implementar una cola simple de enteros.
 *
 * <p>Implementa la clase ColaEnteros con un arreglo, explicando el uso de los índices
 * front y rear y enqueue, dequeue, front, isEmpty, isFull y size.</p>
 *
 * <p>Complejidad: todas las operaciones son O(1).</p>
 *
 * <p>NOTA: esta es una cola SIMPLE; al retirar elementos el índice front avanza y las
 * posiciones liberadas al inicio ya no pueden reutilizarse (ver Ejercicio 9).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio6 {

    /**
     * Cola simple de enteros con arreglo fijo.
     *
     * <p>front y rear inician en -1. Con el primer enqueue ambos pasan a 0. enqueue
     * incrementa rear; dequeue incrementa front.</p>
     */
    static class ColaEnteros {
        private int[] datos;
        private int capacidad;
        private int front; // índice del primer elemento (el más antiguo)
        private int rear;  // índice del último elemento (el más reciente)

        ColaEnteros(int capacidad) {
            this.capacidad = capacidad;
            this.datos = new int[capacidad];
            this.front = -1;
            this.rear = -1;
        }

        /** Agrega un elemento al final de la cola (avanza rear). */
        void enqueue(int valor) {
            if (isFull()) {
                throw new IllegalStateException("Cola llena: no se puede encolar " + valor);
            }
            if (rear == -1) {
                // Primera inserción: front y rear arrancan en 0
                front = 0;
            }
            datos[++rear] = valor;
        }

        /** Retira y devuelve el primer elemento de la cola (avanza front). */
        int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Cola vacía: no se puede desencolar");
            }
            int valor = datos[front];
            if (front == rear) {
                // La cola queda vacía: se reinician los índices
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            return valor;
        }

        /** Consulta el primer elemento sin retirarlo. */
        int front() {
            if (isEmpty()) {
                throw new IllegalStateException("Cola vacía: no hay primer elemento");
            }
            return datos[front];
        }

        boolean isEmpty() {
            return front == -1;
        }

        boolean isFull() {
            return rear == capacidad - 1;
        }

        int size() {
            if (isEmpty()) {
                return 0;
            }
            return rear - front + 1;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 6 - COLA SIMPLE DE ENTEROS ==========");

        ColaEnteros cola = new ColaEnteros(5);
        System.out.println("Cola creada con capacidad 5. front = -1, rear = -1");
        System.out.println("isEmpty: " + cola.isEmpty() + " | size: " + cola.size());

        // Encolar elementos
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        System.out.println();
        System.out.println("Tras enqueue(10), enqueue(20), enqueue(30):");
        System.out.println("   front = " + cola.front() + " | rear dentro = 2 | size: " + cola.size());

        // Desencolar (FIFO: primero entra, primero sale)
        int salido = cola.dequeue();
        System.out.println();
        System.out.println("dequeue() -> " + salido + " (sale el primero que entró, FIFO)");
        System.out.println("   front() ahora = " + cola.front() + " | size: " + cola.size());

        // Llenar la cola
        cola.enqueue(40);
        cola.enqueue(50);
        System.out.println();
        System.out.println("Tras enqueue(40), enqueue(50): isFull = " + cola.isFull());
        try {
            cola.enqueue(60);
        } catch (IllegalStateException e) {
            System.out.println("enqueue(60) -> Error: " + e.getMessage());
        }

        // Vaciar la cola
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        System.out.println();
        System.out.println("Tras vaciar: isEmpty = " + cola.isEmpty());
        try {
            cola.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("dequeue() -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("PARA QUÉ SIRVEN front Y rear:");
        System.out.println("- front señala al que debe salir (FIFO); rear señala dónde entra.");
        System.out.println("- enqueue: ++rear; dequeue: ++front.");
        System.out.println("- Si front > rear, la cola está vacía. Si rear == capacidad-1, está llena.");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// El prompt original se mantuvo en esencia. Se decidió reiniciar front y rear a -1
// cuando la cola queda vacía (front == rear tras un dequeue), para simplificar
// isEmpty() y evitar confusiones, detalle que no altera el comportamiento FIFO.
// ---------------------------------------------------------------------------