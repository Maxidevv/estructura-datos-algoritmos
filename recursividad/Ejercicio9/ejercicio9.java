/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que determine si una palabra es
 * palíndromo.
 *
 * Hay que comparar el primer y el último carácter porque una palabra es palíndromo si
 * se lee igual de izquierda a derecha y de derecha a izquierda, lo cual se cumple
 * cuando los extremos coinciden y el interior también es palíndromo.
 *
 * Si el primer y el último carácter son distintos, la palabra no puede ser palíndromo:
 * la función debe devolver false inmediatamente, sin hacer más llamadas.
 *
 * El problema se reduce eliminando los extremos: se descarta el primer y el último
 * carácter con substring(1, longitud - 1) y se vuelve a evaluar el interior, que es
 * una palabra más corta.
 *
 * El caso base es una cadena vacía o de un solo carácter, porque toda cadena de
 * longitud 0 o 1 es palíndromo por definición, y no quedan caracteres que comparar.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estas palabras:
 *   - esPalindromo("reconocer") -> true
 *   - esPalindromo("anita")     -> false
 *   - esPalindromo("a")         -> true
 *   - esPalindromo("")          -> true
 *   - esPalindromo("hola")      -> false
 *   - esPalindromo("anilina")   -> true
 */

/**
 * Ejercicio 9 - Palíndromo.
 *
 * <p>Determina de forma recursiva si una cadena de texto es palíndromo. La estrategia
 * compara los extremos de la cadena y reduce el problema eliminándolos en cada llamada,
 * hasta llegar al caso base de una cadena vacía o de un solo carácter.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n/2) = O(n) - Se compara un par de caracteres por llamada.</li>
 *   <li>Espacial: O(n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio9 {

    /**
     * Determina si una cadena es palíndromo, ignorando el caso de los caracteres
     * (mayúsculas y minúsculas se consideran iguales).
     *
     * @param palabra la cadena a verificar
     * @return true si la cadena es palíndromo, false en caso contrario
     */
    static boolean esPalindromo(String palabra) {
        // Validación defensiva: una cadena nula se trata como vacía
        if (palabra == null) {
            palabra = "";
        }

        // Normalización: se considera que mayúsculas y minúsculas son equivalentes
        palabra = palabra.toLowerCase();

        // Caso base: cadena vacía o de un solo carácter, siempre es palíndromo.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (palabra.length() <= 1) {
            return true;
        }

        // Si los extremos difieren, no puede ser palíndromo: devolver false.
        char primero = palabra.charAt(0);
        char ultimo = palabra.charAt(palabra.length() - 1);
        if (primero != ultimo) {
            return false;
        }

        // Caso recursivo: se eliminan los extremos y se evalúa el interior.
        // Pila de llamadas para esPalindromo("reconocer"):
        //   ("reconocer") -> 'r' == 'r', -> esPalindromo("econoc")
        //   ("econoc")    -> 'e' == 'c'? No -> ¡false! (la palabra NO es palíndromo)
        // Ejemplo correcto: esPalindromo("anilina"):
        //   ("anilina") -> 'a' == 'a' -> esPalindromo("nilin")
        //   ("nilin")   -> 'n' == 'n' -> esPalindromo("ili")
        //   ("ili")     -> 'i' == 'i' -> esPalindromo("l") -> caso base, true
        return esPalindromo(palabra.substring(1, palabra.length() - 1));
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función esPalindromo con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 9 - PALÍNDROMO ==========");

        System.out.println("esPalindromo(\"reconocer\") = " + esPalindromo("reconocer"));
        System.out.println("esPalindromo(\"anita\")     = " + esPalindromo("anita"));
        System.out.println("esPalindromo(\"a\")         = " + esPalindromo("a"));
        System.out.println("esPalindromo(\"\")          = " + esPalindromo(""));
        System.out.println("esPalindromo(\"hola\")      = " + esPalindromo("hola"));
        System.out.println("esPalindromo(\"anilina\")   = " + esPalindromo("anilina"));

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(n)");
        System.out.println("- Espacial: O(n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// El prompt original no contemplaba la diferencia entre mayúsculas y minúsculas.
// Para que el ejercicio sea más robusto, se agregó la normalización con toLowerCase()
// y el manejo de cadenas nulas como vacías. Este cambio no altera la lógica recursiva
// acordada (comparar extremos y reducir eliminándolos).
// ---------------------------------------------------------------------------