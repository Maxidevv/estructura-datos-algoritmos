import java.util.Arrays;

/**
 * Ejercicio 7 - Inversión de un vector con dos técnicas diferentes.
 *
 * <p>Este programa implementa dos soluciones distintas para invertir (revertir)
 * los elementos de un arreglo de enteros:</p>
 *
 * <p><strong>Solución 1 - Vector Auxiliar:</strong></p>
 * <ul>
 *   <li>Crea un nuevo arreglo del mismo tamaño que el original.</li>
 *   <li>Copia cada elemento del original en la posición inversa del arreglo auxiliar.</li>
 *   <li>Complejidad temporal: O(n) - se recorre el arreglo una sola vez.</li>
 *   <li>Complejidad espacial: O(n) - se crea un arreglo auxiliar del mismo tamaño.</li>
 *   <li>No modifica el arreglo original.</li>
 * </ul>
 *
 * <p><strong>Solución 2 - In-Place (Intercambio de punteros):</strong></p>
 * <ul>
 *   <li>Utiliza dos punteros: uno al inicio y otro al final del arreglo.</li>
 *   <li>Intercambia los elementos en ambas posiciones y mueve los punteros hacia el centro.</li>
 *   <li>Complejidad temporal: O(n/2) = O(n) - se realizan n/2 intercambios.</li>
 *   <li>Complejidad espacial: O(1) - solo se utilizan variables auxiliares para el intercambio.</li>
 *   <li>Modifica el arreglo original directamente.</li>
 * </ul>
 *
 * <p>Comparación general:</p>
 * <ul>
 *   <li>Ambas soluciones tienen complejidad temporal lineal O(n).</li>
 *   <li>La solución con vector auxiliar preserva el original pero requiere memoria adicional O(n).</li>
 *   <li>La solución in-place es más eficiente en memoria pero modifica el original.</li>
 *   <li>La elección depende de si se necesita conservar el vector original.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio7 {

    /**
     * Invierte un arreglo utilizando un vector auxiliar.
     *
     * <p>El algoritmo crea un nuevo arreglo del mismo tamaño y copia cada elemento
     * del arreglo original en la posición inversa del nuevo arreglo. El arreglo
     * original no es modificado.</p>
     *
     * <p>Se imprime cada paso de la copia para fines didácticos, mostrando el
     * índice de origen, el valor copiado y la posición de destino.</p>
     *
     * @param array el arreglo de enteros a invertir
     * @return un nuevo arreglo con los elementos invertidos
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static int[] reverseWithAuxiliary(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        int n = array.length;
        int[] aux = new int[n];

        for (int i = 0; i < n; i++) {
            aux[i] = array[n - 1 - i];
            System.out.println("  Copiando array[" + (n - 1 - i) + "]=" + array[n - 1 - i]
                    + " → aux[" + i + "]=" + aux[i]);
        }

        System.out.println("  Vector auxiliar resultante: " + Arrays.toString(aux));
        return aux;
    }

    /**
     * Invierte un arreglo in-place utilizando la técnica de intercambio de punteros.
     *
     * <p>El algoritmo utiliza dos punteros: uno que comienza en la primera posición
     * (left) y otro en la última posición (right). En cada iteración, se intercambian
     * los elementos en ambas posiciones y los punteros se mueven hacia el centro
     * hasta que se cruzan.</p>
     *
     * <p>Se imprime cada intercambio realizado para fines didácticos, mostrando
     * los índices y valores involucrados en cada paso.</p>
     *
     * @param array el arreglo de enteros a invertir (se modifica in-place)
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static void reverseInPlace(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        int left = 0;
        int right = array.length - 1;
        int paso = 0;

        while (left < right) {
            paso++;
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            System.out.println("  Paso " + paso + ": Intercambiando array[" + left + "]=" + array[right]
                    + " con array[" + right + "]=" + array[left]);
            left++;
            right--;
        }

        System.out.println("  Vector invertido in-place: " + Arrays.toString(array));
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara un vector de ejemplo, ejecuta ambas soluciones de inversión
     * mostrando paso a paso su funcionamiento, e imprime un análisis comparativo
     * de la complejidad temporal y espacial de cada enfoque.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] vector = {3, 7, 1, 9, 4};

        System.out.print("Vector original: [");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i < vector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.println();
        System.out.println("========== SOLUCIÓN 1: Vector Auxiliar ==========");
        System.out.println("Estrategia: Crear un nuevo arreglo y copiar los elementos");
        System.out.println("del original en orden inverso.");
        System.out.println();

        int[] resultado1 = reverseWithAuxiliary(vector);

        System.out.println();
        System.out.print("Vector original (sin modificar): [");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i < vector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("Vector auxiliar resultante:      " + Arrays.toString(resultado1));

        System.out.println();
        System.out.println("========== SOLUCIÓN 2: In-Place (Intercambio de Punteros) ==========");
        System.out.println("Estrategia: Intercambiar elementos desde los extremos hacia");
        System.out.println("el centro utilizando dos punteros.");
        System.out.println();

        int[] vectorCopia = Arrays.copyOf(vector, vector.length);
        System.out.println("Trabajando sobre una copia del vector para preservar el original.");

        reverseInPlace(vectorCopia);

        System.out.println();
        System.out.println("========== COMPARACIÓN DE AMBAS SOLUCIONES ==========");
        System.out.println();
        System.out.println("┌──────────────────────┬──────────────────────┬──────────────────────┐");
        System.out.println("│     Criterio         │   Vector Auxiliar    │      In-Place        │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Complejidad temporal  │ O(n)                │ O(n)                 │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Complejidad espacial  │ O(n)                │ O(1)                 │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Modifica original     │ No                  │ Sí                   │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Memoria adicional     │ n elementos         │ 2 variables (swap)   │");
        System.out.println("├──────────────────────┼──────────────────────┼──────────────────────┤");
        System.out.println("│ Pasos realizados      │ n copias            │ n/2 intercambios     │");
        System.out.println("└──────────────────────┴──────────────────────┴──────────────────────┘");
        System.out.println();
        System.out.println("Ventajas y desventajas:");
        System.out.println();
        System.out.println("Vector Auxiliar:");
        System.out.println("  + No modifica el arreglo original (inmutabilidad).");
        System.out.println("  + Permite conservar ambos vectores (original e invertido).");
        System.out.println("  - Requiere memoria adicional O(n) para el nuevo arreglo.");
        System.out.println("  - Genera presión adicional sobre el recolector de basura.");
        System.out.println();
        System.out.println("In-Place (Intercambio de punteros):");
        System.out.println("  + Usa memoria constante O(1), ideal para arreglos grandes.");
        System.out.println("  + No genera objetos adicionales en memoria.");
        System.out.println("  + Realiza menos operaciones (n/2 intercambios vs n copias).");
        System.out.println("  - Modifica el arreglo original (puede ser indeseado).");
        System.out.println("  - Si se necesita el original, se debe crear una copia antes.");
        System.out.println();
        System.out.println("Conclusión:");
        System.out.println("- Ambas soluciones tienen complejidad temporal lineal O(n).");
        System.out.println("- La diferencia clave está en el uso de memoria: O(n) vs O(1).");
        System.out.println("- La solución in-place es preferida cuando la memoria es un");
        System.out.println("  recurso limitado y no se necesita conservar el original.");
        System.out.println("- La solución con auxiliar es preferida cuando se requiere");
        System.out.println("  inmutabilidad del arreglo original.");
    }
}
