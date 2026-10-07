/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un programa que reciba una expresión matemática y determine
 * si los paréntesis están correctamente balanceados.
 *
 * Estructura necesaria: una pila de caracteres.
 *
 * Cómo se usa la pila:
 *   - Cada vez que se encuentra un paréntesis ABIERTO '(' se hace push de ese carácter,
 *     guardándolo en la pila de "paréntesis abiertos pendientes de cerrar".
 *   - Cada vez que se encuentra un paréntesis CERRADO ')' se hace un pop: se retira un
 *     '(' abierto que había quedado pendiente. Si la pila está vacía cuando hay que hacer
 *     pop, la expresión es inválida (hay un cierre sin apertura).
 *
 * Casos límite:
 *   - Si al terminar quedan paréntesis abiertos sin cerrar en la pila (pila no vacía),
 *     la expresión es inválida.
 *   - Solo si la pila queda vacía al final, la expresión es válida.
 *
 * Qué debe devolver: "válido" o "inválido".
 *
 * Ejemplos:
 *   - "(5 + 3) * (2 + 1)"        -> válido
 *   - "(5 + 3)) * (2 + 1"        -> inválido
 *   - "((1 + 2) * (3 - 1))"      -> válido
 *   - "(1 + 2"                   -> inválido
 *   - ")"                        -> inválido
 *   - "" (vacía)                 -> válido
 */

/**
 * Ejercicio 3 - Validar paréntesis balanceados.
 *
 * <p>Determina si los paréntesis de una expresión matemática están balanceados usando
 * una pila: se apilan los '(' abiertos y se desapilan al encontrar un ')' que los cierre.</p>
 *
 * <p>Complejidad: O(n) temporal, O(n) espacial en el peor caso (todos abiertos).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio3 {

    /**
     * Pila mínima de Strings/characters suficiente para el ejercicio, con arreglo.
     */
    static class Pila {
        private char[] datos;
        private int top = -1;

        Pila(int capacidad) {
            datos = new char[capacidad];
        }

        void push(char c) {
            datos[++top] = c;
        }

        char pop() {
            return datos[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    /**
     * Verifica si una expresión tiene los paréntesis correctamente balanceados.
     *
     * @param expresion la expresión matemática a analizar
     * @return true si está balanceada, false en caso contrario
     */
    static boolean parantesisBalanceados(String expresion) {
        Pila pila = new Pila(expresion.length());

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == '(') {
                // Paréntesis abierto: se guarda en la pila de pendientes de cerrar.
                pila.push(c);
            } else if (c == ')') {
                // Paréntesis cerrado: debe haber un abierto esperándolo. Se hace pop.
                // Si la pila está vacía hay un cierre sin apertura -> inválido.
                if (pila.isEmpty()) {
                    return false;
                }
                pila.pop();
            }
        }

        // Al finalizar, no debe quedar ningún abierto sin cerrar.
        return pila.isEmpty();
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 3 - VALIDAR PARÉNTESIS BALANCEADOS ==========");

        String[] expresiones = {
                "(5 + 3) * (2 + 1)",
                "(5 + 3)) * (2 + 1",
                "((1 + 2) * (3 - 1))",
                "(1 + 2",
                ")",
                ""
        };

        for (String expr : expresiones) {
            boolean valida = parantesisBalanceados(expr);
            System.out.println("\"" + expr + "\" -> " + (valida ? "válido" : "inválido"));
        }

        System.out.println();
        System.out.println("CÓMO SE USA LA PILA:");
        System.out.println("- '(' -> push (queda pendiente de cerrar).");
        System.out.println("- ')' -> pop (cierra un abierto). Si la pila está vacía, inválido.");
        System.out.println("- Al final, la pila debe quedar vacía.");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// No fue necesario modificar el prompt. Se incluyó una pila mínima propia (arreglo
// de chars) para no depender de java.util.Stack y que toda la solución sea "con arreglos"
// como exige el práctico.
// ---------------------------------------------------------------------------