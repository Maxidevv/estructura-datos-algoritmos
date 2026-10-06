import java.util.Arrays;

/**
 * Ejercicio 9 - Elemento mayor de una matriz.
 *
 * <p>Este programa implementa un algoritmo que encuentra el elemento con el
 * mayor valor dentro de una matriz bidimensional de enteros. El recorrido se
 * realiza utilizando dos bucles for anidados: el bucle exterior itera sobre las
 * filas y el bucle interior itera sobre las columnas de cada fila (orden
 * row-major).</p>
 *
 * <p><strong>¿Por qué es necesario recorrer todos los elementos?</strong></p>
 * <ul>
 *   <li>En una matriz no ordenada, el elemento máximo puede estar en cualquier
 *       posición.</li>
 *   <li>No existe un patrón que permita descartar filas o columnas sin
 *       verificarlas.</li>
 *   <li>Un valor alto en la primera fila no garantiza que no haya uno mayor en
 *       la última.</li>
 *   <li>Por lo tanto, es obligatorio visitar cada celda al menos una vez para
 *       garantizar que se ha encontrado el verdadero máximo.</li>
 * </ul>
 *
 * <p><strong>Estrategia de recorrido:</strong></p>
 * <ul>
 *   <li>El bucle exterior recorre cada fila de la matriz (índice i).</li>
 *   <li>El bucle interior recorre cada columna de la fila actual (índice j).</li>
 *   <li>En cada celda matriz[i][j] se compara el valor con el máximo actual.</li>
 *   <li>Si el valor es mayor que el máximo, se actualiza el máximo y su
 *       posición.</li>
 *   <li>Este orden garantiza que se visita exactamente una vez cada uno de los
 *       m × n elementos de la matriz.</li>
 * </ul>
 *
 * <p><strong>Análisis de complejidad:</strong></p>
 * <ul>
 *   <li>Mejor caso: O(m × n) — Se deben visitar todas las celdas porque el
 *       máximo puede estar en la última posición.</li>
 *   <li>Peor caso: O(m × n) — Misma situación, no existe atajo posible.</li>
 *   <li>Caso promedio: O(m × n) — Siempre se realizan m × n - 1 comparaciones
 *       sin importar los valores contenidos.</li>
 *   <li>Espacial: O(1) — Solo se utilizan variables para el máximo, su
 *       posición y los contadores de los bucles.</li>
 * </ul>
 *
 * <p>La complejidad O(m × n) es óptima para este problema porque es imposible
 * determinar el mayor elemento sin acceder a cada celda al menos una vez.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio9 {

    /**
     * Encuentra el elemento mayor de una matriz bidimensional de enteros.
     *
     * <p>El algoritmo recorre la matriz utilizando un bucle exterior para las
     * filas y un bucle interior para las columnas. En cada celda, el valor se
     * compara con el máximo actual y, si es mayor, se actualiza tanto el valor
     * del máximo como su posición. Cada comparación se imprime en consola
     * mostrando los índices, el valor accedido, el máximo actual y el
     * resultado de la comparación.</p>
 *
     * <p>Se valida que la matriz no sea nula, que no esté vacía y que cada
     * fila contenga al menos un elemento.</p>
     *
     * @param matrix la matriz de enteros en la que se desea encontrar el mayor
     * @return el valor del elemento mayor de la matriz
     * @throws IllegalArgumentException si la matriz es null, está vacía, o
     *         alguna fila es null o está vacía
     */
    static int findMaxInMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula o estar vacía.");
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length == 0) {
                throw new IllegalArgumentException(
                        "La fila " + i + " de la matriz no puede ser nula o estar vacía.");
            }
        }

        int max = matrix[0][0];
        int maxRow = 0;
        int maxCol = 0;
        int comparaciones = 0;
        int filas = matrix.length;
        int columnas = matrix[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.println("  matriz[" + i + "][" + j + "]=" + matrix[i][j]
                            + " → Inicializado como max actual");
                } else if (matrix[i][j] > max) {
                    comparaciones++;
                    System.out.println("  matriz[" + i + "][" + j + "]=" + matrix[i][j]
                            + " con max actual=" + max + " → Mayor, se actualiza el máximo");
                    max = matrix[i][j];
                    maxRow = i;
                    maxCol = j;
                } else {
                    comparaciones++;
                    System.out.println("  matriz[" + i + "][" + j + "]=" + matrix[i][j]
                            + " con max actual=" + max + " → Menor o igual, no se actualiza");
                }
            }
        }

        System.out.println();
        System.out.println("  Total de comparaciones realizadas: " + comparaciones);
        System.out.println("  (m=" + filas + " filas × n=" + columnas + " columnas - 1 = "
                + (filas * columnas) + " - 1 = " + (filas * columnas - 1) + " comparaciones)");

        System.out.println();
        System.out.println("========== RESULTADO ==========");
        System.out.println("El elemento mayor es " + max + ", encontrado en la posición ["
                + maxRow + "][" + maxCol + "]");

        return max;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara una matriz de ejemplo, imprime su contenido fila por fila,
     * justifica por qué el algoritmo debe recorrer todos los elementos, ejecuta
     * la búsqueda del elemento mayor mostrando paso a paso cada comparación, e
     * imprime el resultado junto con el análisis de complejidad algorítmica.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[][] matriz = {
            {3, 12, 5},
            {8, 2, 15},
            {7, 9, 1}
        };

        System.out.println("Matriz:");
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("  Fila " + i + ": " + Arrays.toString(matriz[i]));
        }

        System.out.println();
        System.out.println("========== JUSTIFICACIÓN: ¿Por qué es necesario recorrer todos los elementos? ==========");
        System.out.println("En una matriz no ordenada, el elemento máximo puede estar en cualquier");
        System.out.println("posición.");
        System.out.println("No existe un patrón que permita descartar filas o columnas sin");
        System.out.println("verificarlas.");
        System.out.println("Un valor alto en la primera fila no garantiza que no haya uno mayor en");
        System.out.println("la última.");
        System.out.println("Por lo tanto, es obligatorio visitar cada celda al menos una vez.");
        System.out.println();

        System.out.println("========== PROCESO DE BÚSQUEDA DEL ELEMENTO MAYOR ==========");
        System.out.println("Estrategia: Recorrer la matriz completa comparando cada elemento");
        System.out.println("con el máximo actual y actualizando si se encuentra uno mayor.");
        System.out.println();

        int resultado = findMaxInMatrix(matriz);

        int filas = matriz.length;
        int columnas = matriz[0].length;

        System.out.println();
        System.out.println("========== ANÁLISIS DE COMPLEJIDAD ==========");
        System.out.println("┌──────────────────────┬─────────────────────────────────────────────┐");
        System.out.println("│      Caso            │           Complejidad                       │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Mejor caso           │ O(m × n) - Se deben visitar todas las      │");
        System.out.println("│                      │ celdas, el máximo puede estar al final     │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Peor caso            │ O(m × n) - Misma situación, siempre se    │");
        System.out.println("│                      │ recorre la matriz completa                 │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Caso promedio        │ O(m × n) - Exactamente m × n - 1          │");
        System.out.println("│                      │ comparaciones sin importar los valores     │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Espacial             │ O(1) - Solo se utilizan variables para el │");
        System.out.println("│                      │ máximo, su posición y contadores           │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Comparaciones reales │ " + filas + " × " + columnas + " - 1 = "
                + (filas * columnas - 1) + " comparaciones" + "                          │");
        System.out.println("└──────────────────────┴─────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("Conclusión:");
        System.out.println("La complejidad temporal es SIEMPRE O(m × n) porque es imposible");
        System.out.println("determinar el elemento mayor de una matriz sin acceder a cada una de");
        System.out.println("sus celdas al menos una vez. Dado que la matriz tiene m filas y n");
        System.out.println("columnas, se realizan exactamente m × n - 1 comparaciones. Esta");
        System.out.println("complejidad es óptima para el problema planteado.");
    }
}
