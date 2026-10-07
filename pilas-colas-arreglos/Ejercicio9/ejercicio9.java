/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una cola SIMPLE con arreglo y demostrar el problema del
 * desperdicio de posiciones.
 *
 * Estructura necesaria: una cola simple igual a la del Ejercicio 6, con índices front y
 * rear que avanzan de forma creciente y NUNCA vuelven atrás.
 *
 * Operaciones: enqueue (rear++), dequeue (front++), y un método para pintar el estado
 * del arreglo interno.
 *
 * El programa debe mostrar el arreglo luego de varias operaciones enqueue y dequeue, y
 * dejar bien visible cómo quedan espacios libres al INICIO del arreglo que, en una cola
 * simple, NO pueden reutilizarse:
 *   - Cuando rear llega al final (capacidad - 1), aunque el arreglo esté casi vacío por
 *     delante (espacios libres antes de front), la cola se considera llena.
 *
 * Este desperdicio es la motivación para la cola circular (Ejercicio 10), que reutiliza
 * esos espacios con el operador módulo %.
 */

/**
 * Ejercicio 9 - Problema de desperdicio de espacio en cola simple.
 *
 * <p>Demuestra con el arreglo interno visible que una cola simple desperdicia espacio:
 * los índices front y rear solo avanzan, por lo que las posiciones liberadas al inicio
 * del arreglo quedan inutilizables aun cuando la cola esté casi vacía.</p>
 *
 * <p>Complejidad: O(1) por operación; el problema es el uso del espacio.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio9 {

    /**
     * Cola simple con visualización del arreglo interno.
     */
    static class ColaSimple {
        private int[] datos;
        private int capacidad;
        private int front;
        private int rear;

        ColaSimple(int capacidad) {
            this.capacidad = capacidad;
            this.datos = new int[capacidad];
            this.front = -1;
            this.rear = -1;
        }

        void enqueue(int valor) {
            if (rear == capacidad - 1) {
                throw new IllegalStateException("Cola 'llena' (aunque haya huecos al inicio).");
            }
            if (rear == -1) {
                front = 0;
            }
            datos[++rear] = valor;
        }

        int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Cola vacía.");
            }
            int valor = datos[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            return valor;
        }

        boolean isEmpty() {
            return front == -1;
        }

        boolean isFull() {
            return rear == capacidad - 1;
        }

        /** Imprime el arreglo interno marcando los huecos libres. */
        void mostrarArreglo(String etiqueta) {
            System.out.println("   " + etiqueta);
            System.out.print("   índice:  ");
            for (int i = 0; i < capacidad; i++) {
                System.out.printf("%2d ", i);
            }
            System.out.println();
            System.out.print("   valor:   ");
            for (int i = 0; i < capacidad; i++) {
                if (datos[i] == 0 && (i < front || i > rear || front == -1)) {
                    System.out.print(" _ ");
                } else {
                    System.out.printf("%2d ", datos[i]);
                }
            }
            System.out.println();
            System.out.println("   front = " + front + " | rear = " + rear
                    + " | elementos = " + size());
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
        System.out.println("========== EJERCICIO 9 - DESPERDICIO DE ESPACIO EN COLA SIMPLE ==========");

        ColaSimple cola = new ColaSimple(5);

        // 1) Se encolan varios elementos: el arreglo se llena hasta rear = capacidad - 1
        System.out.println("1) Encolamos 10, 20, 30, 40, 50:");
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        cola.enqueue(40);
        cola.enqueue(50);
        cola.mostrarArreglo("Estado tras 5 enqueue:");
        System.out.println("   ¿Cola llena? " + cola.isFull());

        // 2) Se desencolan elementos del frente: front avanza y deja huecos al inicio
        System.out.println();
        System.out.println("2) Desencolamos 10, 20 y 30 (front avanza):");
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        cola.mostrarArreglo("Estado tras 3 dequeue:");

        // 3) Intento de enqueue: falla aunque haya 3 espacios libres al inicio
        System.out.println();
        System.out.println("3) Queremos encolar 60 (quedan 3 huecos libres al inicio):");
        try {
            cola.enqueue(60);
        } catch (IllegalStateException e) {
            System.out.println("   enqueue(60) -> Error: " + e.getMessage());
        }
        System.out.println("   ¿Cola llena? " + cola.isFull() + " pero solo hay "
                + cola.size() + " elemento(s).");

        System.out.println();
        System.out.println("CONCLUSIÓN:");
        System.out.println("- Los huecos en las posiciones 0, 1 y 2 quedaron INUTILIZABLES.");
        System.out.println("- En una cola simple front y rear solo crecen; rear ya llegó a");
        System.out.println("  capacidad - 1, por eso la cola se declara llena pese a tener");
        System.out.println("  espacio libre delante.");
        System.out.println("- Una cola circular (Ejercicio 10) reutiliza esos huecos con %.");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// El prompt original se mantuvo en esencia. Se agregó la visualización del arreglo
// interno con índices para que el desperdicio de espacio quede explícito en la
// prueba de ejecución.
// ---------------------------------------------------------------------------