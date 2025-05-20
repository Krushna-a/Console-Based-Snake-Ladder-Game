# Console-Based Snake & Ladder Game

A multiplayer board game implementation in Java featuring advanced OOP design and Java Collections.

## 🎯 Features

- **Multiplayer Support**: 2-6 players
- **Configurable Board**: 100-cell board with 10+ snakes and 10+ ladders
- **Multi-die Rolling**: Support for 1-3 dice with bonus turn on rolling a 6
- **Chain Resolution**: Cascading snake-ladder collision handling
- **Exact Landing Rule**: Prevents overshoots beyond cell 100
- **Clean Architecture**: 6+ classes across 3 packages

## 📦 Project Structure

```
src/
├── models/           # Data models
│   ├── Player.java
│   ├── Cell.java
│   ├── Snake.java
│   └── Ladder.java
├── game/             # Game logic
│   ├── Board.java
│   ├── Dice.java
│   └── GameEngine.java
└── main/             # Entry point
    └── SnakeAndLadderGame.java
```

## 🛠️ Technologies Used

- **Java Collections**:
  - `Queue<Player>` - Turn management
  - `HashMap<Integer, Integer>` - Snake/Ladder mappings
  - `ArrayList<Integer>` - Move transitions tracking

## 🚀 How to Run

### Compile:
```bash
javac -d bin src/models/*.java src/game/*.java src/main/*.java
```

### Run:
```bash
java -cp bin main.SnakeAndLadderGame
```

## 🎮 Game Rules

1. Players take turns rolling the dice
2. Move forward by the number shown on the dice
3. Landing on a ladder climbs you up
4. Landing on a snake slides you down
5. Rolling a 6 gives you a bonus turn
6. Must land exactly on cell 100 to win
7. Overshoots are rejected, player stays at current position

## 📊 Key Implementation Details

- **Do-while chain resolution loop** handles cascading snake-ladder collisions
- **Queue-based turn management** ensures fair player rotation
- **HashMap lookup** provides O(1) snake/ladder position resolution
- **Exact-landing enforcement** eliminates game-state bugs

## 👨‍💻 Author

Built with Java OOP principles and efficient data structures.
