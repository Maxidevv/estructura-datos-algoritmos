/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java Quicksort sobre un arreglo ya ordenado, usando siempre el
 * primer elemento como pivote.
 *
 * Datos de entrada: int[] numeros = {1, 2, 3, 4, 5, 6, 7}.
 *
 * El programa debe contar la cantidad de llamadas recursivas realizadas.
 *
 * El código debe incluir comentarios que expliquen por qué elegir siempre el primer
 * elemento como pivote puede generar un mal rendimiento cuando el arreglo ya está
 * ordenado: la partición queda desbalanceada, un lado siempre queda vacío y el otro
 * contiene todos los elementos restantes, lo que produce el peor caso de Quicksort,
 * con complejidad O(n^2) y muchas llamadas recursivas.
 *
 * Para verificar, se imprime el arreglo ordenado final, la cantidad de llamadas
 * recursivas y una justificación del mal rendimiento.
 */

/**
 * Ejercicio 7 - Peor caso de Quicksort.
 *
 * <p>Ejecuta Quicksort con primer elemento como pivote sobre un arreglo ya ordenado y
 * cuenta las llamadas recursivas. Muestra por qué ese escenario es el peor caso del
 * algoritmo.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n^2) - cada partición desbalanceada deja un lado vacío.</li>
 *   <li>Espacial: O(n) - la profundidad de la recursión alcanza n en el peor caso.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio7 {

    static int llamadasRecursivas = 0;

    /**
     * Partición de Lomuto con primer elemento como pivote.
     *
     * @param array el arreglo completo
     * @param bajo  límite inferior
     * @param alto  límite superior
     * @return índice final del pivote
     */
    static int particion(int[] array, int bajo, int alto) {
        int pivote = array[bajo];
        int i = bajo;

        for (int j = bajo + 1; j <= alto; j++) {
            if (array[j] < pivote) {
                i++;
                int aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
        }

        int aux = array[i];
        array[i] = array[bajo];
        array[bajo] = aux;

        return i;
    }

    /**
     * Quicksort recursivo con contador de llamadas.
     *
     * @param array el arreglo completo
     * @param bajo  límite inferior
     * @param alto  límite superior
     */
    static void quickSort(int[] array, int bajo, int alto) {
        llamadasRecursivas++;
        if (bajo < alto) {
            int pivoteIndex = particion(array, bajo, alto);

            // Con un arreglo ordenado y primer elemento como pivote, el índice del pivote
            // siempre queda en `bajo`: NO hay subarreglo izquierdo y el derecho contiene
            // n - 1 elementos. La recursión se vuelve tan profunda como n (O(n)), y el
            // costo total llega a O(n^2), el peor caso de Quicksort.
            quickSort(array, bajo, pivoteIndex - 1);
            quickSort(array, pivoteIndex + 1, alto);
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
        System.out.println("========== EJERCICIO 7 - PEOR CASO DE QUICKSORT ==========");

        int[] numeros = {1, 2, 3, 4, 5, 6, 7};

        System.out.print("Arreglo ya ordenado: ");
        imprimir(numeros);

        quickSort(numeros, 0, numeros.length - 1);

        System.out.print("Arreglo final (igual): ");
        imprimir(numeros);
        System.out.println("Cantidad de llamadas recursivas: " + llamadasRecursivas);

        System.out.println();
        System.out.println("OBSERVACIÓN:");
        System.out.println("Con n = " + numeros.length + ", se realizan " + llamadasRecursivas +
                " llamadas recursivas.");
        System.out.println("El arreglo ya estaba ordenado y el pivote (primer elemento) es el");
        System.out.println("más pequeño de cada subarreglo: la partición no divide en dos mitades");
        System.out.println("balanceadas, siempre queda un lado vacío. Esto lleva a una profundidad");
        System.out.println("de O(n) y a un costo total de O(n^2), el peor caso de Quicksort.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. Se usó una variable de clase estática para
// contar las llamadas recursivas, tal como pidió la consigna.
// ---------------------------------------------------------------------------