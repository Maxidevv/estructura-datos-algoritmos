/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una multiplicación recursiva sin usar el operador *.
 *
 * Una multiplicación puede representarse como sumas repetidas: a * b consiste en
 * sumar el factor a tantas veces como indique el factor b. Por ejemplo,
 * 4 * 3 = 4 + 4 + 4.
 *
 * El parámetro que se reduce es b (el multiplicador), porque en cada suma se "consume"
 * una de las veces que debe repetirse a: multiplicar(a, b) = a + multiplicar(a, b - 1).
 *
 * El caso base debe ser cuando uno de los factores vale 0, porque sumar un factor
 * cero veces no agrega nada: el resultado es 0 y ya no quedan sumas por hacer.
 *
 * La función debe devolver el resultado de la multiplicación.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - 4 * 3   -> 12
 *   - 5 * 0   -> 0
 *   - 0 * 7   -> 0
 *   - 6 * 1   -> 6
 *   - 4 * -3  -> -12 (números negativos)
 */

/**
 * Ejercicio 3 - Multiplicación mediante sumas.
 *
 * <p>Implementa de forma recursiva la multiplicación de dos números enteros sin
 * utilizar el operador *, expresándola como una suma repetida de uno de sus
 * factores.</p>
 *
 * <p>Para simplificar, se reduce el segundo factor (b) en cada llamada. Cuando b es
 * negativo, se convierte en a * (-b) negativo, es decir, -multiplicar(a, -b).</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(|b|) - Se realiza una llamada recursiva por cada unidad del multiplicador.</li>
 *   <li>Espacial: O(|b|) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio3 {

    /**
     * Multiplica dos números enteros mediante sumas repetidas.
     *
     * @param a el primer factor
     * @param b el segundo factor (el que se reduce en cada llamada)
     * @return el producto de a por b
     */
    static int multiplicar(int a, int b) {
        // Caso base: si b es 0, el resultado es 0 porque no hay sumas por realizar.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (b == 0) {
            return 0;
        }

        // Si el multiplicador es negativo, se reduce el problema con su valor absoluto
        // y se aplica el signo al final: a * (-b) = -(a * b).
        if (b < 0) {
            return -multiplicar(a, -b);
        }

        // Caso recursivo: a * b = a + a * (b - 1)
        // Pila de llamadas para multiplicar(4, 3):
        //   multiplicar(4, 3) -> 4 + multiplicar(4, 2)
        //   multiplicar(4, 2) -> 4 + multiplicar(4, 1)
        //   multiplicar(4, 1) -> 4 + multiplicar(4, 0) -> caso base, devuelve 0
        // Desenrollado: 4, 8, 12
        return a + multiplicar(a, b - 1);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función multiplicar con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 3 - MULTIPLICACIÓN MEDIANTE SUMAS ==========");

        System.out.println("multiplicar(4, 3)  = " + multiplicar(4, 3));
        System.out.println("multiplicar(5, 0)  = " + multiplicar(5, 0));
        System.out.println("multiplicar(0, 7)  = " + multiplicar(0, 7));
        System.out.println("multiplicar(6, 1)  = " + multiplicar(6, 1));
        System.out.println("multiplicar(4, -3) = " + multiplicar(4, -3));

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(|b|)");
        System.out.println("- Espacial: O(|b|) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// El prompt original consideraba factores positivos. Se agregó el manejo de
// b negativos (multiplicar(4, -3) = -12) invirtiendo el signo sobre la llamada
// recursiva con el valor absoluto, para cubrir el caso de prueba de negativos
// sin entrar en una recursión sin fin.
// ---------------------------------------------------------------------------