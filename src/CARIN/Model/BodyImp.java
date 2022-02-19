package CARIN.Model;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class BodyImp implements Body{
    List<Host>organismInOrder = new CopyOnWriteArrayList<>();
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

        cellLoc = new int[m+1][n+1];
        this.m = m; this.n =n;
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
        this.geneticCodeAnti = geneticCodeAnti;
        this.geneticCodeVirus = geneticCodeVirus;
    }
    // create cell field of array which contain row m and column n
    private void buildField(){
        for (int i=0;i<=m;i++){
            for (int j=0;i<=n;i++){
                cellLoc[i][j] = 0;
            }
        }
    }
    // random genetic code to antibody
    private Host randomAnti(int[] location) {
        int x = (int) (Math.random() * geneticCodeAnti.size());
        return new Antibody(geneticCodeAnti.get(x), antiHealth, antiAttack, antiGain, moveCost,location,this);
    }
    // random genetic code to virus
    private Host randomVirus(int[] location) {
        int x = (int) (Math.random() * geneticCodeVirus.size());
        return new Virus(geneticCodeVirus.get(x), virusHealth, virusAttack, virusGain, location, this);
    }
    // when adding new organism -> add to cell field
    private void addToCellLoc(int[] location){
        cellLoc[location[0]][location[1]] = order;
    }
    // when moving an organism -> change location in field
    private void changeCellLoc(int[] location, int[] newLocation, int order){
        cellLoc[location[0]][location[1]] = 0;
        cellLoc[newLocation[0]][newLocation[1]] = order;
    }
    // random location when adding a new virus
    // location[0] is row and location[1] is column
    private int[] randomLocation(){
        int[] location = new int[2];
        // avoiding adding to owned cell
        if(order == 1) {
            location[0] = (int) (Math.random() * m) + 1;
            location[1] = (int) (Math.random() * n) + 1;
        }else {
            do {
                location[0] = (int) (Math.random() * m) + 1;
                location[1] = (int) (Math.random() * n) + 1;
            } while (cellLoc[location[0]][location[1]] != 0);
        }
        return location;
    }
    //
    public Host findOrganByLocation(int[] location){
        int m = location[0];
        int n = location[1];
        int order = cellLoc[m][n];
        return organismInOrder.get(order-1);
    }

    @Override
    public boolean findhost(int[] location) {
        int m = location[0];
        int n = location[1];
        int order = cellLoc[m][n];
        return order>0 ;
    }

    // adding new antibody
    @Override
    public void addAntibody(int[] location) {
        if(antiCredit>0) {
            if(cellLoc[location[0]][location[1]] != 0)
                System.out.println("This cell is not empty!.");
            else {
                this.organismInOrder.add(randomAnti(location));
                addToCellLoc(location);
                cellLoc[location[0]][location[1]] = order;
                int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
                System.out.println("Added antibody to cell "+ loc);
                antiCredit -= placeCost;
                antibodyNum++;
                order++;
            }
        }else
            System.out.println("Run out of antibody credit! Please try to move your existed antibody instead.");
    }

    /* for test */
    @Override
    public void addvirus(int[] location) {
        if(cellLoc[location[0]][location[1]] == 0){
            this.organismInOrder.add(randomVirus(location));
            addToCellLoc(location);
            cellLoc[location[0]][location[1]] = order;
            int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
            System.out.println("Added virus to cell "+ loc);
            virusNum++;
            order++;
        }else
            System.out.println("The cell you tried to add a virus is not empty.");
    }

    // adding new virus
    @Override
    public void addVirus() {
        // have to use virusSpawn variable as probability.
        // if random number less than virus spawn rate then a virus spawned.
        double probability = Math.random();
        if(probability<=this.virusSpawn){
            int[] location = randomLocation();
            this.organismInOrder.add(randomVirus(location));
            addToCellLoc(location);
            cellLoc[location[0]][location[1]] = order;
            int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
            System.out.println("Added virus to cell "+ loc);
            virusNum++;
            order++;
        }else
            System.out.println("A virus did not spawn to the body this time unit.");
    }

    @Override
    public void addVirusTurnAntiCredit(int[] location) {
        removeOrganism(location);
        antiCredit+=placeCost;
        int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
        System.out.println("Antibody at cell"+ loc+" is dead. Antibody credit added!");
    }

    // when an antibody is dead and turn into a virus
    @Override
    public void addAntiTurnVirus(String geneticCode, int[] location) {
        removeOrganism(location);
        this.organismInOrder.add( new Virus(geneticCode, antiHealth, antiAttack, antiGain,location,this));
        addToCellLoc(location);
        cellLoc[location[0]][location[1]] = order;
        int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
        System.out.println("Antibody at cell"+ loc+"turned into virus!");
        order++;
        virusNum++;
    }
    // called to be evaluating organisms in order at each time unit
    @Override
    public void run() {
        Host current;
        int currentOrder = order;
        for(int i=1; i<currentOrder; i++){
            current = organismInOrder.get(i-1);
            System.out.println("Eval organism "+i);
            current.eval();
        }
    }
    // return cell field that contains order of organisms
    @Override
    public int[][] getCellLoc() {
        return cellLoc;
    }
    // return list that contains location of organisms
    @Override
    public List<Host> getOrganism() {
        return organismInOrder;
    }
    // when an antibody is moved by player
    @Override
    public void move(int[] location, int[] newLocation) {
        Host host = findOrganByLocation(location);
        host.move(newLocation);
        int order = organismInOrder.indexOf(host);
        changeCellLoc(location,newLocation,order+1);
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
    public int[] getmn() {
        return new int[]{m,n};
    }

    private void removeOrganism(int[] location) {
        int m = location[0];
        int n = location[1];
        int currentOrder = cellLoc[m][n];
        organismInOrder.remove(currentOrder);
        int loc = Integer.parseInt((location[0])+String.valueOf(location[1]));
        System.out.println("Organism at cell "+ loc+" is dead");
        cellLoc[m][n] = 0;
        checkGameOver();
    }

    private void checkGameOver(){
        if(virusNum==0 || antibodyNum==0 ) {
            System.out.println("Game over ");
            if (virusNum > antibodyNum) System.out.println("Viruses win");
            else System.out.println("Antibodies win!");
        }
    }

}