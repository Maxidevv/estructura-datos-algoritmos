import java.util.Arrays;

/**
 * Ejercicio 10 - Algoritmo de ordenamiento Bubble Sort.
 *
 * <p>Este programa implementa el algoritmo de ordenamiento burbuja (Bubble Sort)
 * para ordenar un arreglo de enteros de menor a mayor. El algoritmo compara
 * elementos adyacentes y los intercambia si están en el orden incorrecto,
 * repitiendo el proceso hasta que el arreglo esté completamente ordenado.</p>
 *
 * <p><strong>¿Por qué Bubble Sort es solo para fines didácticos?</strong></p>
 * <ul>
 *   <li>Complejidad temporal O(n²) en todos los casos (mejor, peor, promedio).</li>
 *   <li>Algoritmos como Quick Sort (O(n log n) promedio) o Merge Sort (O(n log n))
 *       son significativamente más eficientes.</li>
 *   <li>Para n=1000, Bubble Sort realiza ~1,000,000 operaciones vs ~10,000 de
 *       un algoritmo O(n log n).</li>
 *   <li>Solo es útil para conjuntos muy pequeños (&lt; 20 elementos) o como
 *       herramienta de aprendizaje.</li>
 * </ul>
 *
 * <p><strong>Funcionamiento del algoritmo:</strong></p>
 * <ul>
 *   <li>El bucle exterior realiza n-1 pasadas sobre el arreglo.</li>
 *   <li>En cada pasada, el bucle interior compara pares de elementos adyacentes.</li>
 *   <li>Si el elemento izquierdo es mayor que el derecho, se intercambian.</li>
 *   <li>Después de cada pasada, el elemento más grande \"burbujea\" hacia su
 *       posición correcta al final del arreglo.</li>
 *   <li>Se contabilizan tanto las comparaciones como los intercambios realizados.</li>
 * </ul>
 *
 * <p><strong>Análisis de complejidad:</strong></p>
 * <ul>
 *   <li>Mejor caso: O(n²) — En la implementación estándar, siempre se realizan
 *       todas las comparaciones sin importar el estado del arreglo.</li>
 *   <li>Peor caso: O(n²) — Arreglo ordenado en orden inverso, se realizan todos
 *       los intercambios posibles.</li>
 *   <li>Caso promedio: O(n²) — Se realizan aproximadamente n²/2 comparaciones
 *       e intercambios.</li>
 *   <li>Espacial: O(1) — Ordenamiento in-place, solo se utiliza una variable
 *       auxiliar para el intercambio.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio10 {

    /**
     * Ordena un arreglo de enteros utilizando el algoritmo Bubble Sort.
     *
     * <p>El algoritmo recorre el arreglo realizando comparaciones entre elementos
     * adyacentes. Si el elemento izquierdo es mayor que el derecho, se intercambian
     * usando una variable temporal. Este proceso se repite hasta completar todas
     * las pasadas necesarias.</p>
     *
     * <p>Cada paso se imprime en consola mostrando la pasada actual, las
     * comparaciones realizadas y los intercambios efectuados. Al finalizar,
     * se muestra el resumen con el total de comparaciones e intercambios.</p>
     *
     * @param array el arreglo de enteros a ordenar (se modifica in-place)
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static void bubbleSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        int n = array.length;
        int totalComparaciones = 0;
        int totalIntercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            System.out.println("  Pasada " + (i + 1) + ":");
            boolean huboIntercambio = false;

            for (int j = 0; j < n - i - 1; j++) {
                totalComparaciones++;
                System.out.println("    Comparación " + totalComparaciones
                        + ": array[" + j + "]=" + array[j]
                        + " vs array[" + (j + 1) + "]=" + array[j + 1]
                        + (array[j] > array[j + 1] ? " → Intercambiar" : " → Sin cambio"));

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    totalIntercambios++;
                    huboIntercambio = true;
                    System.out.println("    → Intercambio " + totalIntercambios
                            + ": array[" + j + "]=" + array[j]
                            + " ↔ array[" + (j + 1) + "]=" + array[j + 1]);
                }
            }

            System.out.println("    Estado después de la pasada " + (i + 1) + ": "
                    + Arrays.toString(array));
            System.out.println();

            if (!huboIntercambio) {
                System.out.println("  No hubo intercambios en esta pasada. El arreglo ya está ordenado.");
                System.out.println();
                break;
            }
        }

        System.out.println("========== RESUMEN DE OPERACIONES ==========");
        System.out.println("Total de comparaciones realizadas: " + totalComparaciones);
        System.out.println("Total de intercambios realizados:  " + totalIntercambios);
        System.out.println("Tamaño del arreglo (n):            " + n);
        System.out.println("Comparaciones teóricas (n*(n-1)/2): " + (n * (n - 1) / 2));
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara un arreglo de ejemplo desordenado, imprime su contenido original,
     * justifica por qué Bubble Sort es solo para fines didácticos, ejecuta el
     * algoritmo de ordenamiento mostrando paso a paso cada comparación e
     * intercambio, e imprime el resultado junto con un análisis comparativo
     * de complejidad entre Bubble Sort y otros algoritmos de ordenamiento.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] vector = {64, 34, 25, 12, 22, 11, 90};

        System.out.print("Vector original: [");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i < vector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println();
        System.out.println("========== JUSTIFICACIÓN: ¿Por qué Bubble Sort es solo para fines didácticos? ==========");
        System.out.println("Bubble Sort es un algoritmo de ordenamiento que, aunque simple de");
        System.out.println("entender e implementar, presenta limitaciones significativas que lo");
        System.out.println("hacen inadecuado para uso en producción:");
        System.out.println();
        System.out.println("• Complejidad temporal O(n²) en todos los casos (mejor, peor, promedio).");
        System.out.println("• Algoritmos como Quick Sort (O(n log n) promedio) o Merge Sort");
        System.out.println("  (O(n log n)) son significativamente más eficientes.");
        System.out.println("• Para n=1000, Bubble Sort realiza ~1,000,000 operaciones vs ~10,000");
        System.out.println("  de un algoritmo O(n log n).");
        System.out.println("• Solo es útil para conjuntos muy pequeños (< 20 elementos) o como");
        System.out.println("  herramienta de aprendizaje.");

        System.out.println();
        System.out.println("========== PROCESO DE ORDENAMIENTO: BUBBLE SORT ==========");
        System.out.println("Estrategia: Comparar elementos adyacentes e intercambiarlos si están");
        System.out.println("en el orden incorrecto. Repetir hasta que el arreglo esté ordenado.");
        System.out.println();

        int[] vectorCopia = Arrays.copyOf(vector, vector.length);
        System.out.println("Trabajando sobre una copia del vector para preservar el original.");
        System.out.println("Copia: " + Arrays.toString(vectorCopia));
        System.out.println();

        bubbleSort(vectorCopia);

        System.out.println();
        System.out.println("========== RESULTADO ==========");
        System.out.println("Vector original: " + Arrays.toString(vector));
        System.out.println("Vector ordenado: " + Arrays.toString(vectorCopia));

        int n = vector.length;

        System.out.println();
        System.out.println("========== ANÁLISIS DE COMPLEJIDAD ==========");
        System.out.println("┌──────────────────────┬─────────────────────────────────────────────┐");
        System.out.println("│      Caso            │           Complejidad                       │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Mejor caso           │ O(n²) - En la implementación estándar,     │");
        System.out.println("│                      │ siempre se realizan todas las comparaciones │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Peor caso            │ O(n²) - Arreglo ordenado en orden inverso,  │");
        System.out.println("│                      │ se realizan todos los intercambios          │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Caso promedio        │ O(n²) - Aproximadamente n²/2 comparaciones │");
        System.out.println("│                      │ e intercambios en promedio                  │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Espacial             │ O(1) - Ordenamiento in-place, solo una     │");
        System.out.println("│                      │ variable auxiliar para el intercambio       │");
        System.out.println("└──────────────────────┴─────────────────────────────────────────────┘");

        System.out.println();
        System.out.println("========== COMPARACIÓN CON OTROS ALGORITMOS ==========");
        System.out.println("┌────────────────────┬────────────┬────────────┬────────────┐");
        System.out.println("│     Algoritmo      │    Mejor   │   Peor     │  Promedio  │");
        System.out.println("├────────────────────┼────────────┼────────────┼────────────┤");
        System.out.println("│ Bubble Sort        │ O(n²)      │ O(n²)      │ O(n²)      │");
        System.out.println("├────────────────────┼────────────┼────────────┼────────────┤");
        System.out.println("│ Quick Sort         │ O(n log n) │ O(n²)      │ O(n log n) │");
        System.out.println("├────────────────────┼────────────┼────────────┼────────────┤");
        System.out.println("│ Merge Sort         │ O(n log n) │ O(n log n) │ O(n log n) │");
        System.out.println("├────────────────────┼────────────┼────────────┼────────────┤");
        System.out.println("│ Insertion Sort     │ O(n)       │ O(n²)      │ O(n²)      │");
        System.out.println("└────────────────────┴────────────┴────────────┴────────────┘");

        System.out.println();
        System.out.println("========== CONCLUSIÓN ==========");
        System.out.println("Bubble Sort es un algoritmo de ordenamiento ampliamente utilizado en");
        System.out.println("contextos educativos por su simplicidad y facilidad de implementación.");
        System.out.println("Sin embargo, su complejidad cuadrática O(n²) lo hace impráctico para");
        System.out.println("conjuntos de datos de tamaño moderado o grande.");
        System.out.println();
        System.out.println("En entornos de producción, es preferible utilizar algoritmos con");
        System.out.println("complejidad O(n log n) como Quick Sort, Merge Sort o Heap Sort, que");
        System.out.println("ofrecen un rendimiento significativamente superior:");
        System.out.println();
        System.out.println("  • Para n=100:   Bubble Sort ~5,000 ops vs O(n log n) ~700 ops");
        System.out.println("  • Para n=1,000: Bubble Sort ~500,000 ops vs O(n log n) ~10,000 ops");
        System.out.println("  • Para n=10,000: Bubble Sort ~50,000,000 ops vs O(n log n) ~130,000 ops");
        System.out.println();
        System.out.println("Bubble Sort solo se justifica en escenarios muy específicos: arreglos");
        System.out.println("muy pequeños (menos de 20 elementos), cuando la memoria es extremada");
        System.out.println("mente limitada, o cuando el arreglo ya se encuentra casi ordenado.");
    }
}
