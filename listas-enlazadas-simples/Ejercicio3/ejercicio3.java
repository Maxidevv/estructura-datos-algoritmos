/*
Prompt inicial utilizado:
Quiero implementar en Java el método:

  int obtener(int posicion)

sobre una lista enlazada simple de enteros. El método debe devolver el dato almacenado
en la posición indicada.

Validación de posiciones inválidas:
- Si posicion < 0 -> lanzar IndexOutOfBoundsException.
- Si posicion >= size -> lanzar IndexOutOfBoundsException.

Por qué hay que recorrer nodo por nodo aunque se use una posición:
Aunque la interfaz reciba una "posición", la lista enlazada NO tiene acceso directo como
un arreglo (donde arr[i] es O(1)). Aquí no existe una fórmula para llegar al nodo i:
solo se puede partir de head y avanzar con siguiente tantas veces como indique la
posición. Por eso, obtener(i) es O(i) y, en el peor caso, O(n).

Estructura de clases: Nodo y ListaEnlazada con head y size (para validar rápido).
El recorrido usa un contador desde 0 y se detiene cuando el contador llega a posicion.

Casos a contemplar: posición 0 (head), posición del medio, última posición y posiciones
inválidas (negativa y fuera de rango).

Prueba de ejecución: obtener varias posiciones válidas y probar los errores.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión solo validaba posicion < 0 y no posicion >= size, por lo que al pedir
una posición mayor al tamaño se producía NullPointerException en lugar de un error claro.
Se corrigió la validación usando size antes de recorrer.
*/

/**
 * Ejercicio 3 - Obtener un elemento por posición.
 *
 * <p>Devuelve el dato almacenado en una posición determinada, validando posiciones
 * inválidas y recorriendo la lista nodo por nodo (no hay acceso directo por índice).</p>
 *
 * <p>Complejidad: O(n) temporal, O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio3 {

    /** Nodo de la lista enlazada simple. */
    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /** Lista enlazada simple de enteros. */
    static class ListaEnlazada {
        Nodo head;
        int size;

        void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (head == null) {
                head = nuevo;
            } else {
                Nodo actual = head;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
            size++;
        }

        /**
         * Obtiene el dato de una posición determinada.
         *
         * @param posicion índice 0-based del dato a obtener
         * @return el dato en esa posición
         * @throws IndexOutOfBoundsException si la posición es negativa o >= size
         */
        int obtener(int posicion) {
            // Validación: la lista enlazada no tiene acceso directo, hay que validar antes
            if (posicion < 0 || posicion >= size) {
                throw new IndexOutOfBoundsException(
                        "Posición inválida: " + posicion + " (size = " + size + ")");
            }

            Nodo actual = head;
            int indice = 0;
            // Recorrido nodo por nodo hasta llegar a la posición pedida
            while (indice < posicion) {
                actual = actual.siguiente;
                indice++;
            }
            return actual.dato;
        }

        void imprimir() {
            System.out.print("Lista: ");
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 3 - OBTENER POR POSICIÓN ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.imprimir();
        System.out.println();

        System.out.println("obtener(0) [head]        -> " + lista.obtener(0));
        System.out.println("obtener(2) [medio]       -> " + lista.obtener(2));
        System.out.println("obtener(3) [última]      -> " + lista.obtener(3));

        System.out.println();
        try {
            lista.obtener(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("obtener(-1) -> Error: " + e.getMessage());
        }
        try {
            lista.obtener(4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("obtener(4)  -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("IMPORTANTE:");
        System.out.println("- Aunque se use una posición, NO hay acceso directo como en un arreglo.");
        System.out.println("- Hay que partir de head y avanzar con .siguiente 'posicion' veces.");
    }
}