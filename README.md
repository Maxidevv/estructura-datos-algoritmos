# Estructura de Datos y Algoritmos

Colección de ejercicios académicos de **Estructura de Datos y Algoritmos**, resueltos en **Java** y desarrollados con asistencia de [OpenCode](https://opencode.ai). Cada práctico propone una serie de ejercicios que se resuelven escribiendo primero un *prompt* que explique el razonamiento (estructura a usar, operaciones, casos límite y complejidad) y luego implementando, probando y ajustando el código.

> **Autor:** Maxidevv
> **Lenguaje:** Java (JDK 17+)
> **Herramientas:** OpenCode, `javac` / `java`

## Metodología de trabajo

Para cada ejercicio:

1. Se escribe un **prompt** describiendo el problema, la estructura adecuada, las operaciones, los casos especiales y la complejidad esperada.
2. OpenCode genera la implementación en Java.
3. Se **prueba** el resultado y se ajusta el prompt/código si hace falta.
4. Cada archivo `.java` incluye:
   - un comentario inicial con el **prompt utilizado**;
   - una nota de **ajustes realizados** luego de la primera respuesta;
   - una **prueba de ejecución** con datos de ejemplo en `main`.

## Estructura del repositorio

```
estructura-datos-algoritmos/
├── analisis-algoritmos/            # Análisis de algoritmos (búsqueda, matrices, etc.)
├── recursividad/                   # Funciones recursivas
├── ordenamiento/                   # Algoritmos de ordenamiento
├── pilas-colas-arreglos/           # Pilas y colas con arreglos
├── listas-enlazadas-simples/       # Listas enlazadas simples
└── pilas-colas-listas-enlazadas/   # Pilas, colas y listas doblemente enlazadas
```

Cada módulo tiene la forma:

```
modulo/
├── Ejercicios.docx.pdf
├── Ejercicio1/ejercicio1.java
├── Ejercicio2/ejercicio2.java
└── ...
```

Cada práctico se desarrolla en su propia rama (`feature/<modulo>`).

## Módulos y ejercicios

### 1. Análisis de algoritmos
Estrategias de búsqueda, recorrido de vectores/matrices y análisis de complejidad.

| # | Ejercicio |
|---|-----------|
| 1 | Encontrar el mínimo de un vector |
| 2 | Búsqueda de un elemento en un vector desordenado |
| 3 | Búsqueda binaria |
| 4 | Detección de duplicados (dos ciclos vs. `HashSet`) |
| 5 | Contar ocurrencias de un valor |
| 6 | Determinar si dos vectores son iguales |
| 7 | Invertir un vector (auxiliar vs. in-place) |
| 8 | Suma de los elementos de una matriz |
| 9 | Mayor elemento de una matriz |
| 10 | Bubble Sort (didáctico) |

### 2. Recursividad
Funciones recursivas con identificación de caso base y caso recursivo.

| # | Ejercicio |
|---|-----------|
| 1 | Factorial |
| 2 | Suma de los primeros N números |
| 3 | Multiplicación mediante sumas |
| 4 | Potencia |
| 5 | Conteo regresivo |
| 6 | Contar dígitos |
| 7 | Sumar dígitos |
| 8 | Invertir una palabra |
| 9 | Palíndromo |
| 10 | Buscar un elemento en un arreglo |

### 3. Ordenamiento
Algoritmos de ordenamiento simples y eficientes, con conteo de comparaciones e intercambios.

| # | Ejercicio |
|---|-----------|
| 1 | Ordenamiento Burbuja paso a paso |
| 2 | Comparación entre Burbuja y Selección |
| 3 | Ordenamiento por Inserción con arreglo casi ordenado |
| 4 | Ordenamiento de nombres con Selection Sort |
| 5 | ShellSort explicando los gaps |
| 6 | Quicksort con primer elemento como pivote |
| 7 | Peor caso de Quicksort |
| 8 | MergeSort paso a paso |
| 9 | Comparación de algoritmos simples |
| 10 | Sistema de ranking de puntajes |

### 4. Pilas y colas con arreglos
Estructuras LIFO/FIFO implementadas sobre arreglos de tamaño fijo.

| # | Ejercicio |
|---|-----------|
| 1 | Pila de enteros (`push`, `pop`, `peek`, `isEmpty`, `isFull`, `size`) |
| 2 | Simulador de torre de platos |
| 3 | Validar paréntesis balanceados |
| 4 | Historial de navegación |
| 5 | Pila genérica `Pila<T>` |
| 6 | Cola simple de enteros |
| 7 | Sistema de turnos para atención |
| 8 | Cola de impresión |
| 9 | Desperdicio de espacio en cola simple |
| 10 | Cola circular |

### 5. Listas enlazadas simples
Listas enlazadas sin usar colecciones de Java.

| # | Ejercicio |
|---|-----------|
| 1 | Crear una lista enlazada simple desde cero |
| 2 | Buscar elementos en una lista enlazada |
| 3 | Obtener un elemento por posición |
| 4 | Insertar un nodo en una posición específica |
| 5 | Eliminar un nodo por valor |
| 6 | Eliminar un nodo por posición |
| 7 | Modificar un elemento de la lista |
| 8 | Contar ocurrencias de un valor |
| 9 | Invertir una lista enlazada simple |
| 10 | Convertir la lista de enteros en una lista genérica |

### 6. Pilas, colas y listas doblemente enlazadas
Estructuras dinámicas basadas en nodos enlazados.

| # | Ejercicio |
|---|-----------|
| 1 | Pila enlazada de enteros |
| 2 | Cola enlazada de enteros |
| 3 | Pila genérica |
| 4 | Cola genérica |
| 5 | Historial de navegación con pila |
| 6 | Cola de atención de clientes |
| 7 | Verificador de paréntesis balanceados |
| 8 | Invertir una palabra con pila |
| 9 | Cola de impresión |
| 10 | Lista doblemente enlazada genérica |

## Cómo compilar y ejecutar

Cada ejercicio es un único archivo autocontenido. La clase es `EjercicioN` con visibilidad de paquete (para que coincida con el nombre en minúscula del archivo).

```bash
# Compilar
cd <modulo>/EjercicioN
javac ejercicioN.java

# Ejecutar
java EjercicioN
```

Ejemplo:

```bash
cd recursividad/Ejercicio1
javac ejercicio1.java
java Ejercicio1
```

## Ramas

| Rama | Contenido |
|------|-----------|
| `main` | Versión principal |
| `develop` | Integración de los prácticos |
| `feature/recursividad` | Práctico de recursividad |
| `feature/ordenamiento` | Práctico de ordenamiento |
| `feature/pilas-colas-arreglos` | Práctico de pilas y colas con arreglos |
| `feature/listas-enlazadas-simples` | Práctico de listas enlazadas simples |
| `feature/pilas-colas-listas-enlazadas` | Práctico de pilas, colas y listas doblemente enlazadas |