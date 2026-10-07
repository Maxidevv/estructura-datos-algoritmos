/*
Prompt inicial utilizado:
Quiero tomar la implementación de lista enlazada simple con int y transformarla en una
versión genérica:

  ListaEnlazada<T>
  Nodo<T>

Debe funcionar con distintos tipos de datos, por ejemplo:
  ListaEnlazada<Integer>
  ListaEnlazada<String>
  ListaEnlazada<Alumno>

Qué partes del código CAMBIAN:
  - El tipo del dato en Nodo pasa de int a T.
  - La clase ListaEnlazada pasa a ser ListaEnlazada<T>.
  - Los métodos que reciben/devuelven el dato usan T (insertarAlInicio(T), buscar(T),
    eliminar(T), etc.).
  - Las comparaciones dejan de usar == (que compara referencias) y pasan a usar
    equals(), porque T es un objeto. Ojo: equals() requiere que el tipo concreto lo
    implemente correctamente (String ya lo hace; una clase Alumno debe sobrescribirlo).

Qué partes se MANTIENEN IGUALES:
  - La estructura de nodos y enlaces (nodo.siguiente).
  - head y size.
  - El algoritmo de recorrido: actual = head; while (actual != null) actual = actual.siguiente.
  - La inserción y eliminación: solo cambia el tipo del dato, no la actualización de
    referencias (nuevo.siguiente = head, anterior.siguiente = actual.siguiente, etc.).

Por qué el algoritmo no depende del tipo de dato almacenado:
Las listas enlazadas solo manipulan REFERENCIAS a objetos (enlaces entre nodos). No les
importa qué hay dentro del nodo: el recorrido, la inserción y la eliminación operan sobre
los enlaces, no sobre el valor. Por eso el mismo algoritmo sirve para Integer, String,
Alumno o cualquier T, y los genéricos permiten reutilizar UNA sola clase con seguridad de
tipos en compilación.

Estructura de clases: Nodo<T> {T dato; Nodo<T> siguiente;} y ListaEnlazada<T> {Nodo<T> head;
int size;}.

Prueba de ejecución: crear una lista de Integer, una de String y una de Alumno, insertar,
buscar y eliminar en cada una.

Ajustes realizados luego de la primera respuesta de OpenCode:
La primera versión comparaba con ==, lo que en String y Alumno fallaba (compara
referencias, no contenido). Se cambió a equals() con un chequeo de null, y se sobrescribió
equals()/hashCode() en Alumno para que la búsqueda y eliminación funcionen por valor.
*/

