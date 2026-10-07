/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java el algoritmo Quicksort usando siempre el primer elemento
 * como pivote.
 *
 * El algoritmo a utilizar es Quicksort, que sigue la estrategia de "divide y vencerás"
 * y se implementa de forma recursiva.
 *
 * El razonamiento es:
 *   1. Se elige el primer elemento del subarreglo como pivote.
 *   2. Se particiona el subarreglo: todos los elementos menores que el pivote quedan a su
 *      izquierda y los mayores a su derecha. El pivote queda en su posición final.
 *   3. Se aplica recursivamente el mismo procedimiento al subarreglo izquierdo y derecho.
 *
 * Datos de entrada: un arreglo de enteros hardcodeado, por ejemplo {34, 7, 23, 32, 5, 62, 9}.
 *
 * El programa debe mostrar:
 *   - pivote elegido;
 *   - subarreglo izquierdo (menores que el pivote);
 *   - subarreglo derecho (mayores o iguales que el pivote);
 *   - arreglo final ordenado.
 *
 * El código debe incluir comentarios que relacionen el algoritmo con la recursividad y
 * con la estrategia de divide y vencerás.
 *
 * Para verificar, se imprime el arreglo final ordenado y se comprueba que esté ordenado.
 */

/**
 * Ejercicio 6 - Quicksort con primer elemento como pivote.
 *
 * <p>Implementa Quicksort eligiendo siempre el primer elemento como pivote, y muestra
 * pivote elegido, subarreglo izquierdo y derecho en cada partición. Refleja la estrategia
 * de divide y vencerás y su naturaleza recursiva.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n log n) en el caso promedio; O(n^2) en el peor caso (arreglo ordenado).</li>
 *   <li>Espacial: O(log n) en promedio por la pila de llamadas recursivas.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio6 {

    /**
     * Ordena un subarreglo con el esquema Hoare/Lomuto clásico y primer elemento como pivote.
     *
     * <p>Partición de Lomuto: se elige al primero (bajo) como pivote y se recorre el resto
     * moviendo los menores a la izquierda.</p>
     *
     * @param array el arreglo completo
     * @param bajo  límite inferior del subarreglo
     * @param alto  límite superior del subarreglo
     * @return el índice final del pivote
     */
    static int particion(int[] array, int bajo, int alto) {
        int pivote = array[bajo]; // El primer elemento del subarreglo es el pivote.
        System.out.println("   Pivote elegido: " + pivote);

        int i = bajo;

        // Se recorren los demás elementos; los menores al pivote se acumulan a la izquierda.
        for (int j = bajo + 1; j <= alto; j++) {
            if (array[j] < pivote) {
                i++;
                int aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
        }

        // El pivote se coloca en su posición final (entre menores y mayores).
        int aux = array[i];
        array[i] = array[bajo];
        array[bajo] = aux;

        // Se imprime el subarreglo izquierdo y derecho respecto del pivote.
        System.out.print("   Subarreglo izquierdo (menores): ");
        imprimirDesdeHasta(array, bajo, i - 1);
        System.out.print("   Subarreglo derecho (mayores):   ");
        imprimirDesdeHasta(array, i + 1, alto);

        return i;
    }

    /**
     * Quicksort recursivo.
     *
     * <p>Divide y vencerás: se particiona y luego se resuelve recursivamente cada mitad.
     * El caso base se alcanza cuando bajo >= alto (subarreglo de 0 o 1 elemento), que ya
     * está ordenado.</p>
     *
     * @param array el arreglo completo
     * @param bajo  límite inferior
     * @param alto  límite superior
     */
    static void quickSort(int[] array, int bajo, int alto) {
        // Caso base: el subarreglo tiene 0 o 1 elemento, ya está ordenado.
        if (bajo < alto) {
            int pivoteIndex = particion(array, bajo, alto);

            // Llamadas recursivas: se ordenan las dos mitades (divide y vencerás).
            quickSort(array, bajo, pivoteIndex - 1);
            quickSort(array, pivoteIndex + 1, alto);
        }
    }

    /**
     * Imprime una porción del arreglo entre dos índices.
     *
     * @param array el arreglo
     * @param desde índice inicial
     * @param hasta índice final
     */
    static void imprimirDesdeHasta(int[] array, int desde, int hasta) {
        System.out.print("[");
        if (desde <= hasta) {
            for (int i = desde; i <= hasta; i++) {
                System.out.print(array[i]);
                if (i < hasta) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println("]");
    }

    /**
     * Imprime el contenido de un arreglo en una sola línea.
     *
     * @param array el arreglo a imprimir
     */
    static void imprimir(int[] array) {
        imprimirDesdeHasta(array, 0, array.length - 1);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 6 - QUICKSORT CON PRIMER ELEMENTO COMO PIVOTE ==========");

        int[] numeros = {34, 7, 23, 32, 5, 62, 9};

        System.out.print("Arreglo original: ");
        imprimir(numeros);
        System.out.println();

        quickSort(numeros, 0, numeros.length - 1);

        System.out.println();
        System.out.print("Arreglo final ordenado: ");
        imprimir(numeros);

        System.out.println();
        System.out.println("OBSERVACIÓN:");
        System.out.println("Quicksort es un algoritmo de divide y vencerás recursivo:");
        System.out.println("divide el problema particionando alrededor del pivote y");
        System.out.println("resuelve cada mitad con una llamada recursiva a sí mismo.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// El prompt original no precisaba el esquema de partición. Se eligió la partición
// de Lomuto, que es la más simple de explicar paso a paso con primer elemento como
// pivote, sin alterar la esencia del algoritmo pedido.
// ---------------------------------------------------------------------------