/*
Prompt inicial utilizado:
Quiero implementar en Java el método:

  void insertarEnPosicion(int dato, int posicion)

en una lista enlazada simple de enteros. Debe permitir insertar al inicio, en el medio y
al final. Debe validar posiciones inválidas.

Reglas:
  - Si posicion == 0 -> insertar al inicio (caso particular: cambia head).
  - Si posicion == size -> insertar al final (caso válido: después del último nodo).
  - Si 0 < posicion < size -> insertar en el medio.
  - Si posicion < 0 o posicion > size -> posición inválida.

Orden correcto de actualización de referencias (clave):
Para insertar un nodo nuevo entre un nodo "actual" y su siguiente, el orden debe ser:

  nuevo.setSiguiente(actual.getSiguiente());  // 1) el nuevo se engancha al resto
  actual.setSiguiente(nuevo);                 // 2) el actual apunta al nuevo

Es decir: PRIMERO se enlaza el nuevo al sucesor, y DESPUÉS el anterior al nuevo.

Qué pasaría si se invierte el orden:
Si se hace primero actual.setSiguiente(nuevo), se pierde la referencia al resto de la
lista (el antiguo siguiente queda inaccesible). Luego nuevo.setSiguiente(...) ya no
podría apuntar a ese nodo perdido, y la lista quedaría truncada o con el nodo apuntándose
a sí mismo. Por eso el orden es fundamental.

Estructura de clases: Nodo y ListaEnlazada con head y size.

Casos a contemplar: insertar en lista vacía, al inicio, en el medio, al final, y
posiciones inválidas.

Prueba de ejecución: insertar en cada caso y mostrar la lista antes y después.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión usaba posicion > size - 1 para insertar al final, por lo que no
permitía insertar en la posición size (el final) y lanzaba error para un caso válido. Se
corrigió a posicion > size.
*/

/**
 * Ejercicio 4 - Insertar un nodo en una posición específica.
 *
 * <p>Inserta un dato en la posición indicada, contemplando inicio, medio y final, y
 * valida posiciones inválidas. Explica el orden correcto de actualización de referencias.</p>
 *
 * <p>Complejidad: O(n) temporal (recorrido hasta la posición), O(1) espacial.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio4 {

    /** Nodo de la lista enlazada simple. */
    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }

        void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }

        Nodo getSiguiente() {
            return siguiente;
        }
    }

    /** Lista enlazada simple de enteros. */
    static class ListaEnlazada {
        Nodo head;
        int size;

        /**
         * Inserta un dato en la posición indicada.
         *
         * @param dato     el valor a insertar
         * @param posicion índice 0-based donde insertar (0 = inicio, size = final)
         * @throws IndexOutOfBoundsException si posicion < 0 o posicion > size
         */
        void insertarEnPosicion(int dato, int posicion) {
            if (posicion < 0 || posicion > size) {
                throw new IndexOutOfBoundsException(
                        "Posición inválida: " + posicion + " (size = " + size + ")");
            }

            Nodo nuevo = new Nodo(dato);

            // Caso 1: insertar al inicio (incluye lista vacía)
            if (posicion == 0) {
                nuevo.setSiguiente(head);
                head = nuevo;
                size++;
                return;
            }

            // Caso 2 y 3: insertar en el medio o al final.
            // Se busca el nodo ANTERIOR a la posición de inserción.
            Nodo anterior = head;
            for (int i = 0; i < posicion - 1; i++) {
                anterior = anterior.siguiente;
            }

            // ORDEN CORRECTO: primero el nuevo se engancha al sucesor,
            // luego el anterior apunta al nuevo.
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
            size++;
        }

        void insertarAlFinal(int dato) {
            insertarEnPosicion(dato, size);
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
        System.out.println("========== EJERCICIO 4 - INSERTAR EN POSICIÓN ==========");

        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarEnPosicion(20, 0); // lista vacía -> inicio
        lista.insertarEnPosicion(40, 1); // final
        lista.insertarEnPosicion(10, 0); // inicio
        lista.insertarEnPosicion(30, 2); // medio
        System.out.println("Tras: (20,0) (40,1) (10,0) (30,2):");
        lista.imprimir();
        System.out.println("getSize: " + lista.size);

        System.out.println();
        try {
            lista.insertarEnPosicion(99, -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("insertar(99,-1) -> Error: " + e.getMessage());
        }
        try {
            lista.insertarEnPosicion(99, 5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("insertar(99, 5) -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("ORDEN CORRECTO DE REFERENCIAS:");
        System.out.println("1) nuevo.setSiguiente(actual.getSiguiente());");
        System.out.println("2) actual.setSiguiente(nuevo);");
        System.out.println("Si se invierte, se pierde el resto de la lista (el antiguo");
        System.out.println("siguiente queda inaccesible) y la lista se trunca.");
    }
}