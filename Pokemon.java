public class Pokemon {
    private String name;
    private PokemonTypes type;
    private double maxHp = 100;
    private double currentHp;
    private boolean isAlive = true;

    public Pokemon(String name, PokemonTypes type){
        this.name = name;
        this.type = type;
        this.currentHp = this.maxHp;
    }

    public void displayInfo(){
        System.out.println("Pokemon Name : " + this.name);
        System.out.println("Pokemon Type : " + this.type);
        System.out.println("Current Hp : " + this.currentHp);
        System.out.println("Current Status : " + (isAlive ? "Alive" : "Fainted"));
        System.out.println();
    }
    
    public void displayInfoBattle(){
        System.out.println("Pokemon Name : " + this.name);
        System.out.println("Current Hp : " + this.currentHp);
        System.out.println();
    }

    public void attack(Pokemon enemy){
        double multiplier = this.type.getDmgMultiplier(enemy.getType());
        double damage = PokemonTypes.getAttackDmg() * multiplier;

        System.out.println(this.type.getBattleMsg(enemy.getType()));
        enemy.takeDmg(damage);
    }

    public void takeDmg(double damage){
        this.currentHp -= damage;
        if(this.currentHp <= 0){
            this.currentHp = 0;
            faint();
        }
    }

    public void faint(){
        System.out.println(this.name + " has fainted!");
        this.isAlive = false;
    }

    public void revived(){
        this.currentHp = this.maxHp;
        this.isAlive = true;
    }
    
    public void restore() {
        this.currentHp = this.maxHp;
        this.isAlive = true;
    }
    
    public String getName() { return name; }
    public PokemonTypes getType() { return type; }
    public double getHp() { return currentHp; }
    public boolean getStatus() { return isAlive; }
    public void setName(String name) { this.name = name; }
    public void setType(PokemonTypes type) { this.type = type; }
    public void setHp(double hp) { this.currentHp = hp; }
    public void setStatus(boolean status) { this.isAlive = status; }

    public double getCatchRate() {
        return 0.5; // basic catch rate
    }

    public boolean isDefeated() {
        return !this.isAlive;
    }
}


