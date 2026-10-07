/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un programa que ordene un arreglo de nombres alfabéticamente
 * utilizando Selection Sort.
 *
 * El algoritmo a utilizar es Selection Sort. Su razonamiento es: en cada pasada se busca
 * el elemento menor (en este caso, el nombre alfabéticamente menor) de la zona desordenada
 * y se lo coloca en la siguiente posición ordenada, con un solo intercambio por pasada.
 *
 * Datos de entrada: un arreglo de Strings hardcodeado, por ejemplo
 * String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"}.
 *
 * Debe mostrar por pantalla el arreglo original y el arreglo ordenado alfabéticamente.
 *
 * La comparación cambia respecto de los números: en vez de operadores < y >, se debe usar
 * el método compareTo() de String, que devuelve un número negativo si la palabra es
 * alfabéticamente menor, 0 si es igual y positivo si es mayor.
 *
 * El código debe incluir comentarios explicando cómo cambia la comparación cuando se
 * ordenan textos y no números.
 *
 * Para verificar, se imprime el arreglo final y se comprueba que esté en orden alfabético.
 */

/**
 * Ejercicio 4 - Ordenamiento de nombres con Selection Sort.
 *
 * <p>Ordena alfabéticamente un arreglo de Strings con Selection Sort, mostrando cómo el
 * criterio de comparación cambia respecto de los números: se usa compareTo() y no los
 * operadores relacionales.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n^2) en el peor caso.</li>
 *   <li>Espacial: O(1) - ordenamiento in-place (sin contar el espacio de los Strings).</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio4 {

    /**
     * Ordena alfabéticamente un arreglo de Strings con Selection Sort.
     *
     * <p>La comparación de textos se realiza con String.compareTo(): a diferencia de los
     * números (donde se usa &lt; o &gt;), dos Strings se comparan con compareTo(), que
     * devuelve un valor negativo si el primero es alfabéticamente menor, 0 si son iguales
     * y positivo si es mayor.</p>
     *
     * @param nombres el arreglo de nombres a ordenar
     */
    static void selectionSort(String[] nombres) {
        int n = nombres.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;

            // Se busca el nombre alfabéticamente menor de la zona desordenada.
            // El "menor" deja de ser numérico: se compara con compareTo() < 0.
            for (int j = i + 1; j < n; j++) {
                if (nombres[j].compareTo(nombres[indiceMenor]) < 0) {
                    indiceMenor = j;
                }
            }

            // Se coloca el menor encontrado en su posición final.
            String aux = nombres[i];
            nombres[i] = nombres[indiceMenor];
            nombres[indiceMenor] = aux;
        }
    }

    /**
     * Imprime el contenido de un arreglo de Strings en una sola línea.
     *
     * @param nombres el arreglo a imprimir
     */
    static void imprimir(String[] nombres) {
        System.out.print("[");
        for (int i = 0; i < nombres.length; i++) {
            System.out.print("\"" + nombres[i] + "\"");
            if (i < nombres.length - 1) {
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
        System.out.println("========== EJERCICIO 4 - ORDENAMIENTO DE NOMBRES CON SELECTION SORT ==========");

        String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};

        System.out.print("Arreglo original: ");
        imprimir(nombres);

        selectionSort(nombres);

        System.out.print("Arreglo ordenado: ");
        imprimir(nombres);

        System.out.println();
        System.out.println("OBSERVACIÓN:");
        System.out.println("Para ordenar textos se usa compareTo() en lugar de < o >.");
        System.out.println("compareTo(a, b) < 0 significa que 'a' va antes que 'b' en el");
        System.out.println("orden alfabético. Esto permite reutilizar la misma lógica de");
        System.out.println("Selection Sort con un criterio de comparación distinto.");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. La implementación usa String.compareTo()
// exactamente como se describió y muestra ambos arreglos para verificar el orden.
// ---------------------------------------------------------------------------