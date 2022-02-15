package CARIN.Model;

public interface Host {
    void shoot(String direction);
    void move(String newLocation);
    int getHealth();
    boolean setHealth(int damage);
    int getNearest();
    int getNearBy(String direction);
    void move(int[] newLocation);
    int[] getlocation();
    int gettype();
    void death(Host host);
}
