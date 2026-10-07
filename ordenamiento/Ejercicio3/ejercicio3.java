/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java Insertion Sort (ordenamiento por inserción) sobre un arreglo
 * que ya esté casi ordenado.
 *
 * El algoritmo a utilizar es Insertion Sort. Su razonamiento es como ordenar cartas en la
 * mano: se toma cada elemento y se lo "inserta" en la posición correcta dentro de la parte
 * ya ordenada que le precede, desplazando hacia la derecha los elementos mayores.
 *
 * Datos de entrada: un arreglo casi ordenado, por ejemplo int[] numeros = {1, 2, 3, 5, 4, 6, 7}.
 *
 * El programa debe mostrar cuántos desplazamientos realiza (cuántas veces un elemento se
 * mueve una posición a la derecha para hacerle lugar a otro).
 *
 * El código debe incluir comentarios que expliquen por qué Insertion Sort funciona bien
 * cuando los datos están casi ordenados: si un elemento ya está en su sitio, casi no hay
 * desplazamientos, y el costo queda cerca de O(n).
 *
 * Para verificar, debe mostrarse el arreglo ordenado final y el conteo de desplazamientos.
 */

/**
 * Ejercicio 3 - Ordenamiento por Inserción con arreglo casi ordenado.
 *
 * <p>Implementa Insertion Sort sobre un arreglo casi ordenado y cuenta los
 * desplazamientos realizados. Muestra por qué este algoritmo es muy eficiente cuando
 * los datos ya están casi ordenados.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n^2) en el peor caso; casi O(n) cuando el arreglo está casi ordenado.</li>
 *   <li>Espacial: O(1) - ordenamiento in-place.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio3 {

    /**
     * Ordena un arreglo con Insertion Sort, contando los desplazamientos realizados.
     *
     * @param array el arreglo a ordenar
     * @return la cantidad de desplazamientos realizados
     */
    static int insertionSort(int[] array) {
        int desplazamientos = 0;

        // Se recorre el arreglo desde el segundo elemento: la primera posición ya
        // forma la "mano ordenada" inicial.
        for (int i = 1; i < array.length; i++) {
            int actual = array[i];
            int j = i - 1;

            // Se desplazan hacia la derecha los elementos mayores que `actual`,
            // abriendo espacio para insertarlo en el lugar correcto. Cada avance de
            // estos valores es un desplazamiento.
            while (j >= 0 && array[j] > actual) {
                array[j + 1] = array[j];
                j--;
                desplazamientos++;
            }

            // Se inserta el elemento en su posición final.
            array[j + 1] = actual;
        }

        return desplazamientos;
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
        System.out.println("========== EJERCICIO 3 - INSERCIÓN CON ARREGLO CASI ORDENADO ==========");

        int[] numeros = {1, 2, 3, 5, 4, 6, 7};

        System.out.print("Arreglo casi ordenado: ");
        imprimir(numeros);

        int desplazamientos = insertionSort(numeros);

        System.out.print("Arreglo ordenado:       ");
        imprimir(numeros);
        System.out.println("Desplazamientos realizados: " + desplazamientos);

        System.out.println();
        System.out.println("OBSERVACIÓN:");
        System.out.println("Solo el elemento 5 estuvo fuera de lugar. Insertion Sort no hizo");
        System.out.println("casi nada con el resto porque ya estaban en su posición.");
        System.out.println("Por eso, con datos casi ordenados, su costo tiende a O(n) y no a O(n^2):");
        System.out.println("mientras menos desorden haya, menos desplazamientos se producen.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. La implementación cuenta desplazamientos
// tal como se pidió y muestra el arreglo ordenado para verificar el resultado.
// ---------------------------------------------------------------------------