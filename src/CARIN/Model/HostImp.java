package CARIN.Model;

public class HostImp implements Host{

    int health, attackDamage, gain, location;
    String geneticCode;

    @Override
    public void action() {

    }
    @Override
    public void moveByPlayer(int newLocation) {
        location = newLocation;
    }

    // Parser needed here (protected declaration)
}
