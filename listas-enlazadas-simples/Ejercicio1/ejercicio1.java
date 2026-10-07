/*
Prompt inicial utilizado:
Quiero implementar en Java una lista enlazada simple de números enteros, SIN usar
estructuras ya provistas por Java como ArrayList, LinkedList o similares.

Estructura de clases:
- Clase Nodo: representa un nodo de la lista. Tiene dos atributos: un int "dato" y una
  referencia "siguiente" al próximo nodo. Cuando es el último nodo, siguiente == null.
- Clase ListaEnlazada: tiene un atributo "head" que apunta al primer nodo (o null si la
  lista está vacía) y un atributo "size" con la cantidad de nodos.

Papel de head: head es el único punto de entrada a la lista. Como los nodos solo se
conocen a través de sus enlaces, si se pierde head se pierde toda la lista.

Operaciones requeridas:
  - insertarAlInicio(int dato): crea un nodo, su siguiente apunta al viejo head y head
    pasa a ser el nuevo nodo. Se incrementa size.
  - insertarAlFinal(int dato): si la lista está vacía, head pasa a ser el nuevo nodo.
    Si no, se recorre hasta el último nodo y se enlaza el nuevo. Se incrementa size.
  - imprimir(): recorre desde head imprimiendo cada dato hasta llegar a null.
  - estaVacia(): devuelve head == null.
  - getSize(): devuelve size.

Actualización de size: cada inserción incrementa size en 1; así getSize() es O(1) sin
necesidad de recorrer la lista.

Casos a contemplar: lista vacía al insertar, insertar al final de una lista vacía, e
imprimir una lista vacía.

Prueba de ejecución: insertar algunos al inicio y al final, imprimir y consultar size.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión no contemplaba el caso de insertarAlFinal sobre una lista vacía
(assumía que head != null y lanzaba NullPointerException). Se corrigió agregando la
verificación estaVacia() dentro de insertarAlFinal. También se agregó que imprimir()
muestre "10 -> 20 -> null" para visualizar mejor los enlaces.
*/

/**
 * Ejercicio 1 - Crear una lista enlazada simple desde cero.
 *
 * <p>Implementa una lista enlazada simple de enteros con nodos y head, sin usar
 * estructuras de Java. Permite insertar al inicio, insertar al final, imprimir,
 * consultar si está vacía y obtener su tamaño.</p>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>insertarAlInicio: O(1).</li>
 *   <li>insertarAlFinal: O(n) sin referencia al último nodo.</li>
 *   <li>imprimir y getSize: O(n) la primera, O(1) la segunda (size almacenado).</li>
 * </ul>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio1 {

    /**
     * Nodo de la lista enlazada simple.
     */
    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /**
     * Lista enlazada simple de enteros.
     */
    static class ListaEnlazada {
        private Nodo head; // primer nodo de la lista; null si está vacía
        private int size;  // cantidad de nodos

        ListaEnlazada() {
            this.head = null;
            this.size = 0;
        }

        /** Inserta un nodo al inicio de la lista. */
        void insertarAlInicio(int dato) {
            Nodo nuevo = new Nodo(dato);
            nuevo.siguiente = head; // el nuevo apunta al antiguo primero
            head = nuevo;           // head pasa a ser el nuevo nodo
            size++;
        }

        /** Inserta un nodo al final de la lista. */
        void insertarAlFinal(int dato) {
            Nodo nuevo = new Nodo(dato);

            if (estaVacia()) {
                head = nuevo; // lista vacía: el nuevo es el primero
            } else {
                Nodo actual = head;
                // Recorrido secuencial hasta el último nodo (el que apunta a null)
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo; // se enlaza el nuevo al final
            }
            size++;
        }

        /** Recorre la lista e imprime los datos en forma "a -> b -> null". */
        void imprimir() {
            System.out.print("Lista: ");
            Nodo actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }

        /** @return true si la lista no tiene nodos. */
        boolean estaVacia() {
            return head == null;
        }

        /** @return la cantidad de nodos. */
        int getSize() {
            return size;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 1 - LISTA ENLAZADA SIMPLE ==========");

        ListaEnlazada lista = new ListaEnlazada();
        System.out.println("Lista recién creada -> estaVacia: " + lista.estaVacia()
                + " | getSize: " + lista.getSize());
        lista.imprimir();

        // Insertar al final
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        // Insertar al inicio
        lista.insertarAlInicio(10);
        // Insertar al final sobre lista no vacía
        lista.insertarAlFinal(40);

        System.out.println();
        System.out.println("Tras insertar 10 al inicio y 20, 30, 40 al final:");
        lista.imprimir();
        System.out.println("getSize: " + lista.getSize() + " | estaVacia: " + lista.estaVacia());

        System.out.println();
        System.out.println("CÓMO SE REPRESENTA:");
        System.out.println("- Nodo: {dato, siguiente}. El último tiene siguiente = null.");
        System.out.println("- head apunta al primer nodo; si es null, la lista está vacía.");
        System.out.println("- size se incrementa en cada inserción para que getSize() sea O(1).");
    }
}