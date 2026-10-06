/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que calcule una potencia sin usar Math.pow.
 *
 * Una potencia puede verse como multiplicaciones sucesivas: base^exponente equivale a
 * multiplicar la base por sí misma exponente veces. Por ejemplo, 2^5 = 2 * 2 * 2 * 2 * 2.
 *
 * El caso base es exponente == 0, porque cualquier número elevado a la 0 es 1 por
 * definición matemática. Ahí la recursión se detiene.
 *
 * El caso recursivo reduce el exponente en 1 en cada llamada:
 * potencia(base, exp) = base * potencia(base, exp - 1). De esta forma el problema se
 * acerca paso a paso al caso base.
 *
 * La función debe devolver base^exp. En el caso base (exp == 0) devuelve 1; en el caso
 * recursivo devuelve base multiplicado por el resultado de la llamada más pequeña.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - 2^0  -> 1
 *   - 2^5  -> 32
 *   - 3^3  -> 27
 *   - 5^2  -> 25
 *   - 10^1 -> 10
 */

/**
 * Ejercicio 4 - Potencia.
 *
 * <p>Implementa de forma recursiva el cálculo de una potencia entera sin utilizar
 * Math.pow, expresándola como multiplicaciones sucesivas de la base.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(exp) - Se realiza una llamada recursiva por cada unidad del exponente.</li>
 *   <li>Espacial: O(exp) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio4 {

    /**
     * Calcula base^exponente mediante multiplicaciones recursivas.
     *
     * @param base     el número que se multiplica
     * @param exponente el número de veces que se multiplica la base
     * @return el resultado de elevar base al exponente
     */
    static long potencia(int base, int exponente) {
        // Caso base: exponente == 0, pues cualquier número elevado a la 0 es 1.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (exponente == 0) {
            return 1;
        }

        // Caso recursivo: base^exp = base * base^(exp - 1)
        // Pila de llamadas para potencia(2, 5):
        //   potencia(2, 5) -> 2 * potencia(2, 4)
        //   potencia(2, 4) -> 2 * potencia(2, 3)
        //   potencia(2, 3) -> 2 * potencia(2, 2)
        //   potencia(2, 2) -> 2 * potencia(2, 1)
        //   potencia(2, 1) -> 2 * potencia(2, 0) -> caso base, devuelve 1
        // Desenrollado: 2, 4, 8, 16, 32
        return base * potencia(base, exponente - 1);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función potencia con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 4 - POTENCIA ==========");

        System.out.println("potencia(2, 0)  = " + potencia(2, 0));
        System.out.println("potencia(2, 5)  = " + potencia(2, 5));
        System.out.println("potencia(3, 3)  = " + potencia(3, 3));
        System.out.println("potencia(5, 2)  = " + potencia(5, 2));
        System.out.println("potencia(10, 1) = " + potencia(10, 1));

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(exp)");
        System.out.println("- Espacial: O(exp) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt, la implementación se ajusta al diseño
// acordado: caso base exponente == 0 y caso recursivo base * potencia(base, exp - 1).
// Se usó long como tipo de retorno para evitar desbordes en potencias grandes.
// ---------------------------------------------------------------------------