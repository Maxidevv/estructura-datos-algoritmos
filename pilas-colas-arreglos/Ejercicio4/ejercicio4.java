/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un simulador simple de historial de navegación.
 *
 * Estructura necesaria: una pila de URLs.
 *
 * Cómo funciona:
 *   - Cada vez que el usuario visita una página, se apila (push) la URL visitada.
 *   - El programa debe permitir "volver" a la página anterior usando pop, que retira la
 *     URL actual y queda en la anterior.
 *   - También debe poder consultarse la página actual (el tope de la pila) sin retirarla
 *     (peek).
 *
 * Por qué el historial funciona bajo el principio LIFO:
 *   - El botón "volver" de un navegador siempre regresa a la última página visitada.
 *     Es decir, la página que se vio más recientemente es la primera en salir del
 *     historial: Last In, First Out. Eso es exactamente el comportamiento de una pila.
 *   - Una cola (FIFO) volvería a la primera página visitada de la sesión, lo que no
 *     refleja el comportamiento real de los navegadores.
 *
 * Caso límite: si no queda página anterior (la pila tiene un solo elemento o está vacía),
 * se debe informar que no se puede volver atrás.
 */

/**
 * Ejercicio 4 - Historial de navegación.
 *
 * <p>Simula el historial de navegación de un navegador con una pila de URLs. Justifica
 * por qué funciona con el principio LIFO (Last In, First Out): el botón "volver" regresa
 * a la última página visitada.</p>
 *
 * <p>Complejidad: todas las operaciones son O(1).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio4 {

    /**
     * Historial de navegación implementado como pila de URLs.
     */
    static class Historial {
        private String[] urls;
        private int top; // índice de la página actual; -1 si el historial está vacío

        Historial(int capacidad) {
            urls = new String[capacidad];
            top = -1;
        }

        /** Apila la URL recién visitada (nueva página actual). */
        void visitar(String url) {
            if (top == urls.length - 1) {
                throw new IllegalStateException("Historial lleno; no se puede visitar más.");
            }
            urls[++top] = url;
        }

        /** Retrocede a la página anterior usando pop sobre la página actual. */
        String volver() {
            if (top <= 0) {
                throw new IllegalStateException("No hay página anterior.");
            }
            top--; // se "saca" la página actual y queda la anterior arriba
            return urls[top];
        }

        /** Consulta la página actual (tope) sin retirarla. */
        String paginaActual() {
            if (top == -1) {
                throw new IllegalStateException("El historial está vacío.");
            }
            return urls[top];
        }

        /** @return true si el historial está vacío. */
        boolean estaVacio() {
            return top == -1;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 4 - HISTORIAL DE NAVEGACIÓN ==========");

        Historial historial = new Historial(10);

        // El usuario visita varias páginas (se apilan)
        historial.visitar("https://inicio.com");
        historial.visitar("https://inicio.com/productos");
        historial.visitar("https://inicio.com/productos/celular");

        System.out.print("Historial de visitas: [");
        for (int i = 0; i <= historial.top; i++) {
            System.out.print(historial.urls[i]);
            if (i < historial.top) {
                System.out.print(" -> ");
            }
        }
        System.out.println("]");
        System.out.println("Página actual: " + historial.paginaActual());

        // El usuario presiona "volver" varias veces (pop)
        System.out.println();
        System.out.println("Presionando 'volver'...");
        System.out.println("   volver() -> " + historial.volver());
        System.out.println("   Página actual: " + historial.paginaActual());
        System.out.println("   volver() -> " + historial.volver());
        System.out.println("   Página actual: " + historial.paginaActual());

        // Caso límite: no hay más páginas anteriores
        System.out.println();
        try {
            historial.volver();
            historial.volver();
        } catch (IllegalStateException e) {
            System.out.println("volver() -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("¿POR QUÉ EL HISTORIAL ES LIFO?");
        System.out.println("- El botón 'volver' regresa a la última página visitada.");
        System.out.println("- La más reciente entra al tope y es la primera en salir (LIFO).");
        System.out.println("- Una cola (FIFO) volvería a la primera página de la sesión,");
        System.out.println("  que no es lo que hace un navegador real.");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// No fue necesario modificar el prompt. Se expuso el arreglo interno con un acceso
// orientado a la demostración de la secuencia de URLs, manteniendo el resto del
// diseño como una pila con push/pop.
// ---------------------------------------------------------------------------