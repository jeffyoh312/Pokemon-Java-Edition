import java.util.Random;

public class GrassPokemon extends Pokemon{
    
    static Random random = new Random();
    protected static final String[] grassTypeNames = {"Torterra" , "Rillaboom" , "Venosaur" , "Meowscarada"};

    // gets random name from list of names
    private static String generateName(){
        return grassTypeNames[random.nextInt(grassTypeNames.length)];
    }

    // sets name and type
    public GrassPokemon(){
        super(generateName() , PokemonTypes.GRASS);
    }

}