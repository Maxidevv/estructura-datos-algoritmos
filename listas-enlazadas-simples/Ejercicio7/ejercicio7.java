/*
Prompt inicial utilizado:
Quiero implementar en Java el método:

  void modificar(int posicion, int nuevoDato)

en una lista enlazada simple de enteros. Debe cambiar el dato almacenado en la posición
indicada, validando la posición ANTES de recorrer.

Validación: si posicion < 0 o posicion >= size -> IndexOutOfBoundsException.

Diferencia entre modificar el DATO de un nodo y modificar la REFERENCIA al siguiente:
  - Modificar el dato: actual.dato = nuevoDato. Solo cambia el valor guardado dentro del
    nodo; la estructura de la lista (los enlaces) NO cambia. Es lo que pide este ejercicio.
  - Modificar la referencia: actual.siguiente = otroNodo. Esto cambia la ESTRUCTURA de la
    lista (a qué nodo apunta), y debe hacerse con cuidado para no perder nodos ni crear
    ciclos. Esa operación se usa al insertar o eliminar, no al "modificar un elemento".

Estructura de clases: Nodo y ListaEnlazada con head y size.

Casos a contemplar: modificar el primero, uno del medio, el último, y posiciones inválidas.

Prueba de ejecución: modificar distintas posiciones y mostrar la lista antes y después.

Ajustes realizados luego de la primera respuesta de OpenCode:
No fue necesario ajustar el prompt. La primera versión validaba la posición y luego
recorría nodo por nodo; se agregó únicamente un mensaje que informa el valor anterior
y el nuevo para facilitar la verificación.
*/

/**
 * Ejercicio 7 - Modificar un elemento de la lista.
 *
 * <p>Cambia el dato de un nodo en una posición determinada, validando la posición antes
 * de recorrer. Explica la diferencia entre modificar el dato de un nodo y modificar su
 * referencia al siguiente.</p>
 *
 * <p>Complejidad: O(n) temporal, O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio7 {

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
         * Modifica el dato de la posición indicada.
         *
         * @param posicion  índice 0-based del nodo a modificar
         * @param nuevoDato el nuevo valor
         * @throws IndexOutOfBoundsException si la posición es inválida
         */
        void modificar(int posicion, int nuevoDato) {
            // Validar ANTES de recorrer
            if (posicion < 0 || posicion >= size) {
                throw new IndexOutOfBoundsException(
                        "Posición inválida: " + posicion + " (size = " + size + ")");
            }

            Nodo actual = head;
            for (int i = 0; i < posicion; i++) {
                actual = actual.siguiente;
            }

            // Se modifica el DATO del nodo (no la referencia siguiente)
            int anterior = actual.dato;
            actual.dato = nuevoDato;
            System.out.println("   posicion " + posicion + ": " + anterior + " -> " + nuevoDato);
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
        System.out.println("========== EJERCICIO 7 - MODIFICAR UN ELEMENTO ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.imprimir();
        System.out.println();

        System.out.println("Modificando posiciones:");
        lista.modificar(0, 15);   // primero
        lista.modificar(2, 35);   // medio
        lista.modificar(3, 45);   // último
        lista.imprimir();

        System.out.println();
        try {
            lista.modificar(4, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("modificar(4, 0)  -> Error: " + e.getMessage());
        }
        try {
            lista.modificar(-1, 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("modificar(-1, 0) -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("DIFERENCIA CLAVE:");
        System.out.println("- Modificar el DATO: actual.dato = nuevoDato (no cambia los enlaces).");
        System.out.println("- Modificar la REFERENCIA: actual.siguiente = otro (cambia la estructura);");
        System.out.println("  se usa al insertar/eliminar, no para 'cambiar un elemento'.");
    }
}