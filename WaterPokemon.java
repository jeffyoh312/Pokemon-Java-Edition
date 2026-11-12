import java.util.Random;

public class WaterPokemon extends Pokemon{
    
    static Random random = new Random();
    protected static final String[] waterTypeNames = {"Blastoise" , "Empoleon" , "Greninja" , "Mudkip"};

    // gets random name from list of names
    private static String generateName(){
        return waterTypeNames[random.nextInt(waterTypeNames.length)];
    }

    // sets name and type
    public WaterPokemon(){
        super(generateName() , PokemonTypes.WATER);
    }

}
