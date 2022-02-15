package CARIN.Model;
import java.util.*;

public class Antibody extends HostImp{
    Body body;

    public Antibody(String geneticCode, int health, int attackDamage,int gain,int moveCost, int antiCredit, int[] location,int m,int n, List<Host> organismInOrder,int[][] cellLoc,Body body) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.movecost = moveCost;
        this.location = location;
        this.geneticCode = geneticCode;
        this.m = m;
        this.n = n;
        this.organismInOrder = organismInOrder;
        this.cellLoc = cellLoc;
        this.body = body;
    }

    @Override
    public void cantmove() {
        System.out.println("Cannot move a antibody!");
    }

    @Override
    public int gettype(){
        return 2;
    }

    @Override
    public void move(int[] newLocation){
        location = newLocation;
        health -= movecost;
        System.out.println("move to " + newLocation[0] + newLocation[1]);
    }

    public void death(Virus virus){
        cellLoc[location[0]][location[1]] = 0;
        organismInOrder.remove(organismInOrder.indexOf(this));
        int[] loc = {location[0], location[1]};
        body.addVirus(virus,loc);
    }


}
