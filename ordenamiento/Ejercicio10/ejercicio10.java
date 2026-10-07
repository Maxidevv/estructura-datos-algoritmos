/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un programa que reciba un arreglo de jugadores con nombre
 * y puntaje, y ordene el ranking de mayor a menor puntaje.
 *
 * El algoritmo que considero más adecuado entre los vistos es Selection Sort.
 *
 * Justificación de la elección:
 *   - El volumen de jugadores de un ranking es habitualmente pequeño (decenas o cientos).
 *     Para ese tamaño, los algoritmos O(n^2) son perfectamente aceptables y más simples
 *     de razonar que los avanzados.
 *   - Selection Sort es especialmente conveniente porque en cada pasada busca el máximo
 *     de la zona desordenada y lo coloca de una vez en la posición final. Eso equivale a
 *     "elegir al siguiente del podio" (el de mayor puntaje aún sin ubicar), una operación
 *     conceptualmente natural para construir un ranking de mayor a menor.
 *   - Además realiza la cantidad mínima de intercambios (a lo sumo n - 1), lo que importa
 *     si los jugadores fueran objetos grandes. No derrocha intercambios como Bubble Sort.
 *
 * Datos de entrada: un arreglo de jugadores hardcodeado, por ejemplo:
 *   Jugador("Ana", 1200), Jugador("Pedro", 900), Jugador("Lucia", 1500).
 *
 * Debe mostrar por pantalla el ranking final de mayor a menor puntaje.
 *
 * El código debe incluir comentarios que justifiquen la elección del algoritmo.
 *
 * Para verificar, se imprime el ranking resultante y se comprueba que los puntajes
 * aparecen en orden descendente.
 */

/**
 * Ejercicio 10 - Sistema de ranking de puntajes.
 *
 * <p>Ordena un arreglo de jugadores por puntaje de mayor a menor utilizando Selection Sort,
 * y justifica la elección del algoritmo para este problema concreto.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n^2) - apto para listas pequeñas de jugadores.</li>
 *   <li>Espacial: O(1) - ordenamiento in-place.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio10 {

    /**
     * Representa un jugador con nombre y puntaje.
     */
    static class Jugador {
        String nombre;
        int puntaje;

        Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        @Override
        public String toString() {
            return nombre + " (" + puntaje + ")";
        }
    }

    /**
     * Ordena un arreglo de jugadores por puntaje de MAYOR a MENOR con Selection Sort.
     *
     * <p>En cada pasada se busca el jugador con el mayor puntaje de la zona desordenada y
     * se lo coloca en la siguiente posición del ranking (simula ir "eligiendo" al siguiente
     * del podio). Solo se intercambia cuando el máximo no está ya en su posición.</p>
     *
     * @param jugadores el arreglo de jugadores a ordenar
     */
    static void ordenarRanking(Jugador[] jugadores) {
        int n = jugadores.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMayor = i;

            // Se busca el puntaje máximo de la zona aún sin ordenar.
            for (int j = i + 1; j < n; j++) {
                if (jugadores[j].puntaje > jugadores[indiceMayor].puntaje) {
                    indiceMayor = j;
                }
            }

            // Un solo intercambio coloca al mayor en su posición final del ranking.
            if (indiceMayor != i) {
                Jugador aux = jugadores[i];
                jugadores[i] = jugadores[indiceMayor];
                jugadores[indiceMayor] = aux;
            }
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 10 - SISTEMA DE RANKING DE PUNTAJES ==========");

        Jugador[] jugadores = {
                new Jugador("Ana", 1200),
                new Jugador("Pedro", 900),
                new Jugador("Lucia", 1500),
                new Jugador("Carlos", 1050)
        };

        System.out.println("Jugadores sin ordenar:");
        for (Jugador j : jugadores) {
            System.out.println("   " + j);
        }

        ordenarRanking(jugadores);

        System.out.println();
        System.out.println("Ranking de mayor a menor puntaje:");
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println("   " + (i + 1) + ". " + jugadores[i]);
        }

        System.out.println();
        System.out.println("¿POR QUÉ SELECTION SORT?");
        System.out.println("- El ranking suele tener pocos jugadores, así que O(n^2) es suficiente.");
        System.out.println("- Cada pasada elige al de mayor puntaje sin ubicar, igual que");
        System.out.println("  asignar el puesto 1°, luego 2°, etc. Es natural para el problema.");
        System.out.println("- Hace a lo sumo n - 1 intercambios: no desperdicia movimientos");
        System.out.println("  como Bubble Sort, y es más simple que Insertion/avanzados.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// Se agregó un cuarto jugador ("Carlos", 1050) a los tres provistos por la consigna
// para que el ranking de mayor a menor no coincida con el orden de entrada y quede
// demostrado que el intercambio realmente ocurre. El algoritmo y la justificación
// no cambiaron.
// ---------------------------------------------------------------------------