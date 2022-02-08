package CARIN.Model;

import java.util.*;

public class BodyImp implements Body{
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
    private int virusNum, antibodyNum;

    // input from config file
    // assume m and n is <=10 first
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
        buildField();
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
    private void buildField(){
        for (int i=0;i<m;i++){
            for (int j=0;i<n;i++){
                cellLoc[i][j] = 0;
            }
        }
    }

    private Host randomAnti(int location) {
        int x = (int) (Math.random() * 10) + 1;
        if(x%3==0) return new Antibody1(antiHealth, antiAttack, antiGain,location);
        else if(x%3==1) return new Antibody2(antiHealth, antiAttack, antiGain,location);
        else return new Antibody3(antiHealth, antiAttack, antiGain,location);
    }

    private Host randomVirus(int location) {
        int x = (int) (Math.random() * 10) + 1;
        if(x%3==0) return new Virus1(antiHealth, antiAttack, antiGain,location);
        else if(x%3==1) return new Virus2(antiHealth, antiAttack, antiGain,location);
        else return new Virus3(antiHealth, antiAttack, antiGain,location);
    }

    private void addToCellLoc(int location, int order){
        int m = location/10;
        int n = location%10;
        cellLoc[m][n] = order;
    }

    private int randomLocation(){
        int m = (int) (Math.random() * this.m) + 1;
        int n = (int) (Math.random() * this.n) + 1;
        return (m*10)+n;
    }

    private Host findOrganByLocation(int location){
        int m = location/10;
        int n = location%10;
        int order = cellLoc[m][n];
        return organismInOrder.get(order);
    }

    @Override
    public void addAntibody(int location) {
        if(antiCredit>0) {
            this.organismInOrder.put(order, randomAnti(location));
            addToCellLoc(location, order);
            antiCredit-=placeCost;
            antibodyNum++;
            order++;
        }else
            System.out.println("Run out of antibody credit! Please try to move your existed antibody instead.");
    }

    @Override
    public void addVirus() {
        // have to use virusSpawn variable as probability.
        int location = randomLocation();
        this.organismInOrder.put(order, randomVirus(location));
        addToCellLoc(location,order);
        virusNum++;
        order++;
    }

    @Override
    public void run() {
        Host current;
        for(int i=1; i<(order-1); i++){
            current = organismInOrder.get(i);
            current.action();
        }
    }

    @Override
    public int[][] getCellLoc() {
        return cellLoc;
    }

    @Override
    public Map<Integer, Host> getOrganism() {
        return organismInOrder;
    }

    @Override
    public void moveAntibody(int location, int newLocation) {
        Host host = findOrganByLocation(location);
        host.moveByPlayer(newLocation);
    }

    @Override
    public int getVirusNum() {
        return virusNum;
    }

    @Override
    public int getAntibodyNum() {
        return antibodyNum;
    }

}
