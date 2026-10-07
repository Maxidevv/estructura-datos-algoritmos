/*
Prompt inicial utilizado:
Quiero implementar un sistema simple de HISTORIAL DE NAVEGACIÓN usando una pila enlazada.

El programa debe permitir:
  - visitar una página (agregarla al historial);
  - volver a la página anterior (retroceder);
  - consultar la página actual;
  - imprimir el historial.

Por qué una pila representa correctamente el comportamiento "volver atrás":
Al visitar páginas, cada nueva página pasa a ser la actual y la anterior queda "debajo".
Volver atrás debe devolver exactamente la última página visitada, es decir, la más
reciente. Eso es LIFO: el último en entrar es el primero en salir. Por eso el tope de la
pila es siempre la página actual, y volver atrás es un pop que expone la página previa.

Estructura: pila enlazada de strings (URLs/nombres de página), head = página actual.

Operaciones: visitar(String url), volver() (retrocede y devuelve la nueva actual),
paginaActual(), imprimirHistorial(). Complejidad: visitar O(1), volver O(1),
paginaActual O(1), imprimir O(n).

Casos especiales: volver cuando no hay página anterior (historial vacío o una sola página);
consultar la actual sin páginas; visitar la primera página.

Prueba de ejecución: visitar varias páginas, consultar la actual, volver atrás un par de
veces y forzar el caso "no hay página anterior".

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión permitía un volver() sobre el vacío sin avisar. Se agregó el manejo del
caso sin página anterior informando el error y conservando la página actual.
*/

/**
 * Ejercicio 5 - Historial de navegación con pila.
 *
 * <p>Usa una pila enlazada donde el tope es la página actual; volver atrás es un pop
 * (LIFO).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio5 {

    /** Nodo de la pila. */
    static class Nodo {
        String pagina;
        Nodo siguiente;

        Nodo(String pagina) {
            this.pagina = pagina;
        }
    }

    /** Historial de navegación basado en pila. */
    static class Historial {
        private Nodo tope; // página actual
        private int size;

        /** Visita una página: pasa a ser la actual. O(1). */
        void visitar(String url) {
            Nodo nuevo = new Nodo(url);
            nuevo.siguiente = tope;
            tope = nuevo;
            size++;
            System.out.println("Visitando: " + url);
        }

        /** Vuelve a la página anterior. Devuelve la nueva actual. O(1). */
        String volver() {
            if (tope == null) {
                System.out.println("No hay historial para volver.");
                return null;
            }
            if (tope.siguiente == null) {
                System.out.println("No hay página anterior (estás en la primera).");
                return tope.pagina;
            }
            tope = tope.siguiente;
            size--;
            return tope.pagina;
        }

        /** Página actual (tope). O(1). */
        String paginaActual() {
            return tope == null ? "(sin páginas)" : tope.pagina;
        }

        /** Imprime el historial de la más reciente a la más antigua. O(n). */
        void imprimirHistorial() {
            System.out.print("Historial (actual -> antiguas): ");
            Nodo actual = tope;
            if (actual == null) {
                System.out.print("vacío");
            }
            while (actual != null) {
                System.out.print(actual.pagina);
                if (actual.siguiente != null) {
                    System.out.print(" <- ");
                }
                actual = actual.siguiente;
            }
            System.out.println();
        }

        int getSize() {
            return size;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 5 - HISTORIAL DE NAVEGACIÓN ==========");

        Historial h = new Historial();
        System.out.println("Página actual al inicio: " + h.paginaActual());

        h.visitar("google.com");
        h.visitar("youtube.com");
        h.visitar("github.com");
        h.imprimirHistorial();
        System.out.println("Página actual: " + h.paginaActual());

        System.out.println("Volver -> " + h.volver());
        System.out.println("Página actual: " + h.paginaActual());
        System.out.println("Volver -> " + h.volver());
        System.out.println("Página actual: " + h.paginaActual());

        System.out.println("Volver -> " + h.volver() + " (primera página, no retrocede)");
        System.out.println("size: " + h.getSize());

        System.out.println();
        System.out.println("LIFO: la última página visitada es la primera en salir al volver atrás.");
    }
}