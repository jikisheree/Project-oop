package CARIN.Model;

import java.util.*;

public class BodyImp {
    Map<Integer, Host> organismInOrder = new HashMap<>();
    int[][] cellLoc;
    int timeUnit;
    int antiCredit;
    int placeCost;
    int moveCost;
    double virusSpawn;
    int antiHealth, antiAttack, antiGain;
    int virusHealth, virusAttack, virusGain;
    int m, n;
    int order;

    // input from config file
    public BodyImp(
            int m, int n,
            int antiCredit,
            int placeCost,
            int moveCost,
            double virusSpawn,
            int antiHealth,
            int antiAttack,
            int antiGain,
            int virusHealth,
            int virusAttack,
            int virusGain) {

        cellLoc = new int[m][n];
        this.antiCredit = antiCredit;
        this.virusSpawn = virusSpawn;
        this.placeCost = placeCost;
        this.moveCost = moveCost;
        this.antiHealth = antiHealth;
        this.antiAttack = antiAttack;
        this.antiGain = antiGain;
        this.virusHealth = virusHealth;
        this.virusAttack = virusAttack;
        this.virusGain = virusGain;
        this.order = 1;

    }

    public void addAntibody(int location) {
        this.organismInOrder.put(order, randomAnti(location));
//        cellLoc
        order++;
    }

    private void addVirus() {
        int x = (int) (Math.random() * m) + n;
        this.organismInOrder.put(order, randomVirus());
        order++;
    }

    private Antibody randomAnti(int location) {
        int x = (int) (Math.random() * 10) + 1;
        if(x%3==0) return new Antibody1(
                cellLoc,antiHealth, antiAttack, antiGain,location);
        else if(x%3==1) return new Antibody2();
        else return new Antibody3();
    }

    private Host randomVirus() {
        int x = (int) (Math.random() * 10) + 1;
        if(x%3==0) return new Virus1();
        else if(x%3==1) return new Virus2();
        else return new Virus3();
    }

    private void run(){
        Host current;
        for(int i=1; i<(order-1); i++){
            current = organismInOrder.get(i);
            current.action();
        }
    }


}
