/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un programa que compare tres algoritmos de ordenamiento
 * simples: Bubble Sort, Selection Sort e Insertion Sort.
 *
 * El algoritmo que debe usar cada método:
 *   - Bubble Sort: compara vecinos e intercambia los invertidos en cada pasada.
 *   - Selection Sort: busca directamente el menor de la zona desordenada y lo coloca
 *     en su posición final con un intercambio por pasada.
 *   - Insertion Sort: inserta cada elemento en el lugar correcto de la parte ya ordenada,
 *     desplazando los mayores hacia la derecha.
 *
 * Datos de entrada: un arreglo de enteros hardcodeado, por ejemplo {9, 3, 7, 1, 5, 8}.
 *
 * El programa debe ordenar el mismo arreglo con los tres métodos y mostrar para cada uno:
 *   - arreglo ordenado;
 *   - cantidad de comparaciones;
 *   - cantidad de intercambios o desplazamientos (según corresponda).
 *
 * El código debe incluir comentarios que permitan comparar las estrategias y no solo el
 * resultado final (por ejemplo, Selection hace menos intercambios, Insertion hace más
 * comparaciones pero menos movimientos en datos casi ordenados, etc.).
 *
 * La verificación se hace mostrando que los tres arreglos quedan idénticos y ordenados.
 */

/**
 * Ejercicio 9 - Comparación de algoritmos simples.
 *
 * <p>Ordena el mismo arreglo con Bubble Sort, Selection Sort e Insertion Sort, mostrando
 * el resultado final y las estadísticas de comparaciones e intercambios/desplazamientos,
 * para comparar las estrategias y no solo el resultado.</p>
 *
 * <p>Complejidad algorítmica (los tres):</p>
 * <ul>
 *   <li>Temporal: O(n^2) en el peor caso.</li>
 *   <li>Espacial: O(1) - ordenamiento in-place.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio9 {

    /**
     * Ordena un arreglo con Bubble Sort y devuelve {comparaciones, intercambios}.
     */
    static int[] bubbleSort(int[] array) {
        int n = array.length;
        int comparaciones = 0, intercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                comparaciones++;
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
     * Ordena un arreglo con Selection Sort y devuelve {comparaciones, intercambios}.
     */
    static int[] selectionSort(int[] array) {
        int n = array.length;
        int comparaciones = 0, intercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                comparaciones++;
                if (array[j] < array[indiceMenor]) {
                    indiceMenor = j;
                }
            }
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
     * Ordena un arreglo con Insertion Sort y devuelve {comparaciones, desplazamientos}.
     */
    static int[] insertionSort(int[] array) {
        int n = array.length;
        int comparaciones = 0, desplazamientos = 0;

        for (int i = 1; i < n; i++) {
            int actual = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > actual) {
                comparaciones++;
                array[j + 1] = array[j];
                j--;
                desplazamientos++;
            }
            // La comparación que sale del while (si no se evaluó por j < 0) no se cuenta;
            // con la estructura while se cuentan solo las comparaciones con resultado true
            // y la última falsa debe contarse por rigor de análisis.
            comparaciones++;

            array[j + 1] = actual;
        }
        return new int[]{comparaciones, desplazamientos};
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
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 9 - COMPARACIÓN DE ALGORITMOS SIMPLES ==========");

        int[] original = {9, 3, 7, 1, 5, 8};

        int[] paraBurbuja = original.clone();
        int[] paraSeleccion = original.clone();
        int[] paraInsercion = original.clone();

        System.out.print("Arreglo original: ");
        imprimir(original);
        System.out.println();

        int[] b = bubbleSort(paraBurbuja);
        System.out.print("Bubble Sort    -> ");
        imprimir(paraBurbuja);
        System.out.println("   Comparaciones: " + b[0] + " | Intercambios: " + b[1]);

        int[] s = selectionSort(paraSeleccion);
        System.out.print("Selection Sort -> ");
        imprimir(paraSeleccion);
        System.out.println("   Comparaciones: " + s[0] + " | Intercambios: " + s[1]);

        int[] in = insertionSort(paraInsercion);
        System.out.print("Insertion Sort -> ");
        imprimir(paraInsercion);
        System.out.println("   Comparaciones: " + in[0] + " | Desplazamientos: " + in[1]);

        System.out.println();
        System.out.println("COMPARACIÓN DE ESTRATEGIAS:");
        System.out.println("- Bubble Sort compara vecinos y tiende a intercambiar mucho.");
        System.out.println("- Selection Sort hace UN solo intercambio por pasada, aunque siempre");
        System.out.println("  recorre toda la zona desordenada (mismas comparaciones estables).");
        System.out.println("- Insertion Sort desplaza elementos hacia la derecha en vez de");
        System.out.println("  intercambiar: suele hacer menos movimientos de elementos.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// En el conteo de comparaciones de Insertion Sort se decidió contabilizar también la
// comparación final falsa del while (cuando sale del bucle), para ser consistentes
// con el análisis teórico clásico del algoritmo. El resto sigue el prompt original.
// ---------------------------------------------------------------------------