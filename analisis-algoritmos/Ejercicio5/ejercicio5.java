import java.util.Scanner;

/**
 * Ejercicio 5 - Conteo de ocurrencias de un valor dentro de un vector.
 *
 * <p>Este programa implementa un algoritmo que cuenta cuántas veces aparece
 * un determinado valor dentro de un arreglo de enteros. El algoritmo recorre
 * completamente el vector sin posibilidad de detenerse anticipadamente, ya que
 * el valor buscado puede aparecer en cualquier posición.</p>
 *
 * <p><strong>¿Por qué el algoritmo debe recorrer completamente el vector?</strong></p>
 * <ul>
 *   <li>No existe forma de saber cuántas veces aparece un valor sin revisar
 *       todos los elementos del arreglo.</li>
 *   <li>A diferencia de la búsqueda (que puede detenerse al encontrar la primera
 *       ocurrencia), el conteo requiere verificar cada posición del vector.</li>
 *   <li>Un vector como {7, 1, 7} buscaría el primer 7 y se detendría, pero el
 *       conteo correcto es 2. Por lo tanto, es obligatorio revisar cada posición.</li>
 *   <li>El valor objetivo puede aparecer cero, una o múltiples veces, incluso
 *       en la última posición del arreglo.</li>
 * </ul>
 *
 * <p><strong>Análisis de complejidad:</strong></p>
 * <ul>
 *   <li>Mejor caso: O(n) — El valor no aparece ninguna vez, pero se deben
 *       revisar todos los elementos para confirmarlo.</li>
 *   <li>Peor caso: O(n) — El valor aparece en todas las posiciones, lo que
 *       requiere recorrer el vector completo.</li>
 *   <li>Caso promedio: O(n) — Independientemente del contenido, siempre se
 *       realiza exactamente n comparaciones.</li>
 *   <li>Espacial: O(1) — Solo se utiliza una variable adicional (el contador).</li>
 * </ul>
 *
 * <p>En resumen, la complejidad temporal es <em>siempre</em> O(n) porque no se
 * puede omitir ninguna posición del vector.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class ejercicio5 {

    /**
     * Cuenta cuántas veces aparece un valor determinado dentro de un arreglo.
     *
     * <p>El algoritmo recorre el arreglo completo de izquierda a derecha,
     * verificando en cada posición si el elemento coincide con el valor objetivo.
     * Cada comparación se imprime en consola mostrando el índice, el valor
     * almacenado y si coincide con el objetivo.</p>
     *
     * <p>Es fundamental recorrer la totalidad del vector porque el valor objetivo
     * podría aparecer en cualquier posición, incluida la última. No existe
     * condición que permita detener la búsqueda anticipadamente.</p>
     *
     * @param array  el arreglo de enteros a recorrer
     * @param target el valor cuyas ocurrencias se desean contar
     * @return el número total de veces que aparece el valor en el arreglo
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static int countOccurrences(int[] array, int target) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        int contador = 0;
        int posicionesVerificadas = 0;

        for (int i = 0; i < array.length; i++) {
            posicionesVerificadas++;
            boolean coincide = array[i] == target;
            if (coincide) {
                contador++;
            }

            System.out.println("  Posición " + i + ": array[" + i + "] = " + array[i]
                    + (coincide ? " → Coincide con " + target + " → Contador: " + contador
                            : " → No coincide con " + target));
        }

        System.out.println();
        System.out.println("  Total de posiciones verificadas: " + posicionesVerificadas);
        return contador;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara un vector de ejemplo con elementos repetidos, solicita al
     * usuario el valor cuyo conteo se desea realizar, justifica por qué el
     * algoritmo debe recorrer completamente el vector, ejecuta el conteo
     * mostrando paso a paso cada comparación, e imprime el resultado junto
     * con el análisis de complejidad algorítmica.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] vector = {3, 7, 1, 9, 4, 7, 2, 3, 8, 7};
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vector: [");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i < vector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.print("Ingrese el valor a contar: ");
        int objetivo = scanner.nextInt();

        System.out.println();
        System.out.println("========== JUSTIFICACIÓN: ¿Por qué recorrer todo el vector? ==========");
        System.out.println("El algoritmo debe recorrer completamente el vector porque no hay");
        System.out.println("forma de saber cuántas veces aparece un valor sin revisar todos los");
        System.out.println("elementos.");
        System.out.println("A diferencia de la búsqueda que puede detenerse al encontrar la");
        System.out.println("primera ocurrencia, el conteo requiere verificar cada posición.");
        System.out.println("Un vector {7, 1, 7} buscaría el primer 7 y se detendría, pero el");
        System.out.println("conteo correcto es 2.");
        System.out.println();

        System.out.println("========== PROCESO DE CONTEO ==========");
        System.out.println("Estrategia: Recorrer el vector completo comparando cada elemento");
        System.out.println("con el valor objetivo y acumulando las coincidencias.");
        System.out.println();

        int resultado = countOccurrences(vector, objetivo);

        System.out.println();
        System.out.println("========== RESULTADO ==========");
        System.out.println("El valor " + objetivo + " aparece " + resultado + " vez/veces en el vector.");

        System.out.println();
        System.out.println("========== ANÁLISIS DE COMPLEJIDAD ==========");
        System.out.println("┌──────────────────────┬─────────────────────────────────────────────┐");
        System.out.println("│      Caso            │           Complejidad                       │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Mejor caso           │ O(n) - El valor no aparece, pero se deben   │");
        System.out.println("│                      │ revisar todos los elementos para confirmarlo│");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Peor caso            │ O(n) - El valor aparece en todas las        │");
        System.out.println("│                      │ posiciones, recorriendo el vector completo │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Caso promedio        │ O(n) - Siempre se realizan exactamente n    │");
        System.out.println("│                      │ comparaciones sin importar el contenido    │");
        System.out.println("├──────────────────────┼─────────────────────────────────────────────┤");
        System.out.println("│ Espacial             │ O(1) - Solo se utiliza una variable extra   │");
        System.out.println("│                      │ (el contador)                              │");
        System.out.println("└──────────────────────┴─────────────────────────────────────────────┘");
        System.out.println();
        System.out.println("Conclusión:");
        System.out.println("La complejidad temporal es SIEMPRE O(n) porque no se puede omitir");
        System.out.println("ninguna posición del vector. A diferencia de la búsqueda que puede");
        System.out.println("detenerse anticipadamente, el conteo de ocurrencias requiere revisar");
        System.out.println("el 100% de los elementos en todos los casos.");

        scanner.close();
    }
}
