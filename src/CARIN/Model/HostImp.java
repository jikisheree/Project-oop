package CARIN.Model;

import java.util.*;

public class HostImp implements Host{

    int health, attackDamage, gain, movecost, antiCredit, m, n;
    int[] location = new int[2];
    int[][] cellLoc;
    String geneticCode;
    List<Host> organismInOrder;

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
        if(!shootloc.equals(location)) {
            for (Host h : organismInOrder) {
                if (h.getlocation().equals(shootloc)) {
                    if(h.setHealth(attackDamage) && this.gettype() == 2 && h.gettype() == 1 ){
                        h.death(this);
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
        }else this.cantmove();

        System.out.println("move to" + dir);
    }

    @Override
    public void move(int[] newLocation) { }

    @Override
    public int[] getlocation() {
        int[] loc = new int[2];
        loc[0] = location[0];
        loc[1] = location[1];
        return loc;
    }

    @Override
    public int gettype() { return 0; }

    @Override
    public void death(Host host) {

    }


    public void cantmove(){}

    @Override
    public int getHealth() {
        int h = health;
        return h;
    }

    @Override
    public boolean setHealth(int damage) {
        health-=damage;
        if(health <= 0){
            return true;
        }
        else return false;
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
        if(dir.equals("up")) return Integer.parseInt(dis+"1");
        else if(dir.equals("upright")) return Integer.parseInt(dis+"2");
        else if(dir.equals("right")) return Integer.parseInt(dis+"3");
        else if(dir.equals("downright")) return Integer.parseInt(dis+"4");
        else if(dir.equals("down")) return Integer.parseInt(dis+"5");
        else if(dir.equals("downleft")) return Integer.parseInt(dis+"6");
        else if(dir.equals("left")) return Integer.parseInt(dis+"7");
        else if(dir.equals("upleft")) return Integer.parseInt(dis+"8");
        else return 0;
    }

    @Override
    public int getNearBy(String direction) {
        String dir = direction.toLowerCase();
        int ans = m*n*10;
        boolean notfound = true;
        if(dir.equals("up") && location[0]>1){
            for (Host h : organismInOrder) {
                if (h.getlocation()[1] == location[1] && h.getlocation()[0]<location[0] ) {
                    int a = ((location[0] - h.getlocation()[0])*10) + h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("down") && location[0]<m){
            for (Host h : organismInOrder) {
                if (h.getlocation()[1] == location[1] && h.getlocation()[0]>location[0] ) {
                    int a =((h.getlocation()[0] - location[0])*10)+h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("left") && location[1]>0){
            for (Host h : organismInOrder) {
                if (h.getlocation()[0] == location[0] && h.getlocation()[1]<location[1] ) {
                   int a = ((location[1] - h.getlocation()[1])*10)+h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("right") && location[1]<n){
            for (Host h : organismInOrder) {
                if (h.getlocation()[0] == location[0] && h.getlocation()[1]>location[1] ) {
                    int a =((h.getlocation()[1] - location[1])*10)+h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("upleft") && location[0]>1 && location[1]>0){
            for (Host h : organismInOrder) {
                if (location[0] - h.getlocation()[0] == location[1] - h.getlocation()[1]) {
                    int a = ((location[0] - h.getlocation()[0])*10)+h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else if(dir.equals("upright") && location[0]>1 && location[1]<n){
            for (Host h : organismInOrder) {
                if (location[0]-h.getlocation()[0] == h.getlocation()[1]-location[1] ) {
                    int a = ((location[0] - h.getlocation()[0])*10)+h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("downleft") && location[0]<m && location[1]>0){
            for (Host h : organismInOrder) {
                if (h.getlocation()[0] - location[0] == location[1] - h.getlocation()[1]) {
                    int a = ((h.getlocation()[0] - location[0])*10)+h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else if (dir.equals("downright") && location[0]<m && location[1]<n){
            for (Host h : organismInOrder) {
                if (h.getlocation()[0] - location[0] == h.getlocation()[1] - location[1]) {
                    int a = ((h.getlocation()[0] - location[0])*10)+h.gettype();
                    if(a<ans) ans = a;
                }
            }
        }else{
            System.out.println("worng direction");
        }
        if(ans == m*n*10) return 0;
        else return ans;
    }

}
