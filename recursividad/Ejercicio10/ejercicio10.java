/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que busque un número dentro de un
 * arreglo, sin usar ciclos.
 *
 * La búsqueda debe empezar desde la posición 0: en la primera llamada se pasa el
 * índice 0 como parámetro de la función.
 *
 * Para avanzar en el arreglo sin usar for ni while, se llama a la misma función con el
 * índice incrementado en 1: buscar(arr, objetivo, indice + 1). El "recorrido" se logra
 * única y exclusivamente mediante llamadas recursivas.
 *
 * El caso base cuando encuentra el valor es que arr[indice] == objetivo: en ese momento
 * la función debe devolver el índice donde está el valor y detener la recursión.
 *
 * El caso base cuando llega al final es que indice >= arr.length: si el arreglo se
 * agotó sin encontrar el valor, la función debe devolver -1 (un valor imposible como
 * índice, que indica "no encontrado").
 *
 * La función debe devolver el índice del elemento encontrado, o -1 si no existe.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - buscar(numero, 7) en {3, 7, 1, 9, 7} -> 1
 *   - buscar(numero, 5) en {3, 7, 1, 9, 7} -> -1
 *   - buscar(numero, 3) en {3}            -> 0
 *   - buscar(numero, 4) en {}            -> -1 (arreglo vacío)
 *   - buscar(numero, 9) en {3, 7, 1, 9}  -> 3
 */

/**
 * Ejercicio 10 - Buscar un elemento en un arreglo.
 *
 * <p>Implementa de forma recursiva la búsqueda lineal de un número dentro de un arreglo
 * de enteros, sin utilizar ninguna estructura de iteración (for o while). El recorrido
 * del arreglo se realiza mediante llamadas recursivas que incrementan el índice.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n) - Se puede visitar cada posición del arreglo una vez.</li>
 *   <li>Espacial: O(n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio10 {

    /**
     * Busca un número dentro de un arreglo de enteros de forma recursiva.
     *
     * @param arreglo  el arreglo en el que se busca
     * @param objetivo el número que se desea encontrar
     * @param indice   la posición desde la cual se continúa la búsqueda (empezar en 0)
     * @return el índice de la primera coincidencia, o -1 si el valor no existe
     */
    static int buscar(int[] arreglo, int objetivo, int indice) {
        // Caso base (final del arreglo): si se recorrió todo el arreglo sin encontrar
        // el valor, se devuelve -1. La recursión se detiene aquí cuando no hay coincidencia.
        if (indice >= arreglo.length) {
            return -1;
        }

        // Caso base (elemento encontrado): si el valor de la posición actual coincide
        // con el objetivo, se devuelve el índice y la recursión se detiene.
        if (arreglo[indice] == objetivo) {
            return indice;
        }

        // Caso recursivo: se avanza al siguiente índice sin usar for ni while.
        // Pila de llamadas para buscar({3, 7, 1, 9}, 9, 0):
        //   buscar(arr, 9, 0) -> arr[0]=3 != 9 -> buscar(arr, 9, 1)
        //   buscar(arr, 9, 1) -> arr[1]=7 != 9 -> buscar(arr, 9, 2)
        //   buscar(arr, 9, 2) -> arr[2]=1 != 9 -> buscar(arr, 9, 3)
        //   buscar(arr, 9, 3) -> arr[3]=9 == 9 -> caso base, devuelve 3
        return buscar(arreglo, objetivo, indice + 1);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función buscar con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 10 - BUSCAR UN ELEMENTO EN UN ARREGLO ==========");

        int[] arreglo = {3, 7, 1, 9, 7};
        int[] vacio = {};

        System.out.println("buscar(7 in {3,7,1,9,7}) = " + buscar(arreglo, 7, 0));
        System.out.println("buscar(5 in {3,7,1,9,7}) = " + buscar(arreglo, 5, 0));
        System.out.println("buscar(3 in {3})         = " + buscar(new int[]{3}, 3, 0));
        System.out.println("buscar(4 in {})          = " + buscar(vacio, 4, 0));
        System.out.println("buscar(9 in {3,7,1,9})   = " + buscar(new int[]{3, 7, 1, 9}, 9, 0));

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(n)");
        System.out.println("- Espacial: O(n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// El prompt original no precisaba qué debía devolverse. Se decidió devolver el índice
// de la primera coincidencia (y -1 cuando no existe), ya que es más informativo que un
// simple booleano y permite verificar la posición exacta en la que aparece el valor.
// ---------------------------------------------------------------------------