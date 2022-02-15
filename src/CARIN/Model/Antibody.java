package CARIN.Model;
import java.util.*;

public class Antibody extends HostImp{

    public Antibody(String geneticCode, int health, int attackDamage,int gain, int[] location,int m,int n, List<Host> organismInOrder) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
        this.geneticCode = geneticCode;
        this.m = m;
        this.n = n;
        this.organismInOrder = organismInOrder;
    }

    @Override
    public void cantmove() {
        System.out.println("Cannot move a antibody!");
    }

    @Override
    public int gettype(){
        return 2;
    }
}
