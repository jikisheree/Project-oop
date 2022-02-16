package CARIN.Model;
import CARIN.Parser.Number;
import CARIN.Parser.Parser;

import java.util.*;

public class Virus extends HostImp {

    public Virus(String geneticCode, int health, int attackDamage,int gain, int[] location, Body body) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.gain = gain;
        this.location = location;
        this.geneticCode = geneticCode;
        this.body = body;
        this.m = this.body.getmn()[0];
        this.n = this.body.getmn()[1];
        this.identifier = new HashMap<>();
        this.identifier.put("random", new Number((int)(Math.random()*99)));
        this.parser = new Parser(geneticCode, this);
    }

    @Override
    public void cantMove() {
        System.out.println("Cannot move a virus!");
    }

    @Override
    public int getType(){
        return 1;
    }
    @Override
    public void isDeath(Host antibody){
        body.addVirusTurnAntiCredit(location);
    }

}
