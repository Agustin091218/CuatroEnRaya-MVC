# MEMORY - Proyecto Final Programacion - MVC Purista

## Estado: REFACTORING MVC PURISTA COMPLETADO

### Grafo de dependencias verificado

```
model/  -> java.util SOLAMENTE (0 imports de view, controller, awt)
view/   -> javax.swing, java.awt SOLAMENTE (0 imports de model)
controller/ -> model.* + view.* (mediador legitimo)
app/Main -> model + view + controller (composition root)
```

### Estructura final

```
model/
  GameConstants.java   <- Solo dominio: BOARD_ROWS, IDs, WINNING_LENGTH, errores
  GameModel.java       <- Logica de juego, copia defensiva, deteccion empate
  GameRules.java       <- Algoritmos de victoria y validacion de tablero
  Player.java          <- Entidad jugador con validacion, id inmutable

view/
  ViewConstants.java   <- Solo UI: dimensiones, fuentes, textos, action commands
  ColorScheme.java     <- Mapeo visual playerID->Color (movido desde model/)
  ViewBoard.java       <- Recibe rows/cols por constructor, 0 imports model
  ViewLogin.java       <- 0 imports model, usa ColorScheme propio

controller/
  Controller.java      <- Mediador puro, usa ViewConstants para UI, GameModel para logica

app/
  Main.java            <- Composition root, cablea las 3 capas
```

### Cambios clave realizados

1. ColorScheme movido de model/ a view/ (era java.awt.Color en el modelo)
2. GameConstants partido: dominio en model/, UI en view/ViewConstants
3. ViewBoard ya no importa model.* - recibe dimensiones por constructor
4. ViewLogin ya no importa model.* - usa ViewConstants + ColorScheme local
5. GameModel: copia defensiva en getBoard(), deteccion de empate, getters de dimensiones
6. Player: id ahora es final (inmutable despues de creacion)
7. Controller: usa ViewConstants para strings UI, GameConstants para dominio
8. Compilacion verificada: javac 25 - 0 errores
