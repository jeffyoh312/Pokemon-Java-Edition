import java.util.Random;

public class NormalPokemon extends Pokemon{
    
    static Random random = new Random();
    protected static final String[] normalTypeNames = {"Evee" , "Slaking" , "Arceus" , "Kangaskhan"};

    // gets random name from list of names
    private static String generateName(){
        return normalTypeNames[random.nextInt(normalTypeNames.length)];
    }

    // sets name and type
    public NormalPokemon(){
        super(generateName(), PokemonTypes.NORMAL);
    }

}