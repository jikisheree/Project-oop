package CARIN.Model;

public class Virus1 extends HostImp {

    public Virus1(String geneticCode, int health, int attackDamage,
                  int gain, int location) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
        this.geneticCode = geneticCode;
    }

    @Override
    public void action() {

    }

    @Override
    public void moveByPlayer(int newLocation) {
        System.out.println("Cannot move a virus!");
    }

    // Virus1 genetic code here

}
