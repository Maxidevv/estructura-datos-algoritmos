import java.util.Scanner;

/**
 * Ejercicio 2 - Búsqueda de un elemento en un vector desordenado.
 *
 * <p>Este programa implementa el algoritmo de búsqueda lineal (búsqueda secuencial)
 * para encontrar un elemento dentro de un arreglo de enteros no ordenado. Se
 * recorre el arreglo elemento por elemento desde el inicio hasta el final,
 * comparando cada elemento con el valor buscado.</p>
 *
 * <p>Estrategia utilizada: Búsqueda Lineal (Sequential Search).</p>
 *
 * <p>Justificación: Dado que el vector no está ordenado, la búsqueda lineal es
 * la estrategia más adecuada. Cualquier otro enfoque como la búsqueda binaria
 * requeriría ordenar el arreglo previamente, lo cual costaría O(n log n) solo
 * para la preparación, haciendo el proceso menos eficiente que una simple
 * búsqueda lineal con O(n).</p>
 *
 * <p>Análisis de complejidad:</p>
 * <ul>
 *   <li>Mejor caso: O(1) - El elemento se encuentra en la primera posición.</li>
 *   <li>Peor caso: O(n) - El elemento no existe o está en la última posición.</li>
 *   <li>Caso promedio: O(n/2) - En promedio se recorre la mitad del arreglo.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio2 {

    /**
     * Busca un elemento en un arreglo desordenado mediante búsqueda lineal.
     *
     * <p>Recorre el arreglo desde la primera hasta la última posición, comparando
     * cada elemento con el valor objetivo. Si encuentra una coincidencia, retorna
     * el índice de esa posición. Si termina el recorrido sin encontrar el
     * elemento, retorna -1.</p>
     *
     * @param array  el arreglo de enteros en el que se realizará la búsqueda
     * @param target el elemento que se desea encontrar
     * @return el índice del elemento si se encuentra, o -1 si no existe en el arreglo
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static int linearSearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara un vector de ejemplo, solicita al usuario el elemento a buscar,
     * ejecuta la búsqueda lineal e imprime el resultado incluyendo la cantidad
     * de posiciones recorridas. También imprime el análisis de complejidad
     * algorítmica correspondiente a la estrategia utilizada.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] vector = {3, 7, 1, 9, 4, 6, 2, 8, 5};
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vector: [");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i < vector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.print("Ingrese el elemento a buscar: ");
        int objetivo = scanner.nextInt();

        System.out.println();
        System.out.println("Estrategia utilizada: Búsqueda Lineal (Sequential Search).");
        System.out.println("Justificación: El vector no está ordenado, por lo que la búsqueda");
        System.out.println("lineal es la estrategia más eficiente. Ordenar el vector costaría");
        System.out.println("O(n log n), lo cual es menos eficiente que la búsqueda lineal con O(n).");

        int posicionesRecorridas = 0;
        int resultado = -1;

        for (int i = 0; i < vector.length; i++) {
            posicionesRecorridas++;
            if (vector[i] == objetivo) {
                resultado = i;
                break;
            }
        }

        System.out.println();
        if (resultado != -1) {
            System.out.println("Elemento " + objetivo + " encontrado en la posición: " + resultado);
        } else {
            System.out.println("Elemento " + objetivo + " no encontrado en el vector.");
        }
        System.out.println("Posiciones recorridas: " + posicionesRecorridas);

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Mejor caso: O(1) - El elemento se encuentra en la primera posición.");
        System.out.println("- Peor caso: O(n) - El elemento no existe o está en la última posición.");
        System.out.println("- Caso promedio: O(n/2) - En promedio se recorre la mitad del arreglo.");
        System.out.println("- Espacial: O(1) - Solo se utilizan variables adicionales.");

        scanner.close();
    }
}
