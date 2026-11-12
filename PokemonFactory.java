import java.util.ArrayList;
import java.util.Random;

public class PokemonFactory {
    private static final PokemonTypes[] types = PokemonTypes.values();
    private static final Random rand = new Random();

    public static Pokemon generateRandomPokemon() {
        return switch (types[rand.nextInt(types.length)]) {
            case FIRE -> new FirePokemon();
            case WATER -> new WaterPokemon();
            case GRASS -> new GrassPokemon();
            case NORMAL -> new NormalPokemon();
        };
    }

    public static ArrayList<Pokemon> generateMultipleRandomPokemon(int amount) {
        ArrayList<Pokemon> list = new ArrayList<>();
        for (int i = 0; i < amount; i++) list.add(generateRandomPokemon());
        return list;
    }
}
