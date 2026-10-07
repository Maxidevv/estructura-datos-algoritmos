/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java el algoritmo MergeSort.
 *
 * El algoritmo a utilizar es MergeSort, basado en la estrategia de divide y vencerás,
 * y se implementa de forma recursiva en dos fases claramente diferenciadas:
 *
 *   1. DIVIDIR: el arreglo se parte por la mitad de forma recursiva hasta que cada
 *      subarreglo tenga un solo elemento (la "condición de corte" o caso base).
 *   2. FUSIONAR (MERGE): los subarreglos ordenados se van combinando de a pares,
 *      intercalando sus elementos para obtener un subarreglo ordenado más grande.
 *
 * Datos de entrada: un arreglo de enteros hardcodeado, por ejemplo {38, 27, 43, 3, 9, 82, 10}.
 *
 * El programa debe mostrar:
 *   - cómo se divide el arreglo;
 *   - cuándo llega a la condición de corte (subarreglo de 1 elemento);
 *   - cómo se van fusionando los subarreglos.
 *
 * El código debe incluir comentarios que expliquen claramente la diferencia entre dividir
 * (partir el problema en mitades) y fusionar (combinar soluciones parciales ordenadas).
 *
 * Para verificar, se imprime el arreglo final ordenado.
 */

/**
 * Ejercicio 8 - MergeSort paso a paso.
 *
 * <p>Implementa MergeSort mostrando las dos fases del algoritmo: la división recursiva
 * del arreglo hasta la condición de corte y la fusión ordenada de los subarreglos.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n log n) en todos los casos.</li>
 *   <li>Espacial: O(n) - requiere memoria auxiliar para el arreglo temporal al fusionar.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio8 {

    /**
     * Ordena un subarreglo con MergeSort (fase de división).
     *
     * <p>Si el subarreglo tiene 0 o 1 elemento se alcanza la condición de corte: ya está
     * ordenado (caso base, no hay nada que dividir ni fusionar). En caso contrario se
     * parte por la mitad y se ordena cada mitad recursivamente.</p>
     *
     * @param array el arreglo completo
     * @param bajo  límite inferior
     * @param alto  límite superior
     */
    static void mergeSort(int[] array, int bajo, int alto) {
        // Condición de corte (caso base): subarreglo de 1 o 0 elementos, ya está ordenado.
        if (bajo >= alto) {
            System.out.println("      [CORTE] Subarreglo de un solo elemento: [" + array[bajo] + "]");
            return;
        }

        int medio = (bajo + alto) / 2;

        // DIVIDIR: se muestra la partición y se resuelven recursivamente las mitades.
        System.out.println("Dividir: [" + subarreglo(array, bajo, medio) + "] | ["
                + subarreglo(array, medio + 1, alto) + "]");

        mergeSort(array, bajo, medio);
        mergeSort(array, medio + 1, alto);

        // FUSIONAR: una vez ordenadas las mitades, se combinan en un subarreglo ordenado.
        System.out.println("   Fusionar -> [" + subarreglo(array, bajo, alto) + "]");
        fusionar(array, bajo, medio, alto);
    }

    /**
     * Fusiona dos subarreglos ordenados (arreglo[bajo..medio] y arreglo[medio+1..alto]).
     *
     * <p>Se comparan los primeros elementos de cada mitad y se copia el menor al arreglo
     * temporal, intercalando las secuencias hasta agotar ambas mitades.</p>
     *
     * @param array el arreglo completo
     * @param bajo  límite inferior de la primera mitad
     * @param medio límite final de la primera mitad
     * @param alto  límite superior de la segunda mitad
     */
    static void fusionar(int[] array, int bajo, int medio, int alto) {
        int n1 = medio - bajo + 1;
        int n2 = alto - medio;

        int[] izq = new int[n1];
        int[] der = new int[n2];

        for (int i = 0; i < n1; i++) {
            izq[i] = array[bajo + i];
        }
        for (int j = 0; j < n2; j++) {
            der[j] = array[medio + 1 + j];
        }

        int i = 0, j = 0, k = bajo;

        // Fase de intercalado: se elige el menor entre ambos subarreglos hasta agotar uno.
        while (i < n1 && j < n2) {
            if (izq[i] <= der[j]) {
                array[k] = izq[i];
                i++;
            } else {
                array[k] = der[j];
                j++;
            }
            k++;
        }

        // Se copian los elementos restantes de la mitad que no se agotó.
        while (i < n1) {
            array[k] = izq[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = der[j];
            j++;
            k++;
        }
    }

    /**
     * Devuelve una representación en texto de un subarreglo.
     *
     * @param array el arreglo completo
     * @param desde índice inicial
     * @param hasta índice final
     * @return el subarreglo como texto "[a, b, c]"
     */
    static String subarreglo(int[] array, int desde, int hasta) {
        StringBuilder sb = new StringBuilder();
        for (int i = desde; i <= hasta; i++) {
            sb.append(array[i]);
            if (i < hasta) {
                sb.append(", ");
            }
        }
        return sb.toString();
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
        System.out.println("========== EJERCICIO 8 - MERGESORT PASO A PASO ==========");

        int[] numeros = {38, 27, 43, 3, 9, 82, 10};

        System.out.print("Arreglo original: ");
        imprimir(numeros);
        System.out.println();
        System.out.println("=== FASE DE DIVISIÓN Y FUSIÓN ===");

        mergeSort(numeros, 0, numeros.length - 1);

        System.out.println();
        System.out.print("Arreglo final ordenado: ");
        imprimir(numeros);

        System.out.println();
        System.out.println("DIFERENCIA ENTRE DIVIDIR Y FUSIONAR:");
        System.out.println("- DIVIDIR: parte el arreglo por la mitad en cada llamada recursiva");
        System.out.println("  hasta llegar a subarreglos de 1 elemento (condición de corte).");
        System.out.println("- FUSIONAR: combina pares de subarreglos ya ordenados intercalando");
        System.out.println("  sus elementos, subiendo hasta recomponer el arreglo completo.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. El paso a paso imprime la división, la
// condición de corte y la fusión de cada nivel, tal como se solicitó.
// ---------------------------------------------------------------------------