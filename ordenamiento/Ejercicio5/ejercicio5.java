/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java el algoritmo ShellSort.
 *
 * El algoritmo a utilizar es ShellSort, que es una generalización de Insertion Sort.
 *
 * El razonamiento es: Insertion Sort es eficiente cuando los datos están casi ordenados,
 * pero es lento si un elemento debe recorrer muchas posiciones. ShellSort arregla esto
 * comparando e intercambiando elementos que están separados por un "gap" (salto), de modo
 * que los elementos grandes se mueven hacia la derecha y los pequeños hacia la izquierda
 * con menos pasos. Luego se reduce el gap hasta llegar a 1, momento en el que se ejecuta
 * un Insertion Sort normal sobre un arreglo que ya está casi ordenado.
 *
 * Los gaps se calculan dividiendo el tamaño del arreglo por 2 de forma sucesiva
 * (n/2, n/4, ... hasta 1).
 *
 * El programa recibe un arreglo de enteros hardcodeado, por ejemplo {52, 6, 19, 3, 8, 45, 12}.
 *
 * Debe mostrar por pantalla el valor del gap en cada etapa y el estado del arreglo luego
 * de cada pasada con ese gap.
 *
 * El código debe incluir comentarios explicando por qué ShellSort mejora a Insertion Sort
 * utilizando saltos.
 *
 * Para verificar, se muestra el arreglo final ordenado y se comprueba que esté ordenado.
 */

/**
 * Ejercicio 5 - ShellSort explicando los gaps.
 *
 * <p>Implementa ShellSort mostrando el valor del gap en cada etapa y el estado del arreglo
 * después de cada pasada. Explica por qué los saltos (gaps) mejoran a Insertion Sort.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: depende de la secuencia de gaps; con la secuencia de dividir por 2 es
 *       del orden de O(n^2) en el peor caso, pero en la práctica es mucho más rápido que
 *       Insertion Sort puro.</li>
 *   <li>Espacial: O(1) - ordenamiento in-place.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio5 {

    /**
     * Ordena un arreglo con ShellSort, mostrando el gap y el estado tras cada etapa.
     *
     * @param array el arreglo a ordenar
     */
    static void shellSort(int[] array) {
        int n = array.length;

        // Los gaps empiezan en n/2 y se reducen a la mitad hasta llegar a 1.
        for (int gap = n / 2; gap > 0; gap /= 2) {
            System.out.println("Gap = " + gap);

            // Con este gap, se realiza una especie de Insertion Sort "saltado":
            // no se compara con el vecino inmediato sino con el elemento a `gap` posiciones.
            for (int i = gap; i < n; i++) {
                int actual = array[i];
                int j = i;

                // A diferencia de Insertion Sort clásico (gap = 1), aquí se salta de a
                // `gap` posiciones. Esto permite que un elemento recorra grandes distancias
                // con pocos movimientos, ordenando el arreglo parcialmente antes de la
                // pasada final con gap = 1.
                while (j >= gap && array[j - gap] > actual) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = actual;
            }

            System.out.print("   Estado del arreglo: ");
            imprimir(array);
        }
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
        System.out.println("========== EJERCICIO 5 - SHELLSORT EXPLICANDO LOS GAPS ==========");

        int[] numeros = {52, 6, 19, 3, 8, 45, 12};

        System.out.print("Arreglo original: ");
        imprimir(numeros);
        System.out.println();

        shellSort(numeros);

        System.out.println();
        System.out.print("Arreglo final ordenado: ");
        imprimir(numeros);

        System.out.println();
        System.out.println("OBSERVACIÓN:");
        System.out.println("Con los gaps, el 52 y el 45 avanzan rápido hacia el final y los");
        System.out.println("números chicos suben sin comparaciones de a uno. La última pasada");
        System.out.println("(gap = 1) es un Insertion Sort sobre un arreglo casi ordenado, por");
        System.out.println("eso ShellSort mejora a Insertion Sort: reduce los desplazamientos");
        System.out.println("realizando primero intercambios con saltos.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. Se usó la secuencia de gaps n/2 (dividir
// por 2) para simplificar el paso a paso, difiriendo la elección de secuencias
// óptimas (como Knuth) en favor de la claridad didáctica.
// ---------------------------------------------------------------------------