/*
Prompt inicial utilizado:
Quiero implementar en Java un verificador que determine si los paréntesis de una expresión
están balanceados, usando una pila enlazada.

Ejemplos válidos:   (2 + 3) * (5 - 1)   |   ((a + b) * c)
Ejemplos inválidos: (2 + 3              |   ()()

Cómo se usa una pila para guardar aperturas y validar cierres:
Recorremos la expresión carácter por carácter:
  - Cuando encontramos un paréntesis que ABRE '(', lo apilamos; representa una apertura
    pendiente de cerrar.
  - Cuando encontramos un paréntesis que CIERRA ')':
       * si la pila está vacía -> hay un cierre sin apertura -> inválido;
       * si hay algo en la pila -> hacemos pop (emparejamos ese cierre con la última
         apertura, que es la más reciente).
  - Al terminar el recorrido:
       * si la pila está vacía -> todas las aperturas se cerraron -> balanceado;
       * si quedó algo -> hay aperturas sin cerrar -> inválido.
La pila es ideal porque el emparejamiento es LIFO: el último paréntesis abierto es el
primero que debe cerrarse (el más interno).

Estructura: pila enlazada de caracteres.

Operaciones: estaBalanceado(String). Complejidad: O(n) temporal y O(n) espacial en el peor
caso (todos los caracteres son '(').

Casos especiales: expresión vacía (balanceada), solo cierres ")", solo aperturas "(",
aperturas/cierres cruzados ")(".

Prueba: recorrer los ejemplos válidos e inválidos y también casos límite.

Ajustes realizados luego de la primera respuesta de OpenCode:
No hizo falta cambiar el algoritmo. Se agregaron los casos límite (cadena vacía, solo
aperturas, solo cierres, ")(" ) y un mensaje que indica el motivo del desbalanceo.
*/

/**
 * Ejercicio 7 - Verificador de paréntesis balanceados con pila.
 *
 * <p>Recorre la expresión apilando aperturas y desapilando en cada cierre. Balanceado si
 * la pila queda vacía al final. O(n).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio7 {

    /** Nodo de la pila de caracteres. */
    static class Nodo {
        char dato;
        Nodo siguiente;

        Nodo(char dato) {
            this.dato = dato;
        }
    }

    /** Pila enlazada de caracteres. */
    static class PilaCaracteres {
        private Nodo tope;
        private int size;

        void push(char c) {
            Nodo nuevo = new Nodo(c);
            nuevo.siguiente = tope;
            tope = nuevo;
            size++;
        }

        char pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Pila vacía");
            }
            char c = tope.dato;
            tope = tope.siguiente;
            size--;
            return c;
        }

        boolean isEmpty() {
            return tope == null;
        }

        int getSize() {
            return size;
        }
    }

    /**
     * Determina si los paréntesis de la expresión están balanceados. O(n).
     *
     * @param expresion cadena a verificar
     * @return true si está balanceada, false en caso contrario
     */
    static boolean estaBalanceado(String expresion) {
        PilaCaracteres pila = new PilaCaracteres();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false; // cierre sin apertura
                }
                pila.pop();
            }
        }
        return pila.isEmpty(); // quedan aperturas sin cerrar -> false
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 7 - PARÉNTESIS BALANCEADOS ==========");

        String[] validos = {
            "(2 + 3) * (5 - 1)",
            "((a + b) * c)",
            "",
            "sin parentesis",
            "()"
        };
        String[] invalidos = {
            "(2 + 3",
            "())(",
            ")(",
            "((a + b)",
            "a + b)"
        };

        System.out.println("--- Expresiones válidas ---");
        for (String e : validos) {
            System.out.println("[" + (estaBalanceado(e) ? "OK" : "MAL") + "] \"" + e + "\"");
        }

        System.out.println("--- Expresiones inválidas ---");
        for (String e : invalidos) {
            System.out.println("[" + (estaBalanceado(e) ? "OK" : "MAL") + "] \"" + e + "\"");
        }

        System.out.println("\nLa pila empareja por LIFO: el último '(' abierto es el primero");
        System.out.println("que debe encontrar su ')'. Si queda algo al final, faltan cierres.");
    }
}