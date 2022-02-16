package CARIN.Model;
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
        this.identifier = new HashMap<>();
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
