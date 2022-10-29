public class Wizard extends Character implements Attacker {
    private int mana;
    private int intelligence;

    public Wizard(String id, String name, int hp, boolean isAlive, int mana, int intelligence) {
        super(id, name, hp, isAlive);
        this.mana = mana;
        this.intelligence = intelligence;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if(mana < 10) this.mana = 10;
        else if (mana > 50) this.mana = 50;
        else this.mana = mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        if(intelligence < 1) this.intelligence = 1;
        else if (intelligence > 50) this.intelligence = 50;
        else this.intelligence = intelligence;
    }

    @Override
    public int attack() {
        //Fireball
        if(mana > 5){
            mana -= 5;
            return intelligence;

            //staff hit
        } else {
            mana += 1;
            return 2;
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
