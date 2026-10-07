/*
Prompt inicial utilizado:
Quiero implementar una COLA DE ATENCIÓN para un banco usando una cola enlazada.

Cada cliente debe tener: nombre, número de turno y motivo de consulta.

El sistema debe permitir:
  - agregar cliente a la fila;
  - atender al próximo cliente;
  - consultar quién sigue;
  - imprimir la fila actual.

Por qué la estructura adecuada es una cola y cómo se representa el orden de llegada:
En un banco se atiende por orden de llegada: el primero que llega es el primero en ser
atendido (FIFO). Una cola modela exactamente eso: se encola por el final (tail) y se
desencola por el frente (head). El orden de llegada queda representado por la posición en
la fila: el head es el próximo a atender y cada nuevo cliente se agrega al final; nadie se
"cuela".

Estructura: cola enlazada genérica de Cliente con head (próximo) y tail (último en llegar).

Operaciones: agregarCliente (O(1)), atenderProximo (O(1)), quienSigue (O(1)),
imprimirFila (O(n)).

Casos especiales: atender cuando no hay nadie; consultar quién sigue en fila vacía;
numeración de turnos creciente.

Prueba de ejecución: agregar clientes, consultar quién sigue, atender a varios y forzar el
caso de fila vacía.

Ajustes realizados luego de la primera respuesta de OpenCode:
Se agregó el manejo de fila vacía en atender/siguiente para no lanzar NullPointerException,
y un contador de turnos que se asigna automáticamente.
*/

/**
 * Ejercicio 6 - Cola de atención de clientes.
 *
 * <p>Cola FIFO de Cliente (nombre, turno, motivo) que respeta el orden de llegada.</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio6 {

    /** Cliente de la fila del banco. */
    static class Cliente {
        String nombre;
        int turno;
        String motivo;

        Cliente(String nombre, int turno, String motivo) {
            this.nombre = nombre;
            this.turno = turno;
            this.motivo = motivo;
        }

        @Override
        public String toString() {
            return "Turno " + turno + " - " + nombre + " (" + motivo + ")";
        }
    }

    /** Nodo de la cola. */
    static class Nodo {
        Cliente dato;
        Nodo siguiente;

        Nodo(Cliente dato) {
            this.dato = dato;
        }
    }

    /** Fila de atención FIFO. */
    static class FilaBanco {
        private Nodo head; // próximo a atender
        private Nodo tail; // último en llegar
        private int contadorTurnos;

        /** Agrega un cliente al final de la fila. O(1). */
        void agregarCliente(String nombre, String motivo) {
            Cliente cliente = new Cliente(nombre, ++contadorTurnos, motivo);
            Nodo nuevo = new Nodo(cliente);
            if (head == null) {
                head = nuevo;
                tail = nuevo;
            } else {
                tail.siguiente = nuevo;
                tail = nuevo;
            }
            System.out.println("Ingresó a la fila: " + cliente);
        }

        /** Atiende (saca) al próximo cliente. O(1). */
        Cliente atenderProximo() {
            if (head == null) {
                System.out.println("No hay clientes en la fila.");
                return null;
            }
            Cliente cliente = head.dato;
            head = head.siguiente;
            if (head == null) {
                tail = null;
            }
            System.out.println("Atendiendo: " + cliente);
            return cliente;
        }

        /** Consulta quién sigue sin atenderlo. O(1). */
        Cliente quienSigue() {
            if (head == null) {
                System.out.println("Fila vacía: no hay próximo cliente.");
                return null;
            }
            return head.dato;
        }

        /** Imprime la fila del próximo al último. O(n). */
        void imprimirFila() {
            System.out.println("Fila actual:");
            Nodo actual = head;
            if (actual == null) {
                System.out.println("  (vacía)");
            }
            while (actual != null) {
                System.out.println("  " + actual.dato);
                actual = actual.siguiente;
            }
        }
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========== EJERCICIO 6 - COLA DE ATENCIÓN ==========");

        FilaBanco fila = new FilaBanco();

        fila.agregarCliente("Ana", "Depósito");
        fila.agregarCliente("Luis", "Extracción");
        fila.agregarCliente("Sofia", "Préstamo");
        fila.imprimirFila();

        System.out.println("\nQuién sigue: " + fila.quienSigue());
        System.out.println();
        fila.atenderProximo();
        fila.atenderProximo();
        fila.imprimirFila();

        System.out.println();
        fila.atenderProximo();
        fila.atenderProximo(); // fila vacía

        System.out.println("\nQuién sigue ahora: " + fila.quienSigue());

        System.out.println("\nFIFO: el orden de llegada (turnos) coincide con el orden de atención.");
    }
}