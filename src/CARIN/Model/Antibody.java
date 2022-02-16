package CARIN.Model;
import CARIN.Parser.Parser;

import java.util.*;

public class Antibody extends HostImp{

    public Antibody(String geneticCode, int health, int attackDamage,int gain,int moveCost,
                    int[] location,Body body) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.moveCost = moveCost;
        this.location = location;
        this.geneticCode = geneticCode;
        this.body = body;
        this.m = this.body.getmn()[0];
        this.n = this.body.getmn()[1];
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
    public void isDeath(Host virus){
        body.addAntiTurnVirus(virus.getGeneticCode(),location);
    }


}
