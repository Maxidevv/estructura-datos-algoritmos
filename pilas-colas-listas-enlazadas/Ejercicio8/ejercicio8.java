/*
Prompt inicial utilizado:
Quiero implementar en Java un programa que INVIERTA UNA PALABRA usando una pila enlazada.

Ejemplo: "algoritmo" -> "omtirogla"

Por qué la política LIFO permite invertir el orden de los caracteres:
Si apilamos los caracteres de la palabra de izquierda a derecha, el último carácter leído
queda en el tope. Al desapilar, salen en orden inverso al que entraron (LIFO): primero el
último, después el anteúltimo, etc. Por eso, leer el tope repetidamente (pop) produce la
palabra al revés, sin necesidad de índices.

Estructura: pila enlazada de caracteres.

Operaciones: push de cada carácter, pop hasta vaciar, armar el string invertido.

Complejidad: O(n) temporal y O(n) espacial (se guardan n caracteres).

Casos especiales: cadena vacía (devuelve vacío), un solo carácter (igual), mayúsculas y
minúsculas, espacios (se invierten como cualquier carácter), palíndromos.

Prueba: "algoritmo", "reconocer" (palíndromo), "Hola Mundo", "a", "".

Ajustes realizados luego de la primera respuesta de OpenCode:
No requirió cambios de lógica. Se agregaron casos límite (vacío, un carácter, con espacios)
y se muestra además la versión StringBuilder solo a modo comparativo.
*/

/**
 * Ejercicio 8 - Invertir una palabra con pila.
 *
 * <p>Apila los caracteres y los desapila para obtener el orden inverso (LIFO). O(n).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio8 {

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
     * Invierte una palabra usando una pila. O(n).
     *
     * @param palabra cadena a invertir
     * @return la palabra invertida
     */
    static String invertir(String palabra) {
        PilaCaracteres pila = new PilaCaracteres();
        for (int i = 0; i < palabra.length(); i++) {
            pila.push(palabra.charAt(i));
        }

        StringBuilder resultado = new StringBuilder();
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }
        return resultado.toString();
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 8 - INVERTIR PALABRA CON PILA ==========");

        String[] palabras = {"algoritmo", "reconocer", "Hola Mundo", "a", ""};
        for (String p : palabras) {
            System.out.println("\"" + p + "\" -> \"" + invertir(p) + "\"");
        }

        System.out.println();
        System.out.println("LIFO: el último carácter apilado sale primero,");
        System.out.println("por eso el resultado queda en orden inverso.");
    }
}