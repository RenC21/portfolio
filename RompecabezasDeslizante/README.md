# Rompecabezas Deslizante

Trabajo Práctico de Programación III.

## Descripción
Este proyecto implementa un **juego de rompecabezas deslizante** utilizando el patrón de diseño **Modelo-Vista-Presentador (MVP)** para garantizar una clara separación entre la lógica del juego y la interfaz visual.

El objetivo del juego es reorganizar un tablero desordenado para que las piezas queden en orden numérico. El juego incluye niveles de dificultad, un contador de movimientos y un sistema de puntuaciones basado en el tiempo.

## Características principales
- **Niveles de dificultad:** Fácil, Medio y Difícil.
- **Gestión de puntuaciones:** Registra los mejores tiempos en un archivo y muestra los 5 mejores puntajes.
- **Interfaz gráfica:** Uso de botones para representar las piezas del tablero.
- **Patrón MVP:** Implementación modular que separa la lógica del modelo y la vista.

## Estructura del proyecto
El proyecto se divide en tres componentes principales:

### 1. **Modelo**
La lógica del juego está implementada en esta capa:
- `crearTablero()`: Inicializa un tablero ordenado.
- `desordenarTablero()`: Desordena el tablero según el nivel de dificultad.
- `intentarMoverPieza(Point pActual)`: Intenta mover una pieza del tablero.
- `comprobarGano()` y `comprobarPerdio()`: Verifican el estado del juego.
- Registro de puntajes en un archivo de texto.

### 2. **Presentador**
Intermediario entre la vista y el modelo:
- Gestiona la creación y actualización del tablero.
- Traduce la matriz de enteros del modelo a una matriz de objetos `Pieza` para la vista.
- Verifica las condiciones de victoria o derrota.

### 3. **Vista**
Interfaz gráfica del usuario:
- Ventana de inicio: Selección de nivel de dificultad.
- Ventana principal: Muestra el tablero y el contador de movimientos.
- Mensajes de finalización: Indica si el jugador gana o pierde.

## Problemas enfrentados y soluciones
- **Movimiento incorrecto de las piezas:** Se solucionó graficando la matriz y ajustando el código.
- **Visualización de piezas:** Se eliminó un objeto intermedio (`Celda`) para simplificar la representación.
- **Listeners duplicados:** Se encapsuló la lógica en una función específica para evitar duplicados.
- **Importación cruzada entre capas:** Se refactorizó para evitar dependencias entre el modelo y la interfaz visual.

## Tecnologías utilizadas
- **Lenguaje:** Java
- **Entorno:** Swing para la interfaz gráfica
- **Patrón de diseño:** Modelo-Vista-Presentador (MVP)
