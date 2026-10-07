/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un programa que simule una torre de platos.
 *
 * Estructura necesaria: una pila (stack), porque los platos se apilan uno encima de otro.
 * Cada plato puede representarse con un número o un nombre (usaré Strings con el nombre
 * o número del plato).
 *
 * Operaciones que debe tener:
 *   - agregarPlato(plato): coloca un plato en la parte superior de la torre.
 *   - retirarPlatoSuperior(): retira el plato que está arriba de todo y lo devuelve.
 *   - consultarPlatoSuperior(): muestra cuál es el plato que está arriba sin retirarlo.
 *
 * Por qué se resuelve con una PILA y no con una COLA:
 *   - En una torre de platos, el último plato colocado es el primero que se puede retirar:
 *     solo se accede por arriba. Eso es exactamente LIFO (Last In, First Out).
 *   - Una cola (FIFO) retiraría el primer plato que se puso (el de abajo), lo cual es
 *     imposible en la vida real: no se puede sacar un plato del medio de la torre.
 *
 * Caso límite: si la torre está vacía, retirar o consultar el plato superior debe informar
 * que no hay platos.
 */

/**
 * Ejercicio 2 - Simulador de torre de platos.
 *
 * <p>Simula una torre de platos con una pila (LIFO): los platos se agregan arriba y solo
 * se puede operar sobre el superior. Justifica por qué este problema usa una pila y no
 * una cola.</p>
 *
 * <p>Complejidad: todas las operaciones son O(1).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio2 {

    /**
     * Torre de platos implementada como pila.
     */
    static class TorreDePlatos {
        private String[] platos;
        private int top; // índice del plato superior; -1 si la torre está vacía

        /** Construye una torre vacía con capacidad fija. */
        TorreDePlatos(int capacidad) {
            platos = new String[capacidad];
            top = -1;
        }

        /** Coloca un plato en la parte superior de la torre. */
        void agregarPlato(String plato) {
            if (top == platos.length - 1) {
                throw new IllegalStateException("La torre está llena, no caben más platos.");
            }
            platos[++top] = plato;
        }

        /** Retira y devuelve el plato de arriba de todo. */
        String retirarPlatoSuperior() {
            if (top == -1) {
                throw new IllegalStateException("La torre está vacía, no hay qué retirar.");
            }
            return platos[top--];
        }

        /** Consulta el plato superior sin retirarlo. */
        String consultarPlatoSuperior() {
            if (top == -1) {
                throw new IllegalStateException("La torre está vacía.");
            }
            return platos[top];
        }

        /** @return true si la torre no tiene platos. */
        boolean estaVacia() {
            return top == -1;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 2 - SIMULADOR DE TORRE DE PLATOS ==========");

        TorreDePlatos torre = new TorreDePlatos(5);

        // Se apilan varios platos
        torre.agregarPlato("Plato 1 (de abajo)");
        torre.agregarPlato("Plato 2");
        torre.agregarPlato("Plato 3");

        System.out.print("Torre inicial: [");
        System.out.print(torre.platos[0] + ", " + torre.platos[1] + ", " + torre.platos[2]);
        System.out.println("]");
        System.out.println("Plato superior: " + torre.consultarPlatoSuperior());

        // Se retira el plato superior (LIFO: primero sale el último que entró)
        System.out.println();
        System.out.println("Se retira el plato superior -> " + torre.retirarPlatoSuperior());
        System.out.println("Nuevo plato superior: " + torre.consultarPlatoSuperior());

        // Caso límite: vaciar la torre
        System.out.println();
        torre.retirarPlatoSuperior();
        torre.retirarPlatoSuperior();
        System.out.println("Tras retirar los platos restantes, ¿torre vacía? " + torre.estaVacia());
        try {
            torre.retirarPlatoSuperior();
        } catch (IllegalStateException e) {
            System.out.println("retirarPlatoSuperior() -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("¿POR QUÉ UNA PILA Y NO UNA COLA?");
        System.out.println("- Porque el último plato colocado es el primero que se retira (LIFO).");
        System.out.println("- Una cola (FIFO) atendería primero al plato de abajo de la torre,");
        System.out.println("  lo que no ocurre con una torre de platos real.");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// El prompt original se mantuvo en esencia. Se agregó el caso de torre llena
// (capacidad fija) para completar los casos límite que no estaban especificados.
// ---------------------------------------------------------------------------