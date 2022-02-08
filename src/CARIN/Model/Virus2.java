package CARIN.Model;

public class Virus2 extends HostImp {

    public Virus2(int health, int attackDamage,
                  int gain, int location) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
    }

    @Override
    public void action() {

    }

    @Override
    public void moveByPlayer(int newLocation) {
        System.out.println("Cannot move a virus!");
    }

    // Virus2 genetic code here
}
