package CARIN.Model;

import java.util.*;

public class BodyImp implements Body{
    List<Host>organismInOrder = new LinkedList<>();
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
    List<String> geneticCodeAnti;
    List<String> geneticCodeVirus;
    // input from config file
    // assume m and n is <=10 first
    public BodyImp(
            List<String> geneticCodeAnti,
            List<String> geneticCodeVirus,
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
        this.order = 0;
        this.geneticCodeAnti = geneticCodeAnti;
        this.geneticCodeVirus = geneticCodeVirus;
    }
    private void buildField(){
        for (int i=0;i<m;i++){
            for (int j=0;i<n;i++){
                cellLoc[i][j] = 0;
            }
        }
    }

    private Host randomAnti(int[] location) {
        int x = (int) (Math.random() * geneticCodeAnti.size());
        return new Antibody(geneticCodeAnti.get(x), antiHealth, antiAttack, antiGain, moveCost, location, m, n, organismInOrder, cellLoc,this);
    }

    private Host randomVirus(int[] location) {
        int x = (int) (Math.random() * geneticCodeVirus.size());
        return new Virus(geneticCodeVirus.get(x), antiHealth, antiAttack, antiGain, location, m, n, organismInOrder, cellLoc);
    }

    private void addToCellLoc(int[] location){
        cellLoc[location[0]][location[1]] = order;
    }
    private void changeCellLoc(int[] location, int[] newLocation, int order){
        cellLoc[location[0]][location[1]] = 0;
        cellLoc[newLocation[0]][newLocation[1]] = order;
    }

    // bug: this part does not random
    private int[] randomLocation(){
        int[] location = new int[2];
        location[0] = (int) (Math.random() * 4) + 1;
        location[1] = (int) (Math.random() * 4) + 1;
        return location;
    }

    private Host findOrganByLocation(int[] location){
        int m = location[0];
        int n = location[1];
        int order = cellLoc[m][n];
        return organismInOrder.get(order);
    }

    @Override
    public void addAntibody(int[] location) {
        if(antiCredit>0) {
            this.organismInOrder.add(randomAnti(location));
            addToCellLoc(location);
            antiCredit-=placeCost;
            antibodyNum++;
            order++;
        }else
            System.out.println("Run out of antibody credit! Please try to move your existed antibody instead.");
    }

    @Override
    public void addVirus() {
        // have to use virusSpawn variable as probability.
        int[] location = randomLocation();
        this.organismInOrder.add(randomVirus(location));
        addToCellLoc(location);
        int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
        System.out.println("added virus to location "+ loc);
        virusNum++;
        order++;
    }

    public void addVirus(Virus virus,int[] loc) {
        // have to use virusSpawn variable as probability.
        new Virus(virus.geneticCode, antiHealth, antiAttack, antiGain, loc, m, n, organismInOrder, cellLoc);
        this.organismInOrder.add(virus);
        addToCellLoc(loc);
        virusNum++;
        order++;
    }

    @Override
    public void run() {
        Host current;
        for(int i=1; i<(order-1); i++){
            current = organismInOrder.get(i);
            current.eval();
        }
    }

    @Override
    public int[][] getCellLoc() {
        return cellLoc;
    }

    @Override
    public List<Host> getOrganism() {
        return organismInOrder;
    }

    @Override
    public void moveAntibody(int[] location, int[] newLocation) {
        Host host = findOrganByLocation(location);
        host.move(newLocation);
        int order = organismInOrder.indexOf(host);
        changeCellLoc(location,newLocation,order);
    }

    @Override
    public int getVirusNum() {
        return virusNum;
    }

    @Override
    public int getAntibodyNum() {
        return antibodyNum;
    }

    @Override
    public void setAntiCredit() {
        antiCredit+=placeCost;
    }

}
