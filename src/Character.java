public abstract class Character implements Attacker{
    protected int id;
    protected String name;
    protected int hp;
    protected boolean isAlive;

    public Character(int id, String name, int hp) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.isAlive = true;
    }

    public boolean receiveDamage(int damage) {
        if(hp > damage){
            hp -= damage;
        } else {
            hp = 0;
            isAlive = false;
        }
        return isAlive;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
