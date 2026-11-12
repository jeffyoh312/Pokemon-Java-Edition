public enum PokemonTypes {
    FIRE, WATER, GRASS, NORMAL;

    private PokemonTypes strongAgainst;
    private PokemonTypes weakAgainst;
    private static final double attackDmg = 25;

    static {
        FIRE.strongAgainst = GRASS;
        FIRE.weakAgainst = WATER;
        WATER.strongAgainst = FIRE;
        WATER.weakAgainst = GRASS;
        GRASS.strongAgainst = WATER;
        GRASS.weakAgainst = FIRE;
        NORMAL.strongAgainst = null;
        NORMAL.weakAgainst = null;
    }

    public double getDmgMultiplier(PokemonTypes opponentType){
        if(this.strongAgainst == opponentType) return 2.0;
        if(this.weakAgainst == opponentType) return 0.5;
        if (strongAgainst == null || weakAgainst == null) return 1.0;
        return 1.0;
    }

    public String getBattleMsg(PokemonTypes opponent){
        double mult = getDmgMultiplier(opponent);
        if(mult == 2.0) return "It's super effective!";
        if(mult == 0.5) return "It's not very effective...";
        return "";
    }

    public static double getAttackDmg(){
        return attackDmg;
    }
}
