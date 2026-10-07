/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java dos métodos que ordenen el mismo arreglo de números enteros:
 *
 *   - bubbleSort(int[] array)
 *   - selectionSort(int[] array)
 *
 * Ambos deben ordenar el mismo arreglo de números enteros y, además, contar cuántas
 * comparaciones e intercambios realiza cada algoritmo.
 *
 * Razón del diseño:
 *   - Bubble Sort compara elementos adyacentes (vecinos) e intercambia cuando están
 *     invertidos. En cada pasada el mayor "burbujea" al final. Hace muchas comparaciones
 *     entre vecinos aunque el arreglo esté casi ordenado.
 *   - Selection Sort no intercambia continuamente: en cada pasada busca directamente el
 *     menor de la zona desordenada (recorriéndola por completo) y lo coloca en su posición
 *     final con UN solo intercambio por pasada.
 *
 * El programa recibe un arreglo de enteros hardcodeado, por ejemplo {8, 3, 6, 1, 9, 2}.
 *
 * Debe mostrar por pantalla el arreglo ordenado por cada método, la cantidad de
 * comparaciones y la cantidad de intercambios realizados por cada uno.
 *
 * El código debe incluir comentarios que expliquen la diferencia entre comparar vecinos
 * (Bubble) y buscar directamente el menor (Selection).
 *
 * La verificación se realiza mostrando el resultado de cada método y comprobando que el
 * arreglo queda idéntico (ambos deben ordenarlo igual).
 */

/**
 * Ejercicio 2 - Comparación entre Burbuja y Selección.
 *
 * <p>Implementa Bubble Sort y Selection Sort sobre el mismo arreglo, contando las
 * comparaciones e intercambios que realiza cada uno para razonar la diferencia entre
 * comparar vecinos y buscar directamente el menor.</p>
 *
 * <p>Complejidad algorítmica de ambos:</p>
 * <ul>
 *   <li>Temporal: O(n^2) en el peor caso.</li>
 *   <li>Espacial: O(1) - ordenamiento in-place.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio2 {

    /**
     * Ordena un arreglo con Bubble Sort contando comparaciones e intercambios.
     *
     * @param array el arreglo a ordenar
     * @return un arreglo {comparaciones, intercambios}
     */
    static int[] bubbleSort(int[] array) {
        int n = array.length;
        int comparaciones = 0;
        int intercambios = 0;

        // Cada pasada comparar vecinos (j y j+1) de la zona desordenada.
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                comparaciones++;
                // Si el vecino izquierdo es mayor, se intercambian.
                if (array[j] > array[j + 1]) {
                    int aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                    intercambios++;
                }
            }
        }
        return new int[]{comparaciones, intercambios};
    }

    /**
     * Ordena un arreglo con Selection Sort contando comparaciones e intercambios.
     *
     * @param array el arreglo a ordenar
     * @return un arreglo {comparaciones, intercambios}
     */
    static int[] selectionSort(int[] array) {
        int n = array.length;
        int comparaciones = 0;
        int intercambios = 0;

        // Para cada posición i se busca directamente el menor de la zona desordenada.
        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;

            // Se recorre toda la zona desordenada comparando contra el menor actual.
            // Nota: aquí se compara el menor candidato con cada elemento (no vecinos),
            // por eso el contador de comparaciones crece distinto que en Bubble Sort.
            for (int j = i + 1; j < n; j++) {
                comparaciones++;
                if (array[j] < array[indiceMenor]) {
                    indiceMenor = j;
                }
            }

            // Un único intercambio por pasada coloca al menor en su posición final.
            if (indiceMenor != i) {
                int aux = array[i];
                array[i] = array[indiceMenor];
                array[indiceMenor] = aux;
                intercambios++;
            }
        }
        return new int[]{comparaciones, intercambios};
    }

    /**
     * Imprime el contenido de un arreglo en una sola línea.
     *
     * @param array el arreglo a imprimir
     */
    static void imprimir(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Ordena el mismo arreglo con ambos métodos y muestra las estadísticas.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 2 - COMPARACIÓN BURBUJA vs SELECCIÓN ==========");

        int[] original = {8, 3, 6, 1, 9, 2};

        // Copias independientes del arreglo original para cada algoritmo
        int[] paraBurbuja = original.clone();
        int[] paraSeleccion = original.clone();

        System.out.print("Arreglo original: ");
        imprimir(original);
        System.out.println();

        int[] statsBurbuja = bubbleSort(paraBurbuja);
        System.out.print("Bubble Sort    -> ordenado: ");
        imprimir(paraBurbuja);
        System.out.println("   Comparaciones: " + statsBurbuja[0] + " | Intercambios: " + statsBurbuja[1]);

        int[] statsSeleccion = selectionSort(paraSeleccion);
        System.out.print("Selection Sort -> ordenado: ");
        imprimir(paraSeleccion);
        System.out.println("   Comparaciones: " + statsSeleccion[0] + " | Intercambios: " + statsSeleccion[1]);

        System.out.println();
        System.out.println("OBSERVACIÓN:");
        System.out.println("- Bubble Sort compara vecinos en cada pasada y suele intercambiar más veces.");
        System.out.println("- Selection Sort recorre en busca del menor y hace UN intercambio por pasada,");
        System.out.println("  pero siempre compara toda la zona desordenada.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt, la implementación se ajusta al diseño:
// ambos métodos ordenan el mismo arreglo (con copias independientes) y devuelven
// las estadísticas de comparaciones e intercambios.
// ---------------------------------------------------------------------------