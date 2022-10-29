public class Warrior extends Character implements Attacker {
    public Warrior(String id, String name, int hp, boolean isAlive, int stamina, int strength) {
        super(id, name, hp, isAlive);
        setStamina(stamina);
        setStrength(strength);
    }
    private int stamina;
    private int strength;

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        if(stamina<10) this.stamina = 10;
        else if (stamina>50) this.stamina = 50;
        else this.stamina= stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if(strength<1) this.strength = 1;
        else if (strength>50) this.strength = 50;
        else this.strength= strength;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", isAlive=" + isAlive +
                ", stamina=" + stamina +
                ", strength=" + strength +
                "} " + super.toString();
    }

    @Override
    public int attack() {
        //heavy attack
        if(stamina > 5){
           stamina -= 5;
           return strength;

           //weak attack
        } else {
            stamina += 1;
            return strength/2;
        }
    }

    @Override
    public boolean receiveDamage(int damage) {
        if(hp > damage){
            hp -= damage;
        } else {
            hp = 0;
            isAlive = false;
        }
        return isAlive;
    }
}
