package CARIN.Model;
import java.util.*;

public class Virus extends HostImp {

    public Virus(String geneticCode, int health, int attackDamage,int gain, int[] location,int m,int n, List<Host> organismInOrder,int[][] cellLoc) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
        this.geneticCode = geneticCode;
        this.m = m;
        this.n = n;
        this.organismInOrder = organismInOrder;
        this.cellLoc = cellLoc;
    }

    @Override
    public void cantmove() {
        System.out.println("Cannot move a virus!");
    }

    @Override
    public int gettype(){
        return 1;
    }

    public void death(Antibody antibody){
        cellLoc[location[0]][location[1]] = 0;
        organismInOrder.remove(organismInOrder.indexOf(this));
        antibody.antiCredit+=antibody.gain;
    }

}
