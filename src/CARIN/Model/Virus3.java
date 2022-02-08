package CARIN.Model;

public class Virus3 extends HostImp {

    public Virus3(int health, int attackDamage,
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

    // Virus3 genetic code here
}

