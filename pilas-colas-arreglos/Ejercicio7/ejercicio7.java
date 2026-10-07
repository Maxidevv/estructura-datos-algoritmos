/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un sistema de turnos para una oficina.
 *
 * Estructura necesaria: una cola (queue) de Strings que representan turnos o nombres.
 *
 * Operaciones que debe tener:
 *   - agregarPersona(persona): la persona llega, recibe un turno y se agrega al final
 *     de la cola.
 *   - atenderSiguiente(): atiende y retira a la persona que está primero en la cola.
 *   - consultarPrimero(): consulta quién está primero sin retirarlo.
 *
 * Por qué este problema utiliza FIFO y NO LIFO:
 *   - En una oficina de atención al público, la primera persona que llega es la primera
 *     que se atiende: First In, First Out (FIFO).
 *   - Si se usara una pila (LIFO), la última persona en llegar sería la primera en ser
 *     atendida, lo cual es injusto y no refleja el comportamiento real de una cola de
 *     personas (sistema de turnos).
 *
 * Caso límite: si no hay nadie esperando, atender o consultar debe informar que la cola
 * está vacía.
 */

/**
 * Ejercicio 7 - Sistema de turnos para atención.
 *
 * <p>Simula un sistema de turnos de una oficina con una cola (FIFO). Justifica por qué
 * este problema usa FIFO y no LIFO: la primera persona que llega es la primera atendida.</p>
 *
 * <p>Complejidad: todas las operaciones son O(1).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio7 {

    /**
     * Cola simple de Strings que almacena a las personas esperando su turno.
     */
    static class ColaTurnos {
        private String[] personas;
        private int capacidad;
        private int front;
        private int rear;

        ColaTurnos(int capacidad) {
            this.capacidad = capacidad;
            this.personas = new String[capacidad];
            this.front = -1;
            this.rear = -1;
        }

        /** Una persona llega y se agrega al final de la cola. */
        void agregarPersona(String persona) {
            if (rear == capacidad - 1) {
                throw new IllegalStateException("La cola de espera está llena.");
            }
            if (rear == -1) {
                front = 0;
            }
            personas[++rear] = persona;
        }

        /** Atiende y retira a la persona que está primero. */
        String atenderSiguiente() {
            if (isEmpty()) {
                throw new IllegalStateException("No hay personas en espera.");
            }
            String persona = personas[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            return persona;
        }

        /** Consulta quién está primero sin retirarlo. */
        String consultarPrimero() {
            if (isEmpty()) {
                throw new IllegalStateException("No hay personas en espera.");
            }
            return personas[front];
        }

        boolean isEmpty() {
            return front == -1;
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
        System.out.println("========== EJERCICIO 7 - SISTEMA DE TURNOS PARA ATENCIÓN ==========");

        ColaTurnos cola = new ColaTurnos(5);

        // Van llegando personas y se les asigna una posición en la cola
        cola.agregarPersona("Ana");
        cola.agregarPersona("Pedro");
        cola.agregarPersona("Lucía");
        System.out.println("Llegan Ana, Pedro y Lucía. Esperando: " + cola.size());
        System.out.println("Quién está primero: " + cola.consultarPrimero());

        // Se atiende a quienes esperan (en orden de llegada, FIFO)
        System.out.println();
        System.out.println("Se atiende -> " + cola.atenderSiguiente());
        System.out.println("Se atiende -> " + cola.atenderSiguiente());
        System.out.println("Quién está primero ahora: " + cola.consultarPrimero());

        // Caso límite: nadie esperando
        System.out.println();
        System.out.println("Se atiende -> " + cola.atenderSiguiente());
        System.out.println("Esperando: " + cola.size() + " | cola vacía: " + cola.isEmpty());
        try {
            cola.atenderSiguiente();
        } catch (IllegalStateException e) {
            System.out.println("atenderSiguiente() -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("¿POR QUÉ FIFO Y NO LIFO?");
        System.out.println("- La primera persona que llega es la primera en atenderse (FIFO).");
        System.out.println("- Con una pila (LIFO) la última en llegar sería la primera en salir,");
        System.out.println("  que no es el orden justo de un sistema de turnos.");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// No fue necesario modificar el prompt. La cola es una reimplementación de la del
// Ejercicio 6 adaptada a Strings para representar personas/turnos.
// ---------------------------------------------------------------------------