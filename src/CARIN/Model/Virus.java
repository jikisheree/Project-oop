package CARIN.Model;

public class Virus extends HostImp {

    public Virus(String geneticCode, int health, int attackDamage,
                 int gain, int[] location) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
        this.geneticCode = geneticCode;
    }


    @Override
    public void move(String newLocation) {
        System.out.println("Cannot move a virus!");
    }

    // Virus1 genetic code here

}
