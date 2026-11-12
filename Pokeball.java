public enum Pokeball {
    POKEBALL("PokéBall", 0.5, 1.0),
    GREATBALL("GreatBall", 0.7, 1.5),
    ULTRABALL("UltraBall", 0.9, 2.0);

    private final String ballname;
    private final double catchrate;
    private final double multiplier;
    private int quantity = 5;

    Pokeball(String ballname, double catchrate, double multiplier) {
        this.ballname = ballname;
        this.catchrate = catchrate;
        this.multiplier = multiplier;
    }

    public String getName () { return ballname; }
    public boolean isOutOfStock() { return quantity == 0; }
    public void use() { if (quantity > 0) quantity--; }
    public int getQuantity() {return quantity;}
    public void addQuantity(int amount) {quantity += amount;}

    public boolean tryCatch(Pokemon wild) {
        if (isOutOfStock()) return false;
        use();
        double chance = catchrate * multiplier * wild.getCatchRate();
        return Math.random() < chance;
    }
}