/**
 * Ejercicio 10 - Convertir la lista de enteros en una lista genérica.
 *
 * <p>Reimplementa la lista enlazada simple como ListaEnlazada&lt;T&gt; con Nodo&lt;T&gt;,
 * funcionando con Integer, String y objetos como Alumno. Explica qué cambia (tipo del
 * dato y uso de equals) y qué se mantiene (enlaces, head y algoritmos de recorrido,
 * inserción y eliminación).</p>
 *
 * <p>Complejidad: las mismas que la versión con int (O(1) al inicio, O(n) al final y en
 * búsquedas).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio10 {

    /**
     * Nodo genérico de la lista enlazada simple.
     *
     * @param <T> tipo del dato almacenado
     */
    static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /**
     * Lista enlazada simple genérica.
     *
     * @param <T> tipo de los elementos almacenados
     */
    static class ListaEnlazada<T> {
        private Nodo<T> head;
        private int size;

        /** Inserta al inicio (lógica idéntica, solo cambia el tipo). */
        void insertarAlInicio(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            nuevo.siguiente = head;
            head = nuevo;
            size++;
        }

        /** Inserta al final. */
        void insertarAlFinal(T dato) {
            Nodo<T> nuevo = new Nodo<>(dato);
            if (head == null) {
                head = nuevo;
            } else {
                Nodo<T> actual = head;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
            size++;
        }

        /** Busca un elemento por valor (usa equals, no ==). */
        boolean buscar(T dato) {
            Nodo<T> actual = head;
            while (actual != null) {
                if (iguales(actual.dato, dato)) {
                    return true;
                }
                actual = actual.siguiente;
            }
            return false;
        }

        /** Elimina la primera aparición de un elemento (usa equals). */
        boolean eliminar(T dato) {
            if (head == null) {
                return false;
            }
            if (iguales(head.dato, dato)) {
                head = head.siguiente;
                size--;
                return true;
            }
            Nodo<T> anterior = head;
            Nodo<T> actual = head.siguiente;
            while (actual != null) {
                if (iguales(actual.dato, dato)) {
                    anterior.siguiente = actual.siguiente;
                    size--;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
        }

        /** Comparación segura con equals para tipos genéricos. */
        private boolean iguales(T a, T b) {
            if (a == null) {
                return b == null;
            }
            return a.equals(b);
        }

        int getSize() {
            return size;
        }

        void imprimir() {
            System.out.print("Lista: ");
            Nodo<T> actual = head;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }
    }

    /**
     * Clase de ejemplo para probar la lista genérica con objetos.
     */
    static class Alumno {
        String nombre;
        int legajo;

        Alumno(String nombre, int legajo) {
            this.nombre = nombre;
            this.legajo = legajo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Alumno alumno = (Alumno) o;
            return legajo == alumno.legajo && nombre.equals(alumno.nombre);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(nombre, legajo);
        }

        @Override
        public String toString() {
            return nombre + "(" + legajo + ")";
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 10 - LISTA GENÉRICA ==========");

        // ---------- Lista<Integer> ----------
        ListaEnlazada<Integer> enteros = new ListaEnlazada<>();
        enteros.insertarAlInicio(5);
        enteros.insertarAlFinal(10);
        enteros.insertarAlFinal(20);
        System.out.println("\n[Integer]");
        enteros.imprimir();
        System.out.println("buscar(10): " + enteros.buscar(10)
                + " | eliminar(5): " + enteros.eliminar(5) + " | size: " + enteros.getSize());
        enteros.imprimir();

        // ---------- Lista<String> ----------
        ListaEnlazada<String> strings = new ListaEnlazada<>();
        strings.insertarAlFinal("hola");
        strings.insertarAlInicio("mundo");
        strings.insertarAlFinal("!");
        System.out.println("\n[String]");
        strings.imprimir();
        System.out.println("buscar(\"hola\"): " + strings.buscar("hola")
                + " | eliminar(\"mundo\"): " + strings.eliminar("mundo")
                + " | size: " + strings.getSize());
        strings.imprimir();

        // ---------- Lista<Alumno> ----------
        ListaEnlazada<Alumno> alumnos = new ListaEnlazada<>();
        alumnos.insertarAlFinal(new Alumno("Ana", 1001));
        alumnos.insertarAlFinal(new Alumno("Pedro", 1002));
        alumnos.insertarAlFinal(new Alumno("Lucia", 1500));
        System.out.println("\n[Alumno]");
        alumnos.imprimir();
        Alumno buscado = new Alumno("Pedro", 1002);
        System.out.println("buscar(Pedro,1002): " + alumnos.buscar(buscado)
                + " | eliminar(Ana,1001): " + alumnos.eliminar(new Alumno("Ana", 1001))
                + " | size: " + alumnos.getSize());
        alumnos.imprimir();

        System.out.println();
        System.out.println("QUÉ CAMBIA Y QUÉ SE MANTIENE:");
        System.out.println("- Cambia: el tipo del dato (int -> T) y las comparaciones (== -> equals).");
        System.out.println("- Se mantiene: enlaces, head, size y los algoritmos de recorrido,");
        System.out.println("  inserción y eliminación (operan sobre referencias, no sobre el dato).");
    }
}