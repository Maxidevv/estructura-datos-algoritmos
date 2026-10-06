/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que cuente los dígitos de un
 * número entero positivo.
 *
 * El número puede reducirse usando división entera: cada dígito se "desprende"
 * dividiendo el número por 10. Por ejemplo, 1234 / 10 = 123, perdiendo el último
 * dígito en la división entera.
 *
 * El caso base es un número menor que 10, porque un número de un solo dígito no puede
 * seguir dividiéndose. Un número menor que 10 tiene exactamente un dígito y por eso,
 * en ese caso, la función debe devolver 1.
 *
 * Cada llamada recursiva debe devolver 1 (por el dígito "desprendido") más el conteo
 * de los dígitos que quedan: contarDigitos(n) = 1 + contarDigitos(n / 10).
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - contarDigitos(7)     -> 1
 *   - contarDigitos(10)    -> 2
 *   - contarDigitos(1234)  -> 4
 *   - contarDigitos(100000) -> 6
 *   - contarDigitos(0)     -> 1
 */

/**
 * Ejercicio 6 - Contar dígitos.
 *
 * <p>Implementa de forma recursiva el conteo de dígitos de un número entero positivo.
 * Cada llamada elimina el último dígito mediante división entera entre 10 y suma 1
 * por el dígito eliminado.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(log n) - Hay una llamada recurativa por cada dígito.</li>
 *   <li>Espacial: O(log n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio6 {

    /**
     * Cuenta la cantidad de dígitos de un número entero positivo.
     *
     * @param n el número cuyo número de dígitos se desea contar
     * @return la cantidad de dígitos del número
     */
    static int contarDigitos(int n) {
        // Caso base: si n es menor que 10, tiene un solo dígito.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (n < 10) {
            return 1;
        }

        // Caso recursivo: se descarta el último dígito con división entera y se suma 1.
        // Pila de llamadas para contarDigitos(1234):
        //   contarDigitos(1234) -> 1 + contarDigitos(123)
        //   contarDigitos(123)  -> 1 + contarDigitos(12)
        //   contarDigitos(12)   -> 1 + contarDigitos(1) -> caso base, devuelve 1
        // Desenrollado: 2, 3, 4
        return 1 + contarDigitos(n / 10);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función contarDigitos con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 6 - CONTAR DÍGITOS ==========");

        System.out.println("contarDigitos(7)      = " + contarDigitos(7));
        System.out.println("contarDigitos(10)     = " + contarDigitos(10));
        System.out.println("contarDigitos(1234)   = " + contarDigitos(1234));
        System.out.println("contarDigitos(100000) = " + contarDigitos(100000));
        System.out.println("contarDigitos(0)      = " + contarDigitos(0));

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(log n)");
        System.out.println("- Espacial: O(log n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. Se agregó como caso de prueba n = 0 que,
// al ser menor que 10, es tratado por el caso base y devuelve 1, lo cual coincide
// con el comportamiento esperado para el número 0.
// ---------------------------------------------------------------------------