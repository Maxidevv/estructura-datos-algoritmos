/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java una función recursiva que imprima un conteo desde n hasta 0.
 *
 * A diferencia de los ejercicios anteriores, esta función no necesariamente devuelve un
 * valor: su propósito es un "efecto secundario", imprimir cada número en pantalla.
 * Por eso la declaramos de tipo void.
 *
 * El caso base es n < 0: cuando n es negativo no hay nada más que imprimir y la
 * recursión se detiene, evitando seguir bajando indefinidamente.
 *
 * En cada llamada se imprime n y luego se invoca la función con n - 1, de modo que el
 * argumento disminuye en 1 y se acerca al caso base.
 *
 * Si en lugar de n - 1 se usara n + 1, el número crecería en cada llamada y nunca se
 * alcanzaría el caso base, provocando una recursión infinita que terminaría en un
 * StackOverflowError.
 *
 * Quiero que el código tenga comentarios explicando el caso base, el caso recursivo
 * y la pila de llamadas.
 *
 * También quiero probarlo con estos casos:
 *   - conteoRegresivo(5)
 *   - conteoRegresivo(0)
 *   - conteoRegresivo(-2)
 */

/**
 * Ejercicio 5 - Conteo regresivo.
 *
 * <p>Implementa de forma recursiva un conteo regresivo desde n hasta 0, imprimiendo
 * cada número en pantalla. La función es de tipo void porque no devuelve un valor:
 * su objetivo es producir un efecto observable (la salida por consola).</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n) - Se realiza una llamada recursiva por cada número a imprimir.</li>
 *   <li>Espacial: O(n) - La pila de llamadas acumula un marco por cada recursión.</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio5 {

    /**
     * Imprime un conteo regresivo desde n hasta 0.
     *
     * @param n el valor desde el cual se inicia el conteo
     */
    static void conteoRegresivo(int n) {
        // Caso base: si n es menor que 0 no queda nada por imprimir.
        // Aquí la recursión se detiene y comienza el "desenrollado" de la pila.
        if (n < 0) {
            return;
        }

        // Imprimir el valor actual antes de la llamada recursiva (efecto secundario)
        System.out.println(n);

        // Caso recursivo: n - 1 acerca el argumento al caso base.
        // Pila de llamadas para conteoRegresivo(3):
        //   conteoRegresivo(3) -> imprime 3 -> conteoRegresivo(2)
        //   conteoRegresivo(2) -> imprime 2 -> conteoRegresivo(1)
        //   conteoRegresivo(1) -> imprime 1 -> conteoRegresivo(0)
        //   conteoRegresivo(0) -> imprime 0 -> conteoRegresivo(-1) -> caso base
        // Salida: 3, 2, 1, 0
        conteoRegresivo(n - 1);
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Prueba la función conteoRegresivo con los casos definidos en el prompt.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 5 - CONTEO REGRESIVO ==========");

        System.out.println("Conteo desde 5:");
        conteoRegresivo(5);

        System.out.println();
        System.out.println("Conteo desde 0:");
        conteoRegresivo(0);

        System.out.println();
        System.out.println("Conteo desde -2 (caso base inmediato):");
        conteoRegresivo(-2);

        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(n)");
        System.out.println("- Espacial: O(n) (pila de llamadas recursivas)");
    }
}

// ---------------------------------------------------------------------------
// MODIFICACIONES AL PROMPT:
// No fue necesario modificar el prompt. Se aclaró en el código, como falla de
// seguridad, que si se usara n + 1 en lugar de n - 1 se produciría una recursión
// infinita (StackOverflowError), tal como el prompt pedía explicar.
// ---------------------------------------------------------------------------