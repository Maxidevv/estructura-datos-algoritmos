import java.util.Scanner;

/**
 * Ejercicio 6 - Igualdad de dos vectores con salida anticipada.
 *
 * <p>Este programa implementa un algoritmo que determina si dos vectores de
 * enteros son iguales comparando elemento por elemento. La característica
 * principal del algoritmo es que finaliza en el momento que detecta una
 * diferencia, sin necesidad de recorrer todo el vector.</p>
 *
 * <p><strong>¿Por qué el algoritmo puede detenerse anticipadamente?</strong></p>
 * <ul>
 *   <li>Para que dos vectores sean iguales, TODOS sus elementos deben coincidir
 *       en las mismas posiciones.</li>
 *   <li>Si en alguna posición los elementos difieren, ya no es necesario seguir
 *       comparando: los vectores no pueden ser iguales.</li>
 *   <li>Esta optimización permite ahorrar tiempo cuando los vectores difieren
 *       en las primeras posiciones (mejor caso O(1)).</li>
 * </ul>
 *
 * <p><strong>Análisis de complejidad:</strong></p>
 * <ul>
 *   <li>Mejor caso: O(1) — Los vectores tienen longitudes diferentes, o el
 *       primer elemento ya difiere.</li>
 *   <li>Peor caso: O(n) — Todos los elementos coinciden y se debe recorrer
 *       el vector completo para confirmarlo.</li>
 *   <li>Caso promedio: O(n) — En promedio, se verifican aproximadamente n/2
 *       posiciones antes de encontrar una diferencia o confirmar la igualdad.</li>
 *   <li>Espacial: O(1) — Solo se utilizan variables adicionales (índice y
 *       contador de comparaciones).</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio6 {

    /**
     * Determina si dos arreglos de enteros son iguales comparando elemento por
     * elemento con salida anticipada al detectar la primera diferencia.
     *
     * <p>El algoritmo primero verifica si las longitudes de ambos arreglos son
     * iguales. Si difieren, retorna false inmediatamente. Luego recorre ambos
     * arreglos simultáneamente, comparando cada par de elementos en la misma
     * posición. En cuanto detecta una diferencia, imprime los valores encontrados
     * y retorna false sin revisar las posiciones restantes.</p>
     *
     * <p>Cada paso se imprime en consola mostrando el índice, los valores de
     * ambos arreglos y si coinciden o no, para fines didácticos.</p>
     *
     * @param array1 el primer arreglo de enteros a comparar
     * @param array2 el segundo arreglo de enteros a comparar
     * @return true si ambos arreglos tienen la misma longitud y todos los
     *         elementos coinciden en las mismas posiciones, false en caso contrario
     * @throws IllegalArgumentException si alguno de los arreglos es null
     */
    static boolean areEqual(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("Ninguno de los arreglos puede ser nulo.");
        }

        int comparaciones = 0;

        if (array1.length != array2.length) {
            System.out.println("  Las longitudes difieren: " + array1.length + " vs " + array2.length);
            System.out.println("  No es necesario comparar elementos.");
            System.out.println();
            System.out.println("  Total de comparaciones realizadas: 0");
            return false;
        }

        System.out.println("  Longitudes iguales: " + array1.length + " elementos.");
        System.out.println("  Comparando elemento por elemento con salida anticipada...");
        System.out.println();

        for (int i = 0; i < array1.length; i++) {
            comparaciones++;
            boolean coincide = array1[i] == array2[i];

            System.out.println("  Comparación " + comparaciones + " → Posición " + i
                    + ": array1[" + i + "] = " + array1[i]
                    + ", array2[" + i + "] = " + array2[i]
                    + (coincide ? " → Coinciden" : " → ¡DIFERENTES!"));

            if (!coincide) {
                System.out.println();
                System.out.println("  Se detectó una diferencia en la posición " + i + ".");
                System.out.println("  Los vectores NO son iguales. Salida anticipada.");
                System.out.println();
                System.out.println("  Total de comparaciones realizadas: " + comparaciones);
                return false;
            }
        }

        System.out.println();
        System.out.println("  Todos los elementos coinciden.");
        System.out.println();
        System.out.println("  Total de comparaciones realizadas: " + comparaciones);
        return true;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara dos pares de vectores: uno donde ambos son iguales y otro donde
     * difieren en una posición intermedia. Para cada par imprime los vectores,
     * ejecuta la comparación mostrando paso a paso cada operación, e imprime el
     * resultado junto con el análisis de complejidad algorítmica.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vector 1: [");
        int[] vector1a = {3, 7, 1, 9, 4};
        for (int i = 0; i < vector1a.length; i++) {
            System.out.print(vector1a[i]);
            if (i < vector1a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.print("Vector 2: [");
        int[] vector1b = {3, 7, 1, 9, 4};
        for (int i = 0; i < vector1b.length; i++) {
            System.out.print(vector1b[i]);
            if (i < vector1b.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println();
        System.out.println("========== COMPARACIÓN 1: Vectores iguales ==========");
        System.out.println();

        boolean resultado1 = areEqual(vector1a, vector1b);

        System.out.println("========== RESULTADO 1 ==========");
        System.out.println("¿Los vectores son iguales? " + (resultado1 ? "SÍ" : "NO"));

        System.out.println();
        System.out.println("====================================================");
        System.out.println();

        System.out.print("Vector 3: [");
        int[] vector2a = {3, 7, 1, 9, 4};
        for (int i = 0; i < vector2a.length; i++) {
            System.out.print(vector2a[i]);
            if (i < vector2a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.print("Vector 4: [");
        int[] vector2b = {3, 7, 2, 9, 4};
        for (int i = 0; i < vector2b.length; i++) {
            System.out.print(vector2b[i]);
            if (i < vector2b.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println();
        System.out.println("========== COMPARACIÓN 2: Vectores diferentes ==========");
        System.out.println();

        boolean resultado2 = areEqual(vector2a, vector2b);

        System.out.println("========== RESULTADO 2 ==========");
        System.out.println("¿Los vectores son iguales? " + (resultado2 ? "SÍ" : "NO"));

        System.out.println();
        System.out.println("====================================================");
        System.out.println();

        System.out.println("========== ANÁLISIS DE COMPLEJIDAD ==========");
        System.out.println("┌──────────────────────┬─────────────────────────────────────────────────────┐");
        System.out.println("│      Caso            │           Complejidad                              │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────────────┤");
        System.out.println("│ Mejor caso           │ O(1) - Longitudes diferentes o el primer elemento  │");
        System.out.println("│                      │ ya difiere, terminando de inmediato                │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────────────┤");
        System.out.println("│ Peor caso            │ O(n) - Todos los elementos coinciden, se recorre  │");
        System.out.println("│                      │ el vector completo para confirmar la igualdad      │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────────────┤");
        System.out.println("│ Caso promedio        │ O(n) - En promedio se verifican aproximadamente    │");
        System.out.println("│                      │ n/2 posiciones antes de encontrar una diferencia   │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────────────┤");
        System.out.println("│ Espacial             │ O(1) - Solo se utilizan variables adicionales      │");
        System.out.println("│                      │ (índice y contador de comparaciones)               │");
        System.out.println("└──────────────────────┴─────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("Conclusión:");
        System.out.println("El algoritmo se detiene en el momento que detecta una diferencia, por lo");
        System.out.println("que no necesita recorrer todo el vector si los elementos no coinciden.");
        System.out.println("En el mejor caso solo realiza 1 comparación (O(1)), mientras que en el");
        System.out.println("peor caso debe comparar todos los n elementos (O(n)).");

        scanner.close();
    }
}
