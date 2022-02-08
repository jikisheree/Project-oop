package CARIN.Model;

public interface Host {
    void action();
    // must be Overridden to do nothing in Virus classes
    void moveByPlayer(int newLocation);

}
