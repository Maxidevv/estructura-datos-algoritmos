/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que calcule el factorial de un número.
 *
 * Este problema puede resolverse recursivamente porque el factorial de n se define en
 * términos del factorial de un número menor: n! = n * (n - 1)!. Es decir, la solución
 * del caso n depende de la solución de un caso más pequeño, lo cual encaja con el modelo
 * de recursión.
 *
 * El caso base es n == 0 o n == 1, porque por definición matemática 0! = 1 y 1! = 1;
 * en ambos casos la función devuelve 1 directamente sin volver a llamarse a sí misma.
 *
 * Cuando n vale 0 o 1 la función debe devolver 1 inmediatamente, deteniendo la recursión.
 *
 * El caso recursivo es factorial(n) = n * factorial(n - 1), porque en cada llamada el
 * argumento se reduce en 1, acercándose de forma segura al caso base.
 *
 * La función debe devolver el factorial calculado. Se usa long para soportar números
 * más grandes que los que admite int.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - n = 0  -> 1
 *   - n = 1  -> 1
 *   - n = 5  -> 120
 *   - n = 10 -> 3628800
 *   - n = -3 -> debe indicar un error (el factorial no está definido para negativos)
 */

/**
 * Ejercicio 1 - Factorial.
 *
 * <p>Implementa el cálculo del factorial de un número mediante una función
 * recursiva. El factorial de un entero no negativo n se define como
 * n! = n * (n - 1) * ... * 1, con 0! = 1.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n) - Se realiza una llamada recursiva por cada número hasta el 1.</li>
 *   <li>Espacial: O(n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio1 {

    /**
     * Calcula el factorial de un número entero no negativo.
     *
     * @param n el número del que se desea calcular el factorial
     * @return el factorial de n
     * @throws IllegalArgumentException si n es negativo
     */
    static long factorial(int n) {
        // Validación defensiva: el factorial no está definido para números negativos
        if (n < 0) {
            throw new IllegalArgumentException("El factorial no está definido para números negativos: " + n);
        }

        // Caso base: cuando n es 0 o 1, el factorial es 1.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (n == 0 || n == 1) {
            return 1;
        }

        // Caso recursivo: n! = n * (n - 1)!
        // Pila de llamadas para factorial(5):
        //   factorial(5) -> 5 * factorial(4)
        //   factorial(4) -> 4 * factorial(3)
        //   factorial(3) -> 3 * factorial(2)
        //   factorial(2) -> 2 * factorial(1)  -> caso base, devuelve 1
        // Desenrollado: 2*1=2, 3*2=6, 4*6=24, 5*24=120
        return n * factorial(n - 1);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función factorial con los valores definidos en el prompt,
     * incluida la validación del caso negativo.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 1 - FACTORIAL ==========");

        int[] casos = {0, 1, 5, 10};
        for (int n : casos) {
            System.out.println("factorial(" + n + ") = " + factorial(n));
        }

        // Caso de error: factorial de un número negativo
        try {
            factorial(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("factorial(-3) -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(n)");
        System.out.println("- Espacial: O(n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// El prompt original no cambió en su contenido conceptual. Únicamente se agregó
// una validación para números negativos (antes no contemplada), ya que en el caso
// de prueba n = -3 era necesario decidir qué debía hacer la función: decidí que
// lanzara una excepción en lugar de entrar en una recursión infinita.
// ---------------------------------------------------------------------------