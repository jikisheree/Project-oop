package CARIN.Model;

public class Antibody1 implements Antibody{
    int health, attackDamage, gain, location;
    int[][] cellLoc;
    public Antibody1(int[][] cellLoc, int health, int attackDamage,
                     int gain, int location) {
        this.cellLoc = cellLoc;
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
    }

    //  parser needed

    @Override
    public void action() {

    }

    @Override
    public void move() {

    }

    private Host getNearby(){
        return null;
    }
}
