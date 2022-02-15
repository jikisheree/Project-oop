package CARIN.Model;

import CARIN.Parser.Expr;
import CARIN.Parser.Parser;

import java.util.*;

public class HostImp implements Host{

    int health, attackDamage, gain, moveCost, m, n;
    int[] location = new int[2];
    int[][] cellLoc;
    String geneticCode;
    List<Host> organismInOrder;
    HashMap<String, Expr> identifier;
    Parser parser;

    @Override
    public void shoot(String direction) {
        String dir = direction.toLowerCase();
        int[] shootloc = new int[2];
        shootloc[0] = location[0];
        shootloc[1] = location[1];
        if(dir.equals("up") && location[0]>1){
            shootloc[0]-=1;
        }else if (dir.equals("down") && location[0]<m){
            shootloc[0]+=1;
        }else if (dir.equals("left") && location[1]>0){
            shootloc[1]-=1;
        }else if (dir.equals("right") && location[1]<n){
            shootloc[1]+=1;
        }else if (dir.equals("upleft") && location[0]>1 && location[1]>0){
            shootloc[0]-=1;
            shootloc[1]-=1;
        }else if(dir.equals("upright") && location[0]>1 && location[1]<n){
            shootloc[0]-=1;
            shootloc[1]+=1;
        }else if (dir.equals("downleft") && location[0]<m && location[1]>0){
            shootloc[0]+=1;
            shootloc[1]-=1;
        }else if (dir.equals("downright") && location[0]<m && location[1]<n){
            shootloc[0]+=1;
            shootloc[1]+=1;
        }else{
            System.out.println("can't shoot");
        }
        if(!Arrays.equals(shootloc, location)) {
            for (Host h : organismInOrder) {
                if (Arrays.equals(h.getLocation(), shootloc)) {
                    if(h.setHealth(attackDamage) && this.getType() == 2 && h.getType() == 1 ){
                        h.isDeath(this);
                    }
                    health+=gain;
                    System.out.println("shoot" + dir);
                }
            }
        }
    }

    @Override
    public void move(String newLocation) {
        String dir = newLocation.toLowerCase();
        if(dir.equals("up") && location[0]>1 ){
            location[0]-=1;
        }else if (dir.equals("down") && location[0]<m){
            location[0]+=1;
        }else if (dir.equals("left") && location[1]>0){
            location[1]-=1;
        }else if (dir.equals("right") && location[1]<n){
            location[1]+=1;
        }else if (dir.equals("upleft") && location[0]>1 && location[1]>0){
            location[0]-=1;
            location[1]-=1;
        }else if(dir.equals("upright") && location[0]>1 && location[1]<n){
            location[0]-=1;
            location[1]+=1;
        }else if (dir.equals("downleft") && location[0]<m && location[1]>0){
            location[0]+=1;
            location[1]-=1;
        }else if (dir.equals("downright") && location[0]<m && location[1]<n){
            location[0]+=1;
            location[1]+=1;
        }else this.cantMove();

        System.out.println("moved to " + dir);
    }

    @Override
    public void move(int[] newLocation) { }

    @Override
    public int[] getLocation() {
        int[] loc = new int[2];
        loc[0] = location[0];
        loc[1] = location[1];
        return loc;
    }

    @Override
    public int getType() { return 0; }

    @Override
    public void isDeath(Host host) {}

    @Override
    public void eval() {
        parser.eval();
    }

    @Override
    public HashMap<String, Expr> getIdentifier() {
        return identifier;
    }

    public void cantMove(){}

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean setHealth(int damage) {
        health-=damage;
        return health <= 0;
    }

    @Override
    public int getNearest() {
        Map<Integer,String> alldir = new HashMap<>();
        int[] distance = new int [8];
        int i = 0;
        String[] direction = {"up","down","right","left","upleft","downleft","upright","downright"};
        for(String dir : direction){
            int x = this.getNearBy(dir);
            if(x!=0) {
                alldir.put(x, dir);
                distance[i] = x;
                i++;
            }
        }
        Arrays.sort(distance);
        String dis = String.valueOf(distance[0]);
        String dir = alldir.get(dis);
        return switch (dir) {
            case "up" -> Integer.parseInt(dis + "1");
            case "upright" -> Integer.parseInt(dis + "2");
            case "right" -> Integer.parseInt(dis + "3");
            case "downright" -> Integer.parseInt(dis + "4");
            case "down" -> Integer.parseInt(dis + "5");
            case "downleft" -> Integer.parseInt(dis + "6");
            case "left" -> Integer.parseInt(dis + "7");
            case "upleft" -> Integer.parseInt(dis + "8");
            default -> 0;
        };
    }

    @Override
    public int getNearBy(String direction) {
        String dir = direction.toLowerCase();
        int ans = m*n*10;
        boolean notfound = true;
        if(dir.equals("up") && location[0]>1){
            for (Host h : organismInOrder) {
                if (h.getLocation()[1] == location[1] && h.getLocation()[0]<location[0] ) {
                    int a = ((location[0] - h.getLocation()[0])*10) + h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("down") && location[0]<m){
            for (Host h : organismInOrder) {
                if (h.getLocation()[1] == location[1] && h.getLocation()[0]>location[0] ) {
                    int a =((h.getLocation()[0] - location[0])*10)+h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("left") && location[1]>0){
            for (Host h : organismInOrder) {
                if (h.getLocation()[0] == location[0] && h.getLocation()[1]<location[1] ) {
                   int a = ((location[1] - h.getLocation()[1])*10)+h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("right") && location[1]<n){
            for (Host h : organismInOrder) {
                if (h.getLocation()[0] == location[0] && h.getLocation()[1]>location[1] ) {
                    int a =((h.getLocation()[1] - location[1])*10)+h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("upleft") && location[0]>1 && location[1]>0){
            for (Host h : organismInOrder) {
                if (location[0] - h.getLocation()[0] == location[1] - h.getLocation()[1]) {
                    int a = ((location[0] - h.getLocation()[0])*10)+h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else if(dir.equals("upright") && location[0]>1 && location[1]<n){
            for (Host h : organismInOrder) {
                if (location[0]-h.getLocation()[0] == h.getLocation()[1]-location[1] ) {
                    int a = ((location[0] - h.getLocation()[0])*10)+h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("downleft") && location[0]<m && location[1]>0){
            for (Host h : organismInOrder) {
                if (h.getLocation()[0] - location[0] == location[1] - h.getLocation()[1]) {
                    int a = ((h.getLocation()[0] - location[0])*10)+h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("downright") && location[0]<m && location[1]<n){
            for (Host h : organismInOrder) {
                if (h.getLocation()[0] - location[0] == h.getLocation()[1] - location[1]) {
                    int a = ((h.getLocation()[0] - location[0])*10)+h.getType();
                    if(a<ans) ans = a;
                }
            }
        }else{
            System.out.println("Wrong direction!");
        }
        if(ans == m*n*10) return 0;
        else return ans;
    }

}
