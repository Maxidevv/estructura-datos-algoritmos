/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una cola circular utilizando un arreglo, que reutilice los
 * espacios liberados al inicio del arreglo.
 *
 * Diferencias con la cola simple:
 *   - Se usa el operador módulo % para mover front y rear, de modo que al llegar al final
 *     del arreglo los índices "vuelvan a envolverse" hacia el inicio:
 *       - enqueue: rear = (rear + 1) % capacidad
 *       - dequeue: front = (front + 1) % capacidad
 *   - Como front y rear dan vueltas, ya no se puede usar (front == rear) para saber si
 *     está vacía o llena (ambos casos coinciden). Se agrega un contador "size" que
 *     distingue: size == 0 es vacía, size == capacidad es llena.
 *
 * Operaciones que debe tener: enqueue, dequeue, front, isEmpty, isFull y size.
 *
 * Debe demostrarse con una prueba que, a diferencia de la cola simple (Ejercicio 9), los
 * espacios liberados al inicio se vuelven a usar: encolar, desencolar varias veces y
 * volver a encolar para ver el arreglo completo.
 *
 * Caso límite: desencolar sobre cola vacía y encolar sobre cola llena lanzan error.
 */

/**
 * Ejercicio 10 - Cola circular.
 *
 * <p>Implementa una cola circular con arreglo, reutilizando los espacios liberados al
 * inicio gracias al operador módulo %. Un contador size distingue entre cola llena y
 * vacía, dado que en una cola circular front == rear es ambiguo.</p>
 *
 * <p>Complejidad: O(1) por operación.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio10 {

    /**
     * Cola circular de enteros con arreglo.
     *
     * <p>front apunta al primer elemento y rear al último. Ambos avanzan con módulo %,
     * envolviéndose al llegar al final del arreglo.</p>
     */
    static class ColaCircular {
        private int[] datos;
        private int capacidad;
        private int front;
        private int rear;
        private int size; // distingue vacía (0) de llena (capacidad)

        ColaCircular(int capacidad) {
            this.capacidad = capacidad;
            this.datos = new int[capacidad];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        /** Agrega un elemento al final; rear avanza con módulo %. */
        void enqueue(int valor) {
            if (isFull()) {
                throw new IllegalStateException("Cola circular llena: no se puede encolar " + valor);
            }
            rear = (rear + 1) % capacidad; // si llega al final, "da la vuelta" al inicio
            datos[rear] = valor;
            size++;
        }

        /** Retira el primer elemento; front avanza con módulo %. */
        int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Cola circular vacía.");
            }
            int valor = datos[front];
            front = (front + 1) % capacidad; // el hueco que deja se podrá reutilizar
            size--;
            return valor;
        }

        /** Consulta el primer elemento sin retirarlo. */
        int front() {
            if (isEmpty()) {
                throw new IllegalStateException("Cola circular vacía.");
            }
            return datos[front];
        }

        boolean isEmpty() {
            return size == 0;
        }

        boolean isFull() {
            return size == capacidad;
        }

        int size() {
            return size;
        }

        /** Imprime el arreglo interno mostrando los elementos activos. */
        void mostrarArreglo(String etiqueta) {
            System.out.println("   " + etiqueta);
            System.out.print("   índice: ");
            for (int i = 0; i < capacidad; i++) {
                System.out.printf("%2d ", i);
            }
            System.out.println();
            System.out.print("   valor:  ");
            for (int i = 0; i < capacidad; i++) {
                String marca = " _ ";
                // Un dato está activo si está dentro del rango circular [front..rear]
                if (size > 0) {
                    if (rear >= front) {
                        if (i >= front && i <= rear) {
                            marca = String.format("%2d ", datos[i]);
                        }
                    } else {
                        if (i >= front || i <= rear) {
                            marca = String.format("%2d ", datos[i]);
                        }
                    }
                }
                System.out.print(marca);
            }
            System.out.println();
            System.out.println("   front = " + front + " | rear = " + rear
                    + " | size = " + size);
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 10 - COLA CIRCULAR ==========");

        ColaCircular cola = new ColaCircular(5);

        // 1) Llenamos la cola
        System.out.println("1) Encolar 10, 20, 30, 40, 50:");
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        cola.enqueue(40);
        cola.enqueue(50);
        cola.mostrarArreglo("Cola llena:");
        System.out.println("   isFull = " + cola.isFull());

        // 2) Desencolamos y reutilizamos el espacio liberado (a diferencia de la simple)
        System.out.println();
        System.out.println("2) Dequeue 10, 20, 30 (front avanza con módulo):");
        cola.dequeue();
        cola.dequeue();
        cola.dequeue();
        cola.mostrarArreglo("Tras 3 dequeue:");
        System.out.println("   front = " + cola.front());

        // 3) Volvemos a encolar: rear da la vuelta y REUTILIZA los huecos 0, 1 y 2
        System.out.println();
        System.out.println("3) Encolar 60, 70, 80 (rear 'da la vuelta' con %):");
        cola.enqueue(60); // rear vuelve a 0
        cola.enqueue(70); // rear vuelve a 1
        cola.enqueue(80); // rear vuelve a 2
        cola.mostrarArreglo("Cola llena de nuevo reutilizando huecos:");

        // 4) Caso límite: llena no admite más
        System.out.println();
        try {
            cola.enqueue(90);
        } catch (IllegalStateException e) {
            System.out.println("enqueue(90) -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("USO DEL MÓDULO %:");
        System.out.println("- enqueue: rear = (rear + 1) % capacidad.");
        System.out.println("- dequeue: front = (front + 1) % capacidad.");
        System.out.println("- Con size se distingue llena (size == capacidad) de vacía (size == 0).");
        System.out.println("- Los espacios liberados al inicio se reutilizan, solucionando el");
        System.out.println("  desperdicio de espacio de la cola simple (Ejercicio 9).");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// El prompt original se mantuvo en esencia. Se usó el contador size (en lugar de
// reservar una celda o un flag) para distinguir llena de vacía, que es el enfoque
// más directo para una demostración didáctica con módulo %.
// ---------------------------------------------------------------------------