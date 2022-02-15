package CARIN.Model;
import CARIN.Parser.Parser;

import java.util.*;

public class Antibody extends HostImp{
    Body body;

    public Antibody(String geneticCode, int health, int attackDamage,int gain,int moveCost,
                    int[] location,int m,int n, List<Host> organismInOrder,int[][] cellLoc,Body body) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.moveCost = moveCost;
        this.location = location;
        this.geneticCode = geneticCode;
        this.m = m;
        this.n = n;
        this.organismInOrder = organismInOrder;
        this.cellLoc = cellLoc;
        this.body = body;
        this.identifier = new HashMap<>();
        this.parser = new Parser(geneticCode, this);
    }

    @Override
    public void cantMove() {
        System.out.println("Cannot move a antibody!");
    }

    @Override
    public int getType(){
        return 2;
    }

    @Override
    public void move(int[] newLocation){
        location = newLocation;
        health -= moveCost;
        System.out.println("move to " + newLocation[0] + newLocation[1]);
    }
    @Override
    public void isDeath(Host virus){
        cellLoc[location[0]][location[1]] = 0;
        organismInOrder.remove(this);
        int[] loc = {location[0], location[1]};
        body.addVirus((Virus) virus,loc);
    }


}
