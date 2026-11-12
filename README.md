# Pokémon: Java Edition

### Gotta catch 'em all. Assuming your Poké Balls cooperate.

A tiny terminal-based Pokémon game built with **Java** as a group project. Assemble your team, challenge wild Pokémon, exploit type advantages, and collect points. If everything goes wrong, there's a doctor. Healthcare costs 20 points, unless your entire team has fainted. Then, apparently, it's free.

No graphics. No open world. Just Pokémon, questionable luck, and a surprising amount of console output.

## What you can do

| Feature | Description |
| --- | --- |
| **Random starting team** | Begin with two randomly generated Pokémon. |
| **2v2 battles** | Choose two Pokémon to face two wild opponents in automatic turn-based combat. |
| **Type advantages** | Fire, Water, and Grass have strengths and weaknesses; Normal deals neutral damage. |
| **Catch Pokémon** | Attempt to catch defeated wild Pokémon using Poké Balls, Great Balls, and Ultra Balls. Success is not guaranteed. |
| **Heal your team** | Restore your Pokémon at the doctor for 20 score points, or for free if the entire collection has fainted. |
| **Leaderboard** | Save and display the five highest scores in a local text file. |

## How it works

**1. Meet your team.** Enter a trainer name. The game gives you two random Pokémon. You do not get to choose your starters. The RNG has opinions.

**2. Pick a fight.** Select **Start Battle** and choose two distinct Pokémon from your collection. Two wild Pokémon appear, and both sides take turns attacking automatically. The game shows HP and announces when a Pokémon faints.

**3. Know your types.** Fire beats Grass, Grass beats Water, and Water beats Fire. Strong attacks deal double damage; resisted attacks deal half. Normal-type attacks deal standard damage.

**4. Try your luck.** After battle, the game attempts to catch defeated wild Pokémon using your available Poké Balls. Catching is chance-based, and used balls are removed from inventory.

**5. Keep going.** Win points from battles, check the top-five leaderboard, view your collection, or heal your team. Your scoreboard is saved locally to `scores.txt`.

## Getting started

### Requirements

- **Java Development Kit (JDK) 17 or newer**
- A terminal (Command Prompt, PowerShell, Terminal, or your IDE's terminal)
- No external libraries or build tools

### Run the game

Clone the repository:

```bash
git clone <repository-url>
cd <repository-folder>
```

Replace the placeholder URL and folder name with this repository's actual details. Alternatively, download the repository as a ZIP and extract it.

Compile the Java files:

```bash
javac *.java
```

Start playing:

```bash
java PokemonGame
```

All `.java` files must be in the same directory when compiling with the commands above. In an IDE, run the `main` method in `PokemonGame.java`.

### Main menu

```text
1. Start Battle
2. View My Pokemon
3. View Top Scores
4. View Pokeball Inventory
5. Go to Doctor (Heal Team)
6. Exit
7. Check Current Score
```

Tip: Enter the number corresponding to your choice. Choosing a Pokémon uses **zero-based indexing**, meaning the first Pokémon is `0`.

## Behind the logic

The game uses object-oriented Java, including inheritance and enums:

- `Pokemon.java`: core Pokémon attributes, HP, damage, and fainting.
- `FirePokemon.java`, `WaterPokemon.java`, `GrassPokemon.java`, `NormalPokemon.java`: type-specific Pokémon with randomized names.
- `PokemonTypes.java`: type matchups and damage multipliers.
- `PokemonFactory.java`: creates random Pokémon.
- `Player.java`: team collection, selection, catching, and healing.
- `Pokeball.java`: ball inventory and probabilistic catching.
- `Battle.java`: automatic combat rounds, battle results, and scoring.
- `ScoreManager.java`: local score persistence and leaderboard sorting.
- `PokemonGame.java`: main entry point and menu navigation.

**Tech stack:** Java 17+, Java standard library, file I/O. No framework, no database, no hidden paid Poké Ball subscription.

## Good to know

- This is a **console game**, not a graphical Pokémon game.
- Battles are **automatic** once you choose your two Pokémon; you don't select individual moves.
- Player scores are written to a local `scores.txt` file in the working directory. That file is ignored by Git.
- The game expects numeric menu inputs. Non-numeric input may cause an error.
- Pokémon names and type mechanics are used in a small, unofficial educational fan project. This project is **not affiliated with, sponsored by, or endorsed by Nintendo, Game Freak, or The Pokémon Company**.

## Contributors

Made as a **group Java project**. The team and individual contributions are listed in [CONTRIBUTORS.md](CONTRIBUTORS.md).

Have a suggestion or found a bug? Open an issue. If a wild exception appeared, include what you typed before it attacked.

---

*Built with Java, teamwork, and a healthy respect for type advantage.*
