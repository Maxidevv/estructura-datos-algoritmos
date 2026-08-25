import java.util.Scanner;

/**
 * Ejercicio 3 - Búsqueda binaria en un vector ordenado.
 *
 * <p>Este programa implementa el algoritmo de búsqueda binaria para encontrar
 * un elemento dentro de un arreglo de enteros ordenado de forma ascendente.
 * En cada iteración se divide el espacio de búsqueda a la mitad, comparando
 * el elemento central con el valor objetivo para determinar en qué mitad
 * continuar la búsqueda.</p>
 *
 * <p>Por qué la búsqueda binaria es más eficiente que la búsqueda lineal
 * cuando los datos están ordenados:</p>
 * <ul>
 *   <li>La búsqueda lineal recorre el arreglo elemento por elemento, realizando
 *       hasta n comparaciones en el peor caso.</li>
 *   <li>La búsqueda binaria aprovecha el ordenamiento para descartar la mitad
 *       del espacio de búsqueda en cada paso, reduciendo exponencialmente las
 *       comparaciones necesarias.</li>
 *   <li>Para un arreglo de 1000 elementos, la búsqueda lineal puede requerir
 *       hasta 1000 comparaciones, mientras que la búsqueda binaria solo
 *       necesita aproximadamente 10.</li>
 * </ul>
 *
 * <p>Análisis de complejidad:</p>
 * <ul>
 *   <li>Mejor caso: O(1) - El elemento se encuentra en la posición central
 *       en la primera iteración.</li>
 *   <li>Peor caso: O(log n) - Se deben dividir el rango hasta que el espacio
 *       de búsqueda se agote.</li>
 *   <li>Caso promedio: O(log n) - En promedio se realizan log₂(n) comparaciones.</li>
 *   <li>Espacial: O(1) - Solo se utilizan variables adicionales (low, high, mid).</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio3 {

    /**
     * Busca un elemento en un arreglo ordenado mediante búsqueda binaria.
     *
     * <p>El algoritmo inicializa dos punteros: low al inicio del arreglo y
     * high al final. En cada iteración calcula el punto medio (mid) y compara
     * el elemento en esa posición con el objetivo. Si son iguales, retorna el
     * índice. Si el objetivo es menor, se busca en la mitad izquierda; si es
     * mayor, en la mitad derecha. El proceso continúa hasta encontrar el
     * elemento o que low supere a high.</p>
     *
     * <p>Cada paso se imprime en consola mostrando la evolución de las variables
     * low, high y mid para fines didácticos.</p>
     *
     * @param array  el arreglo de enteros ordenado de forma ascendente
     * @param target el elemento que se desea encontrar
     * @return el índice del elemento si se encuentra, o -1 si no existe en el arreglo
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        int low = 0;
        int high = array.length - 1;
        int iteracion = 1;

        System.out.println("Iniciando búsqueda binaria del elemento: " + target);
        System.out.println("Tamaño del arreglo: " + array.length);
        System.out.println();

        while (low <= high) {
            int mid = low + (high - low) / 2;

            System.out.println("--- Iteración " + iteracion + " ---");
            System.out.println("  low = " + low + ", high = " + high + ", mid = " + mid);
            System.out.println("  Elemento en mid: array[" + mid + "] = " + array[mid]);

            if (array[mid] == target) {
                System.out.println("  Comparación: array[" + mid + "] (" + array[mid] + ") == " + target + " → ¡Elemento encontrado!");
                System.out.println();
                return mid;
            } else if (array[mid] < target) {
                System.out.println("  Comparación: array[" + mid + "] (" + array[mid] + ") < " + target + " → Buscar en la mitad derecha.");
                low = mid + 1;
            } else {
                System.out.println("  Comparación: array[" + mid + "] (" + array[mid] + ") > " + target + " → Buscar en la mitad izquierda.");
                high = mid - 1;
            }

            System.out.println("  Nuevo rango: [" + low + ", " + high + "]");
            System.out.println();
            iteracion++;
        }

        System.out.println("Se agotó el espacio de búsqueda. Elemento no encontrado.");
        return -1;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara un vector de ejemplo ordenado de forma ascendente, solicita al
     * usuario el elemento a buscar mediante la entrada estándar, ejecuta la
     * búsqueda binaria mostrando paso a paso la evolución de las variables, e
     * imprime el resultado junto con el análisis de complejidad algorítmica.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] vector = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
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
        System.out.println("Estrategia utilizada: Búsqueda Binaria (Binary Search).");
        System.out.println("Justificación: El vector está ordenado, por lo que la búsqueda");
        System.out.println("binaria es significativamente más eficiente que la búsqueda lineal.");
        System.out.println("En cada paso se descarta la mitad del espacio de búsqueda,");
        System.out.println("reduciendo las comparaciones de O(n) a O(log n).");
        System.out.println();

        int resultado = binarySearch(vector, objetivo);

        if (resultado != -1) {
            System.out.println("Elemento " + objetivo + " encontrado en la posición: " + resultado);
        } else {
            System.out.println("Elemento " + objetivo + " no encontrado en el vector.");
        }

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Mejor caso: O(1) - El elemento se encuentra en la posición central en la primera iteración.");
        System.out.println("- Peor caso: O(log n) - Se deben dividir el rango hasta que el espacio de búsqueda se agote.");
        System.out.println("- Caso promedio: O(log n) - En promedio se realizan log₂(n) comparaciones.");
        System.out.println("- Espacial: O(1) - Solo se utilizan variables adicionales (low, high, mid).");

        scanner.close();
    }
}
