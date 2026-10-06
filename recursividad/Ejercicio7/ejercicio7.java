/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que sume los dígitos de un número
 * entero positivo.
 *
 * El último dígito de un número se obtiene con el operador módulo: n % 10 devuelve el
 * dígito de las unidades. Por ejemplo, 1234 % 10 = 4.
 *
 * El número se reduce usando división entera: n / 10 elimina el último dígito.
 * Por ejemplo, 1234 / 10 = 123.
 *
 * El caso base es un número menor que 10, porque entonces el número coincide con su
 * único dígito y la suma es el propio número.
 *
 * Los resultados se combinan sumando: sumarDigitos(n) = (n % 10) + sumarDigitos(n / 10).
 * Cada llamada aporta el último dígito y delega el resto a la llamada más pequeña.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - sumarDigitos(7)     -> 7
 *   - sumarDigitos(123)   -> 6
 *   - sumarDigitos(999)   -> 27
 *   - sumarDigitos(1005)  -> 6
 *   - sumarDigitos(0)     -> 0
 */

/**
 * Ejercicio 7 - Sumar dígitos.
 *
 * <p>Implementa de forma recursiva la suma de los dígitos de un número entero positivo.
 * El último dígito se obtiene con el módulo (n % 10) y el resto del número con la
 * división entera (n / 10).</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(log n) - Hay una llamada recursiva por cada dígito.</li>
 *   <li>Espacial: O(log n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio7 {

    /**
     * Suma los dígitos de un número entero positivo.
     *
     * @param n el número cuyos dígitos se desean sumar
     * @return la suma de todos los dígitos del número
     */
    static int sumarDigitos(int n) {
        // Caso base: si n es menor que 10, el número es su único dígito.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (n < 10) {
            return n;
        }

        // Caso recursivo: se suma el último dígito (n % 10) con los dígitos restantes.
        // Pila de llamadas para sumarDigitos(123):
        //   sumarDigitos(123) -> 3 + sumarDigitos(12)
        //   sumarDigitos(12)  -> 2 + sumarDigitos(1) -> caso base, devuelve 1
        // Desenrollado: 1 + 2 = 3, 3 + 3 = 6
        return (n % 10) + sumarDigitos(n / 10);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función sumarDigitos con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 7 - SUMAR DÍGITOS ==========");

        System.out.println("sumarDigitos(7)    = " + sumarDigitos(7));
        System.out.println("sumarDigitos(123)  = " + sumarDigitos(123));
        System.out.println("sumarDigitos(999)  = " + sumarDigitos(999));
        System.out.println("sumarDigitos(1005) = " + sumarDigitos(1005));
        System.out.println("sumarDigitos(0)    = " + sumarDigitos(0));

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(log n)");
        System.out.println("- Espacial: O(log n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. Se agregó el caso de prueba n = 0, que al
// ser menor que 10 es cubierto por el caso base y devuelve 0 (la suma de sus
// dígitos es 0).
// ---------------------------------------------------------------------------