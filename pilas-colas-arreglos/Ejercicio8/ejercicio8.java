/**
 * PROMPT UTILIZADO PARA GENERAR LA SOLUCIÓN
 * -----------------------------------------
 * Quiero implementar en Java un sistema de cola de impresión.
 *
 * Estructura necesaria: una cola (queue) de documentos. Cada documento tiene un nombre
 * y una cantidad de páginas.
 *
 * Operaciones que debe tener:
 *   - agregarDocumento(doc): el documento entra a la cola de impresión.
 *   - imprimirSiguiente(): imprime y retira el documento que está primero en la cola,
 *     mostrando su nombre y cantidad de páginas.
 *   - consultarSiguiente(): consulta qué documento está próximo a imprimirse sin retirarlo.
 *
 * Cómo una cola representa correctamente el orden de impresión:
 *   - Las impresoras reales atienden los trabajos en el orden en que fueron enviados:
 *     el primero en entrar a la cola es el primero en imprimirse (FIFO).
 *   - Si se usara una pila (LIFO), el último documento enviado se imprimiría primero,
 *     lo que arruinaría el orden de entrega esperado.
 *
 * Caso límite: si la cola está vacía, imprimir o consultar debe informar que no hay
 * documentos.
 */

/**
 * Ejercicio 8 - Cola de impresión.
 *
 * <p>Simula la cola de una impresora con documentos (nombre + páginas). Representa el
 * orden de impresión con una cola FIFO: el primer documento enviado es el primero en
 * imprimirse.</p>
 *
 * <p>Complejidad: todas las operaciones son O(1).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio8 {

    /**
     * Documento con nombre y cantidad de páginas.
     */
    static class Documento {
        String nombre;
        int paginas;

        Documento(String nombre, int paginas) {
            this.nombre = nombre;
            this.paginas = paginas;
        }

        @Override
        public String toString() {
            return nombre + " (" + paginas + " págs.)";
        }
    }

    /**
     * Cola simple de documentos de impresión.
     */
    static class ColaImpresion {
        private Documento[] docs;
        private int capacidad;
        private int front;
        private int rear;

        ColaImpresion(int capacidad) {
            this.capacidad = capacidad;
            this.docs = new Documento[capacidad];
            this.front = -1;
            this.rear = -1;
        }

        /** Agrega un documento al final de la cola de impresión. */
        void agregarDocumento(Documento doc) {
            if (rear == capacidad - 1) {
                throw new IllegalStateException("La cola de impresión está llena.");
            }
            if (rear == -1) {
                front = 0;
            }
            docs[++rear] = doc;
        }

        /** Imprime y retira el primer documento de la cola. */
        Documento imprimirSiguiente() {
            if (isEmpty()) {
                throw new IllegalStateException("No hay documentos en la cola de impresión.");
            }
            Documento doc = docs[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            return doc;
        }

        /** Consulta el documento próximo a imprimir sin retirarlo. */
        Documento consultarSiguiente() {
            if (isEmpty()) {
                throw new IllegalStateException("No hay documentos en la cola de impresión.");
            }
            return docs[front];
        }

        boolean isEmpty() {
            return front == -1;
        }

        int size() {
            if (isEmpty()) {
                return 0;
            }
            return rear - front + 1;
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 8 - COLA DE IMPRESIÓN ==========");

        ColaImpresion cola = new ColaImpresion(5);

        // Se envían documentos a imprimir
        cola.agregarDocumento(new Documento("Informe_Proyecto.pdf", 15));
        cola.agregarDocumento(new Documento("Factura_AB-123.pdf", 2));
        cola.agregarDocumento(new Documento("Manual_Usuario.docx", 40));
        System.out.println("Documentos en cola: " + cola.size());
        System.out.println("Próximo a imprimir: " + cola.consultarSiguiente());

        // Se imprimen en el orden en que fueron enviados (FIFO)
        System.out.println();
        System.out.println("Imprimiendo -> " + cola.imprimirSiguiente());
        System.out.println("Imprimiendo -> " + cola.imprimirSiguiente());
        System.out.println("Próximo a imprimir: " + cola.consultarSiguiente());

        // Caso límite: se termina la cola
        System.out.println();
        System.out.println("Imprimiendo -> " + cola.imprimirSiguiente());
        System.out.println("Cola vacía: " + cola.isEmpty());
        try {
            cola.imprimirSiguiente();
        } catch (IllegalStateException e) {
            System.out.println("imprimirSiguiente() -> Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("CÓMO REPRESENTA LA COLA EL ORDEN DE IMPRESIÓN:");
        System.out.println("- La impresora imprime en el mismo orden en que se enviaron (FIFO).");
        System.out.println("- El primer documento agregado es el primero en imprimirse.");
        System.out.println("- Usar una pila (LIFO) imprimiría primero el último enviado, lo que");
        System.out.println("  no refleja una impresora real.");
    }
}

// ---------------------------------------------------------------------------
// AJUSTE DEL PROMPT:
// No fue necesario modificar el prompt. La clase Documento se define como clase
// auxiliar del mismo archivo para cumplir con la consigna de un solo archivo Java.
// ---------------------------------------------------------------------------