import java.util.HashSet;

/**
 * Ejercicio 4 - Detección de elementos duplicados en un vector.
 *
 * <p>Este programa implementa dos soluciones diferentes para detectar si existen
 * elementos duplicados dentro de un arreglo de enteros:</p>
 *
 * <p><strong>Solución 1 - Ciclos anidados (Fuerza bruta):</strong></p>
 * <ul>
 *   <li>Utiliza dos bucles for anidados para comparar cada par de elementos.</li>
 *   <li>El bucle exterior recorre el arreglo desde la posición 0 hasta n-1.</li>
 *   <li>El bucle interior compara cada elemento con los que le siguen.</li>
 *   <li>Complejidad temporal: O(n²) - se realizan n*(n-1)/2 comparaciones en el peor caso.</li>
 *   <li>Complejidad espacial: O(1) - no se utiliza memoria adicional.</li>
 * </ul>
 *
 * <p><strong>Solución 2 - HashSet (Conjunto hash):</strong></p>
 * <ul>
 *   <li>Utiliza un HashSet para almacenar los elementos ya vistos.</li>
 *   <li>Para cada elemento, se verifica si ya existe en el conjunto antes de agregarlo.</li>
 *   <li>Complejidad temporal: O(n) - se recorre el arreglo una sola vez.</li>
 *   <li>Complejidad espacial: O(n) - en el peor caso se almacenan todos los elementos.</li>
 * </ul>
 *
 * <p>Comparación general:</p>
 * <ul>
 *   <li>Los ciclos anidados son simples pero lentos para arreglos grandes.</li>
 *   <li>El HashSet es significativamente más rápido pero requiere memoria adicional.</li>
 *   <li>En la práctica, la solución con HashSet es preferida cuando la memoria lo permite.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio4 {

    /**
     * Detecta duplicados utilizando dos ciclos anidados (fuerza bruta).
     *
     * <p>El algoritmo recorre el arreglo con un bucle exterior que selecciona cada
     * elemento, y un bucle interior que compara ese elemento con todos los que le
     * siguen en el arreglo. Si encuentra dos elementos iguales, retorna true.</p>
     *
     * <p>Se imprime cada comparación realizada para fines didácticos, mostrando
     * los índices y valores involucrados en cada paso.</p>
     *
     * @param array el arreglo de enteros a evaluar
     * @return true si se encuentra al menos un duplicado, false en caso contrario
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static boolean detectDuplicatesNestedLoops(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        int comparaciones = 0;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                comparaciones++;
                System.out.println("  Comparación " + comparaciones + ": array[" + i + "]=" + array[i]
                        + " vs array[" + j + "]=" + array[j]
                        + (array[i] == array[j] ? " → ¡Duplicado encontrado!" : " → Diferentes."));

                if (array[i] == array[j]) {
                    System.out.println("  Total de comparaciones realizadas: " + comparaciones);
                    return true;
                }
            }
        }

        System.out.println("  No se encontraron duplicados tras " + comparaciones + " comparaciones.");
        return false;
    }

    /**
     * Detecta duplicados utilizando un HashSet.
     *
     * <p>El algoritmo recorre el arreglo una sola vez. Para cada elemento, verifica
     * si ya existe en el HashSet. Si existe, se ha encontrado un duplicado y se
     * retorna true. Si no existe, se agrega al conjunto y se continúa.</p>
     *
     * <p>Se imprime el estado del HashSet en cada paso para fines didácticos.</p>
     *
     * @param array el arreglo de enteros a evaluar
     * @return true si se encuentra al menos un duplicado, false en caso contrario
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static boolean detectDuplicatesHashSet(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        HashSet<Integer> conjunto = new HashSet<>();
        int pasos = 0;

        for (int i = 0; i < array.length; i++) {
            pasos++;
            if (conjunto.contains(array[i])) {
                System.out.println("  Paso " + pasos + ": array[" + i + "]=" + array[i]
                        + " ya existe en el conjunto → ¡Duplicado encontrado!");
                return true;
            } else {
                conjunto.add(array[i]);
                System.out.println("  Paso " + pasos + ": array[" + i + "]=" + array[i]
                        + " no existe en el conjunto → Agregado. Conjunto: " + conjunto);
            }
        }

        System.out.println("  No se encontraron duplicados tras " + pasos + " pasos.");
        return false;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara un vector de ejemplo que contiene elementos duplicados, ejecuta
     * ambas soluciones de detección de duplicados mostrando paso a paso su
     * funcionamiento, e imprime un análisis comparativo de la complejidad
     * temporal y espacial de cada enfoque.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] vector = {3, 7, 1, 9, 4, 7, 2, 3, 8};

        System.out.print("Vector: [");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i < vector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println();
        System.out.println("========== SOLUCIÓN 1: Ciclos Anidados (Fuerza Bruta) ==========");
        System.out.println("Estrategia: Comparar cada elemento con todos los demás utilizando");
        System.out.println("dos bucles for anidados.");
        System.out.println();

        boolean resultado1 = detectDuplicatesNestedLoops(vector);
        System.out.println("Resultado: " + (resultado1 ? "Se encontraron duplicados." : "No se encontraron duplicados."));

        System.out.println();
        System.out.println("========== SOLUCIÓN 2: HashSet (Conjunto Hash) ==========");
        System.out.println("Estrategia: Almacenar cada elemento en un HashSet y verificar");
        System.out.println("si ya existe antes de agregarlo.");
        System.out.println();

        boolean resultado2 = detectDuplicatesHashSet(vector);
        System.out.println("Resultado: " + (resultado2 ? "Se encontraron duplicados." : "No se encontraron duplicados."));

        System.out.println();
        System.out.println("========== COMPARACIÓN DE AMBAS SOLUCIONES ==========");
        System.out.println();
        System.out.println("┌──────────────────────┬──────────────────────┬──────────────────────┐");
        System.out.println("│     Criterio         │   Ciclos Anidados    │      HashSet         │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Complejidad temporal  │ O(n²)               │ O(n)                 │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Complejidad espacial  │ O(1)                │ O(n)                 │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Estructura auxiliar   │ Ninguna             │ HashSet              │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Mejor caso (temporal) │ O(n)                │ O(n)                 │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Peor caso (temporal)  │ O(n²)               │ O(n)                 │");
        System.out.println("└──────────────────────┴──────────────────────┴──────────────────────┘");
        System.out.println();
        System.out.println("Conclusión:");
        System.out.println("- Los ciclos anidados no utilizan memoria adicional, pero son");
        System.out.println("  significativamente más lentos para arreglos grandes debido a su");
        System.out.println("  complejidad cuadrática O(n²).");
        System.out.println("- El HashSet es mucho más rápido con complejidad lineal O(n),");
        System.out.println("  pero requiere memoria adicional O(n) para almacenar los");
        System.out.println("  elementos del conjunto.");
        System.out.println("- En la práctica, la solución con HashSet es preferida cuando");
        System.out.println("  la memoria lo permite, ya que ofrece un mejor rendimiento");
        System.out.println("  para arreglos de tamaño moderado o grande.");
    }
}
