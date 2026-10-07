/*
Prompt inicial utilizado:
Quiero implementar en Java el método:

  int contarOcurrencias(int dato)

en una lista enlazada simple de enteros. Debe recorrer TODA la lista y contar cuántas
veces aparece un valor.

Ejemplo:
  10 -> 20 -> 10 -> 30 -> 10 -> null
  contarOcurrencias(10) debe devolver 3.

Por qué NO se debe cortar el recorrido al encontrar la primera coincidencia:
A diferencia de una búsqueda (buscar), que puede detenerse al primer match, aquí el
objetivo es conocer la CANTIDAD total de apariciones. Si se cortara al primer 10, se
devolvería 1 y se perderían las demás ocurrencias. Por eso el contador se incrementa
cada vez que se encuentra el dato, pero el recorrido continúa hasta actual == null.

Estructura de clases: Nodo y ListaEnlazada con head y size.

Casos a contemplar: valor repetido, valor que aparece una sola vez, valor inexistente
(devuelve 0) y lista vacía (devuelve 0).

Prueba de ejecución: contar un valor repetido, uno único y uno inexistente.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión devolvía el resultado dentro del while (cortaba al primer match),
comportándose como una búsqueda. Se corrigió para que incremente un contador y continúe
hasta el final de la lista, tal como exige el enunciado.
*/

/**
 * Ejercicio 8 - Contar ocurrencias de un valor.
 *
 * <p>Recorre toda la lista enlazada simple contando cuántas veces aparece un valor.
 * Explica por qué el recorrido no debe cortarse al encontrar la primera coincidencia.</p>
 *
 * <p>Complejidad: O(n) temporal, O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio8 {

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
         * Cuenta cuántas veces aparece un dato en toda la lista.
         *
         * @param dato el valor a contar
         * @return la cantidad de apariciones (0 si no aparece)
         */
        int contarOcurrencias(int dato) {
            int contador = 0;
            Nodo actual = head;

            // Se recorre TODA la lista; no se corta aunque se encuentre el dato.
            while (actual != null) {
                if (actual.dato == dato) {
                    contador++; // se cuenta, pero se sigue avanzando
                }
                actual = actual.siguiente;
            }
            return contador;
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
        System.out.println("========== EJERCICIO 8 - CONTAR OCURRENCIAS ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(10);
        lista.imprimir();

        System.out.println();
        System.out.println("contarOcurrencias(10) [repetido]    -> " + lista.contarOcurrencias(10));
        System.out.println("contarOcurrencias(20) [único]       -> " + lista.contarOcurrencias(20));
        System.out.println("contarOcurrencias(99) [inexistente] -> " + lista.contarOcurrencias(99));

        ListaEnlazada vacia = new ListaEnlazada();
        System.out.println("contarOcurrencias(1) en vacía       -> " + vacia.contarOcurrencias(1));

        System.out.println();
        System.out.println("POR QUÉ NO CORTAR EN LA PRIMERA COINCIDENCIA:");
        System.out.println("- El objetivo es la cantidad total de apariciones, no saber si existe.");
        System.out.println("- Se incrementa un contador y se sigue recorriendo hasta actual == null.");
        System.out.println("- Cortar al primer match devolvería 1 e ignoraría las demás apariciones.");
    }
}