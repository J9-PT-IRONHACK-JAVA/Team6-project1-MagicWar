public class Warrior extends Character {
    public Warrior() {
    }
    public Warrior(int id, String name, int hp, int stamina, int strength) {
        super(id, name, hp);
        setHp(hp);
        setStamina(stamina);
        setStrength(strength);
        setAlive(true);
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
    public void setHp(int hp) {
        if (hp>200) hp = 200;
        else if (hp<100) hp = 100;
        super.setHp(hp);
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
                "} ";
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
}
