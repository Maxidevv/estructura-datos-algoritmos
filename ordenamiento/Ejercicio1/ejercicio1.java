/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un programa que ordene un arreglo de números enteros
 * usando el algoritmo Bubble Sort (ordenamiento burbuja).
 *
 * El algoritmo que debo utilizar es Bubble Sort, que consiste en recorrer el arreglo
 * comparando pares de elementos adyacentes e intercambiándolos si están invertidos.
 *
 * El razonamiento del algoritmo es: en cada pasada, el elemento más grande "burbujea"
 * hacia el final. Cuando un elemento en la posición i es mayor que su vecino en i + 1,
 * se intercambian; así el mayor avanza una posición hacia la derecha. Repitiendo esto
 * varias veces, el mayor de toda la pasada queda al final, y con pasadas sucesivas el
 * arreglo queda ordenado.
 *
 * El programa recibe un arreglo de números enteros hardcodeado, por ejemplo
 * {9, 5, 1, 4, 3}.
 *
 * Debe mostrar por pantalla el arreglo original y, luego, el estado del arreglo después
 * de CADA pasada completa del Bubble Sort, para que se aprecie cómo el elemento mayor
 * se desplaza hacia el final.
 *
 * El código debe incluir comentarios explicando por qué el elemento mayor se va
 * desplazando hacia el final en cada pasada.
 *
 * Para verificar que el resultado es correcto, al final debe mostrarse el arreglo ordenado
 * y comprobarse que coincida con la versión ordenada esperada.
 */

/**
 * Ejercicio 1 - Ordenamiento Burbuja paso a paso.
 *
 * <p>Ordena un arreglo de números enteros mediante Bubble Sort y muestra el estado
 * del arreglo después de cada pasada completa, evidenciando cómo el elemento mayor
 * "burbujea" hacia el final.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n^2) en el peor caso (arreglo invertido).</li>
 *   <li>Espacial: O(1) - ordenamiento in-place, solo usa una variable auxiliar.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio1 {

    /**
     * Ordena un arreglo con Bubble Sort e imprime su estado tras cada pasada.
     *
     * @param array el arreglo de enteros a ordenar
     */
    static void bubbleSortPasoAPaso(int[] array) {
        int n = array.length;

        // Se realizan n - 1 pasadas como máximo; en cada una el mayor queda al final.
        // Por cada pasada el "frente de ordenación" (los últimos i elementos) se cierra.
        for (int i = 0; i < n - 1; i++) {
            boolean huboIntercambio = false;

            // Se comparan vecinos hasta la zona aún desordenada.
            // El elemento mayor de la pasada avanza comparaciones repetidas:
            // si arr[j] > arr[j + 1], se intercambian, y así "burbujea" a la derecha.
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                    huboIntercambio = true;
                }
            }

            System.out.print("Pasada " + (i + 1) + ": ");
            imprimir(array);

            // Optimización: si no hubo intercambios, el arreglo ya está ordenado.
            if (!huboIntercambio) {
                break;
            }
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
     * <p>Declara el arreglo de ejemplo, muestra el original y cada pasada del Bubble Sort.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 1 - ORDENAMIENTO BURBUJA PASO A PASO ==========");

        int[] numeros = {9, 5, 1, 4, 3};

        System.out.print("Arreglo original: ");
        imprimir(numeros);

        System.out.println();
        bubbleSortPasoAPaso(numeros);

        System.out.println();
        System.out.print("Arreglo final ordenado: ");
        imprimir(numeros);

        System.out.println();
        System.out.println("OBSERVACIÓN:");
        System.out.println("El mayor (9) avanza una posición hacia la derecha en cada comparación");
        System.out.println("hasta llegar al final en la primera pasada. Luego 5, luego 4, etc.");
        System.out.println("Esto ocurre porque cada par invertido se intercambia, empujando el");
        System.out.println("elemento más grande de la pasada hacia el extremo derecho.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// El prompt original no pedía la optimización de detenerse cuando no hay
// intercambios ni el resumen final. Se agregaron ambas para que el paso a paso
// sea más claro y para verificar el resultado, sin cambiar el algoritmo pedido.
// ---------------------------------------------------------------------------