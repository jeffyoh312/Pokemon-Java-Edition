import java.util.Random;

public class FirePokemon extends Pokemon{

    static Random random = new Random();
    protected static final String[] fireTypeNames = {"Charizard" , "Infernape" , "Embour" , "Incineroar"};

    // gets random name from list of names
    private static String generateName(){
        return fireTypeNames[random.nextInt(fireTypeNames.length)];
    }

    // sets name and type
    public FirePokemon(){
        super(generateName() , PokemonTypes.FIRE);
    }

}