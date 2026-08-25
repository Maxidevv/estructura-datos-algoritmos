/**
 * Ejercicio 1 - Búsqueda del valor mínimo en un vector.
 *
 * <p>Este programa implementa un algoritmo de búsqueda lineal para encontrar
 * el valor mínimo dentro de un arreglo de enteros. Se utiliza un recorrido
 * secuencial (linear scan) que compara cada elemento con el mínimo actual,
 * logrando eficiencia óptima sin necesidad de ordenar el arreglo.</p>
 *
 * <p>Complejidad algorítmica:</p>
 * <ul>
 *   <li>Temporal: O(n) - Se recorre el arreglo una sola vez.</li>
 *   <li>Espacial: O(1) - Solo se utiliza una variable adicional (min).</li>
 * </ul>
 *
 * <p>No es necesario ordenar porque un recorrido lineal es suficiente y óptimo.
 * Ordenar tendría una complejidad de O(n log n), lo cual es menos eficiente
 * que el enfoque de linear scan con O(n).</p>
 *
 * @author maxidev
 * @version 1.0
 */
class Ejercicio1 {

    /**
     * Encuentra el valor mínimo en un arreglo de enteros mediante un recorrido lineal.
     *
     * <p>El algoritmo inicializa el mínimo con el primer elemento del arreglo
     * y recorre los elementos restantes, actualizando el mínimo cada vez que
     * encuentra un valor menor.</p>
     *
     * @param array el arreglo de enteros en el que se buscará el mínimo
     * @return el valor mínimo encontrado en el arreglo
     * @throws IllegalArgumentException si el arreglo es null o está vacío
     */
    static int findMinimum(int[] array) {
        // Validación defensiva: verificar que el arreglo no sea nulo ni esté vacío
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser nulo o estar vacío.");
        }

        // Inicializar el mínimo con el primer elemento del arreglo
        int min = array[0];

        // Recorrer el arreglo desde el segundo elemento hasta el final
        for (int i = 1; i < array.length; i++) {
            // Si el elemento actual es menor que el mínimo registrado, actualizar
            if (array[i] < min) {
                min = array[i];
            }
        }

        // Retornar el valor mínimo encontrado
        return min;
    }

    /**
     * Método principal - Punto de entrada del programa.
     *
     * <p>Declara un vector de ejemplo, valida los datos, ejecuta la búsqueda
     * del mínimo e imprime el resultado en el formato especificado.</p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Declarar el vector de ejemplo
        int[] vector = {3, 7, 1, 9, 4};

        // Imprimir el vector original
        System.out.print("Vector: [");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
            if (i < vector.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        // Buscar e imprimir el valor mínimo
        int valorMinimo = findMinimum(vector);
        System.out.println("El valor mínimo es: " + valorMinimo);

        // Documentación de complejidad algorítmica
        System.out.println();
        System.out.println("COMPLEJIDAD ALGORÍTMICA:");
        System.out.println("- Temporal: O(n)");
        System.out.println("- Espacial: O(1)");
        System.out.println("- No es necesario ordenar porque un recorrido lineal es suficiente y óptimo.");
    }
}
