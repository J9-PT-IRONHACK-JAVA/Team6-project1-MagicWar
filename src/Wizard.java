public class Wizard extends Character {
    private int mana;
    private int intelligence;

    public Wizard(int id, String name, int hp, int mana, int intelligence) {
        super(id, name, hp);
        setHp(hp);
        this.mana = mana;
        this.intelligence = intelligence;
        setAlive(true);
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
    public void setHp(int hp) {
        if (hp>100) hp = 100;
        else if (hp<50) hp = 50;
        super.setHp(hp);
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
    public String toString() {
        return "Wizard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", isAlive=" + isAlive +
                ", mana=" + mana +
                ", intelligence=" + intelligence +
                '}';
    }
}
