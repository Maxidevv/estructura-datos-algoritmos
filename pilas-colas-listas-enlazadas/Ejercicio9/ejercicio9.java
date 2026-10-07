/*
Prompt inicial utilizado:
Quiero implementar una COLA DE IMPRESIÓN usando una cola enlazada.

Cada trabajo de impresión debe tener: nombre del archivo, cantidad de páginas y usuario que
lo envió.

El sistema debe permitir:
  - agregar trabajos;
  - imprimir el próximo trabajo;
  - consultar el próximo archivo;
  - mostrar todos los trabajos pendientes.

Por qué una impresora debe procesar los trabajos usando FIFO:
Una impresora es un recurso compartido y único. Si se atendiera por LIFO (pila), el último
trabajo enviado se imprimiría primero y los más antiguos podrían quedar postergados
indefinidamente. El criterio justo y predecible es por orden de llegada: el primer trabajo
enviado es el primero en imprimirse (FIFO). Una cola modela ese orden: se encola por el
final (tail) y se desencola por el frente (head).

Estructura: cola enlazada genérica de Trabajo con head (próximo) y tail (último).

Operaciones: agregarTrabajo (O(1)), imprimirProximo (O(1)), proximoArchivo (O(1)),
mostrarPendientes (O(n)).

Casos especiales: imprimir/consultar con la cola vacía; trabajos de 0 páginas; actualizar
tail = null al vaciarse.

Prueba: agregar varios trabajos, consultar el próximo, imprimir a todos y forzar cola vacía.

Ajustes realizados luego de la primera respuesta de OpenCode:
Se agregó el manejo de cola vacía en imprimir próximo/consultar próximo y se mostró el
total de páginas pendientes como dato extra.
*/

/**
 * Ejercicio 9 - Cola de impresión.
 *
 * <p>Cola FIFO de Trabajo (archivo, páginas, usuario); el primero en enviarse es el primero
 * en imprimirse.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio9 {

    /** Trabajo de impresión. */
    static class Trabajo {
        String archivo;
        int paginas;
        String usuario;

        Trabajo(String archivo, int paginas, String usuario) {
            this.archivo = archivo;
            this.paginas = paginas;
            this.usuario = usuario;
        }

        @Override
        public String toString() {
            return archivo + " (" + paginas + " págs, " + usuario + ")";
        }
    }

    /** Nodo de la cola. */
    static class Nodo {
        Trabajo dato;
        Nodo siguiente;

        Nodo(Trabajo dato) {
            this.dato = dato;
        }
    }

    /** Cola de impresión FIFO. */
    static class ColaImpresion {
        private Nodo head; // próximo a imprimir
        private Nodo tail; // último en llegar

        /** Agrega un trabajo al final. O(1). */
        void agregarTrabajo(String archivo, int paginas, String usuario) {
            Trabajo trabajo = new Trabajo(archivo, paginas, usuario);
            Nodo nuevo = new Nodo(trabajo);
            if (head == null) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.siguiente = nuevo;
                tail = nuevo;
            }
            System.out.println("Encolado: " + trabajo);
        }

        /** Imprime (saca) el próximo trabajo. O(1). */
        Trabajo imprimirProximo() {
            if (head == null) {
                System.out.println("No hay trabajos pendientes.");
                return null;
            }
            Trabajo trabajo = head.dato;
            head = head.siguiente;
            if (head == null) {
                tail = null;
            }
            System.out.println("Imprimiendo: " + trabajo);
            return trabajo;
        }

        /** Consulta el próximo archivo sin imprimirlo. O(1). */
        String proximoArchivo() {
            if (head == null) {
                System.out.println("Cola de impresión vacía.");
                return null;
            }
            return head.dato.archivo;
        }

        /** Muestra todos los trabajos pendientes. O(n). */
        void mostrarPendientes() {
            System.out.println("Trabajos pendientes:");
            Nodo actual = head;
            if (actual == null) {
                System.out.println("  (ninguno)");
            }
            int total = 0;
            while (actual != null) {
                System.out.println("  " + actual.dato);
                total += actual.dato.paginas;
                actual = actual.siguiente;
            }
            if (head != null) {
                System.out.println("  Total de páginas pendientes: " + total);
            }
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 9 - COLA DE IMPRESIÓN ==========");

        ColaImpresion cola = new ColaImpresion();

        cola.agregarTrabajo("informe.pdf", 12, "ana");
        cola.agregarTrabajo("foto.png", 1, "luis");
        cola.agregarTrabajo("tesis.docx", 80, "sofia");
        cola.mostrarPendientes();

        System.out.println("\nPróximo archivo: " + cola.proximoArchivo());
        System.out.println();
        cola.imprimirProximo();
        cola.imprimirProximo();
        cola.mostrarPendientes();

        System.out.println();
        cola.imprimirProximo();
        cola.imprimirProximo(); // cola vacía
        System.out.println("Próximo archivo: " + cola.proximoArchivo());

        System.out.println("\nFIFO: se respeta el orden de envío, sin postergar trabajos antiguos.");
    }
}