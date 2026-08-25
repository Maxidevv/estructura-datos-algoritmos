import java.util.Arrays;

/**
 * Ejercicio 8 - Suma de todos los elementos de una matriz.
 *
 * <p>Este programa implementa un algoritmo que calcula la suma de todos los
 * elementos de una matriz bidimensional de enteros. El recorrido se realiza
 * utilizando dos bucles for anidados: el bucle exterior itera sobre las filas
 * y el bucle interior itera sobre las columnas de cada fila (orden row-major).</p>
 *
 * <p><strong>Estrategia de recorrido:</strong></p>
 * <ul>
 *   <li>El bucle exterior recorre cada fila de la matriz (índice i).</li>
 *   <li>El bucle interior recorre cada columna de la fila actual (índice j).</li>
 *   <li>En cada celda matriz[i][j] se realiza una operación de suma para
 *       acumular el valor en un acumulador.</li>
 *   <li>Este orden garantiza que se visita exactamente una vez cada uno de los
 *       m × n elementos de la matriz.</li>
 * </ul>
 *
 * <p><strong>Análisis de complejidad:</strong></p>
 * <ul>
 *   <li>Mejor caso: O(m × n) — Se deben visitar todas las celdas porque la suma
 *       no puede calcularse sin acceder a cada elemento.</li>
 *   <li>Peor caso: O(m × n) — Misma situación, no existe atajo posible.</li>
 *   <li>Caso promedio: O(m × n) — Siempre se realizan exactamente m × n
 *       adiciones sin importar los valores contenidos.</li>
 *   <li>Espacial: O(1) — Solo se utiliza un acumulador y las variables
 *       de control de los bucles.</li>
 * </ul>
 *
 * <p>La complejidad O(m × n) es óptima para este problema porque es imposible
 * calcular la suma total sin acceder a cada elemento al menos una vez.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio8 {

    /**
     * Calcula la suma de todos los elementos de una matriz bidimensional.
     *
     * <p>El algoritmo recorre la matriz utilizando un bucle exterior para las
     * filas y un bucle interior para las columnas. En cada celda, el valor se
     * acumula en una variable y se imprime el paso realizado mostrando los
     * índices, el valor accedido y el acumulado parcial.</p>
     *
     * <p>Se valida que la matriz no sea nula, que no esté vacía y que cada
     * fila contenga al menos un elemento.</p>
     *
     * @param matrix la matriz de enteros cuya suma se desea calcular
     * @return la suma total de todos los elementos de la matriz
     * @throws IllegalArgumentException si la matriz es null, está vacía, o
     *         alguna fila es null o está vacía
     */
    static int sumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("La matriz no puede ser nula o estar vacía.");
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length == 0) {
                throw new IllegalArgumentException(
                        "La fila " + i + " de la matriz no puede ser nula o estar vacía.");
            }
        }

        int acumulado = 0;
        int operaciones = 0;
        int filas = matrix.length;
        int columnas = matrix[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                operaciones++;
                acumulado += matrix[i][j];
                System.out.println("  Sumando matriz[" + i + "][" + j + "]=" + matrix[i][j]
                        + " → Acumulado: " + acumulado);
            }
        }

        System.out.println();
        System.out.println("  Total de operaciones de suma realizadas: " + operaciones);
        System.out.println("  (m=" + filas + " filas × n=" + columnas + " columnas = " + (filas * columnas) + ")");
        return acumulado;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara una matriz de ejemplo, imprime su contenido fila por fila,
     * describe la estrategia de recorrido, ejecuta el cálculo de la suma
     * mostrando paso a paso cada operación, e imprime el resultado junto
     * con el análisis de complejidad algorítmica.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Matriz:");
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("  Fila " + i + ": " + Arrays.toString(matriz[i]));
        }

        System.out.println();
        System.out.println("========== ESTRATEGIA DE RECORRIDO ==========");
        System.out.println("Recorrido: Bucle exterior por filas, bucle interior por columnas");
        System.out.println("(row-major order).");
        System.out.println("Se visitará cada celda exactamente una vez.");

        System.out.println();
        System.out.println("========== PROCESO DE SUMA ==========");
        System.out.println("Estrategia: Recorrer la matriz completa sumando cada elemento");
        System.out.println("a un acumulador.");
        System.out.println();

        int resultado = sumMatrix(matriz);

        System.out.println();
        System.out.println("========== RESULTADO ==========");
        System.out.println("La suma de todos los elementos de la matriz es: " + resultado);

        int filas = matriz.length;
        int columnas = matriz[0].length;

        System.out.println();
        System.out.println("========== ANÁLISIS DE COMPLEJIDAD ==========");
        System.out.println("┌──────────────────────┬─────────────────────────────────────────────┐");
        System.out.println("│      Caso            │           Complejidad                       │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Mejor caso           │ O(m × n) - Se deben visitar todas las      │");
        System.out.println("│                      │ celdas, no existe atajo posible             │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Peor caso            │ O(m × n) - Misma situación, siempre se    │");
        System.out.println("│                      │ recorre la matriz completa                 │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Caso promedio        │ O(m × n) - Exactamente m × n adiciones   │");
        System.out.println("│                      │ sin importar los valores de la matriz      │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Espacial             │ O(1) - Solo se utiliza un acumulador y    │");
        System.out.println("│                      │ las variables de control de los bucles     │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Operaciones reales   │ " + filas + " × " + columnas + " = " + (filas * columnas)
                + " adiciones" + "                             │");
        System.out.println("└──────────────────────┴─────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("Conclusión:");
        System.out.println("La complejidad temporal es SIEMPRE O(m × n) porque es imposible");
        System.out.println("calcular la suma total de una matriz sin acceder a cada uno de sus");
        System.out.println("elementos al menos una vez. Dado que la matriz tiene m filas y n");
        System.out.println("columnas, se realizan exactamente m × n operaciones de suma.");
        System.out.println("Esta complejidad es óptima para el problema planteado.");
    }
}
