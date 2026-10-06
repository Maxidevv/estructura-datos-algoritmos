/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que sume los números desde n hasta 1.
 *
 * El caso más simple es n == 0, porque la suma de los números desde 0 hasta 1 no incluye
 * ningún número y el resultado es 0.
 *
 * La función puede pensarse como suma(n) = n + suma(n - 1) porque la suma de n hasta 1
 * equivale a sumar el número n con la suma de todos los números anteriores (n - 1 hasta 1).
 *
 * Si n es 0 la función debe devolver 0, deteniendo así la recursión.
 *
 * El caso recursivo reduce el problema en 1 en cada llamada: suma(n - 1), acercándose
 * al caso base.
 *
 * La función debe devolver el resultado de la suma.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - n = 0  -> 0
 *   - n = 1  -> 1
 *   - n = 5  -> 15
 *   - n = 10 -> 55
 */

/**
 * Ejercicio 2 - Suma de los primeros N números.
 *
 * <p>Implementa de forma recursiva la suma de los números enteros desde n hasta 1.
 * Esta operación equivale a la sumatoria 1 + 2 + ... + n.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n) - Se realiza una llamada recursiva por cada número.</li>
 *   <li>Espacial: O(n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio2 {

    /**
     * Suma los números enteros desde n hasta 1.
     *
     * @param n el límite superior de la sumatoria
     * @return el resultado de 1 + 2 + ... + n
     */
    static int suma(int n) {
        // Caso base: si n es 0, la suma no tiene términos, por lo que se devuelve 0.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (n == 0) {
            return 0;
        }

        // Caso recursivo: suma(n) = n + suma(n - 1)
        // Pila de llamadas para suma(5):
        //   suma(5) -> 5 + suma(4)
        //   suma(4) -> 4 + suma(3)
        //   suma(3) -> 3 + suma(2)
        //   suma(2) -> 2 + suma(1)
        //   suma(1) -> 1 + suma(0)  -> caso base, devuelve 0
        // Desenrollado: 1+0=1, 2+1=3, 3+3=6, 4+6=10, 5+10=15
        return n + suma(n - 1);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función suma con los valores definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 2 - SUMA DE LOS PRIMEROS N NÚMEROS ==========");

        int[] casos = {0, 1, 5, 10};
        for (int n : casos) {
            System.out.println("suma(" + n + ") = " + suma(n));
        }

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(n)");
        System.out.println("- Espacial: O(n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt, la implementación se ajusta al diseño
// acordado: caso base n == 0 y caso recursivo n + suma(n - 1).
// ---------------------------------------------------------------------------