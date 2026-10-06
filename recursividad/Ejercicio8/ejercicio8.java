/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que invierta un String.
 *
 * El caso base es una cadena vacía o de un solo carácter, porque en ambos casos la
 * cadena invertida es la misma y no hay nada que reordenar. La función debe devolver
 * la cadena tal cual.
 *
 * Para separar el primer carácter del resto se usa el método charAt(0) para el primer
 * carácter y substring(1) para el resto de la cadena.
 *
 * La cadena se reduce en cada llamada: invertir(palabra) se convierte en invertir sobre
 * el resto de la cadena, es decir, sobre substring(1). Así, cada llamada trabaja con
 * una cadena un carácter más corta.
 *
 * Al volver de la recursión se reconstruye el resultado: invertir(palabra) =
 * invertir(palabra.substring(1)) + palabra.charAt(0). El primer carácter original pasa
 * a quedar al final, logrando la inversión completa.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estas palabras:
 *   - invertir("hola")   -> "aloh"
 *   - invertir("")       -> ""
 *   - invertir("a")      -> "a"
 *   - invertir("anita")  -> "atina"
 *   - invertir("reconocer") -> "reconocer" (palíndromo)
 */

/**
 * Ejercicio 8 - Invertir una palabra.
 *
 * <p>Implementa de forma recursiva la inversión de una cadena de texto. En cada
 * llamada se toma el primer carácter y se le antepone la inversión del resto,
 * reconstruyendo así la cadena en orden inverso.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n) - Hay una llamada recursiva por cada carácter de la cadena.</li>
 *   <li>Espacial: O(n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio8 {

    /**
     * Invierte una cadena de texto de forma recursiva.
     *
     * @param palabra la cadena a invertir
     * @return la cadena invertida
     */
    static String invertir(String palabra) {
        // Validación defensiva: una cadena nula se devuelve tal cual
        if (palabra == null) {
            return null;
        }

        // Caso base: cadena vacía o de un solo carácter, se devuelve sin cambios.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (palabra.length() <= 1) {
            return palabra;
        }

        // Caso recursivo: se invierte el resto y se le agrega el primer carácter al final.
        // Pila de llamadas para invertir("hola"):
        //   invertir("hola") -> invertir("ola") + 'h'
        //   invertir("ola")  -> invertir("la")  + 'o'
        //   invertir("la")   -> invertir("a")   + 'l' -> caso base, devuelve "a"
        // Desenrollado: "a" + 'l' = "al", "al" + 'o' = "alo", "alo" + 'h' = "aloh"
        return invertir(palabra.substring(1)) + palabra.charAt(0);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función invertir con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 8 - INVERTIR UNA PALABRA ==========");

        System.out.println("invertir(\"hola\")       = \"" + invertir("hola") + "\"");
        System.out.println("invertir(\"\")          = \"" + invertir("") + "\"");
        System.out.println("invertir(\"a\")         = \"" + invertir("a") + "\"");
        System.out.println("invertir(\"anita\")     = \"" + invertir("anita") + "\"");
        System.out.println("invertir(\"reconocer\") = \"" + invertir("reconocer") + "\"");

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(n)");
        System.out.println("- Espacial: O(n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. Se agregó únicamente una validación
// defensiva para el caso de una cadena nula, devolviendo null en lugar de lanzar
// una excepción, para mantener el comportamiento consistente con el caso base.
// ---------------------------------------------------------------------------