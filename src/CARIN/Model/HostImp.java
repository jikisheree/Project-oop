package CARIN.Model;

import CARIN.Parser.Expr;
import CARIN.Parser.Number;
import CARIN.Parser.Parser;

import java.util.*;

public class HostImp extends Entity implements Host{

    int health, attackDamage, gain, moveCost, m, n;
    int[] location;
    String geneticCode;
    HashMap<String, Expr> identifier;
    Parser parser;
    Body body;
    public HostImp(String geneticCode, int health, int attackDamage,int gain, int[] location, Body body){
        super();
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
            if(body.findHost(shootloc)){
                System.out.println("shoot" + dir);
                Host shoot = body.findOrganByLocation(shootloc);
                if(shoot.setHealth(attackDamage)) shoot.isDeath(this);
                health+=gain;
            }
        }
    }

    @Override
    public void move(String newLocation) {
        String dir = newLocation.toLowerCase();
        int[] newLoc = {location[0], location[1]};
        if(dir.equals("up") && location[0]>1 ){
            newLoc[0]-=1;
        }else if (dir.equals("down") && location[0]<m){
            newLoc[0]+=1;
        }else if (dir.equals("left") && location[1]>0){
            newLoc[1]-=1;
        }else if (dir.equals("right") && location[1]<n){
            newLoc[1]+=1;
        }else if (dir.equals("upleft") && location[0]>1 && location[1]>0){
            newLoc[0]-=1;
            newLoc[1]-=1;
        }else if(dir.equals("upright") && location[0]>1 && location[1]<n){
            newLoc[0]-=1;
            newLoc[1]+=1;
        }else if (dir.equals("downleft") && location[0]<m && location[1]>0){
            newLoc[0]+=1;
            newLoc[1]-=1;
        }else if (dir.equals("downright") && location[0]<m && location[1]<n){
            newLoc[0]+=1;
            newLoc[1]+=1;
        }else this.cantMove();
        if(!body.findHost(newLoc)){
            body.move(location,newLoc);
        }
    }

    @Override
    public void move(int[] newLocation) {
        if(!body.findHost(newLocation)){
            System.out.println(location[0] +""+ location[1] + " moved to " + newLocation[0] + newLocation[1]);
            location = newLocation;
            health -= moveCost;
        }else this.cantMove();
    }

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

    @Override
    public String getGeneticCode() {
        return geneticCode;
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
        List<Host>host = new LinkedList<>();
        int ans = m*n*10;
        String dir = "notfound";
        for (Host h : body.getOrganism()) {
            if(h.getType() == this.getType()){
                if(h != this)
                    host.add(h);
            }
        }
        for(Host h : host) {
            if(location[0] > 1 && h.getLocation()[1] == location[1] && h.getLocation()[0] < location[0] && (location[0] - h.getLocation()[0]) > 0 ) {
                int a = (location[0] - h.getLocation()[0]);
                if (a < ans) {
                    ans = a;
                    dir = "up";
                }
            }else if (location[0] < m && h.getLocation()[1] == location[1] && h.getLocation()[0] > location[0] && h.getLocation()[0] - location[0] > 0) {
                int a = h.getLocation()[0] - location[0];
                if (a < ans) {
                    ans = a;
                    dir = "down";
                }
            }else if (location[1] > 1 && h.getLocation()[0] == location[0] && h.getLocation()[1] < location[1] && location[1] - h.getLocation()[1] > 0) {
                int a = location[1] - h.getLocation()[1];
                if (a < ans) {
                    ans = a;
                    dir = "left";
                }
            }else if (location[1] < n && h.getLocation()[0] == location[0] && h.getLocation()[1] > location[1] && h.getLocation()[1] - location[1] > 0) {
                int a = h.getLocation()[1] - location[1];
                if (a < ans) {
                    ans = a;
                    dir = "right";
                }
            }else if (location[0] > 1 && location[1] > 1 && location[0] - h.getLocation()[0] == location[1] - h.getLocation()[1] && location[0] - h.getLocation()[0] > 0) {
                int a = location[0] - h.getLocation()[0];
                if (a < ans){
                    ans = a;
                    dir  = "upleft";
                }
            }else if (location[0] > 1 && location[1] < n && location[0] - h.getLocation()[0] == h.getLocation()[1] - location[1] && location[0] - h.getLocation()[0] > 0) {
                int a = location[0] - h.getLocation()[0];
                if (a < ans){
                    ans = a;
                    dir  = "upright";
                }
            }else if (location[0] < m && location[1] > 1 && h.getLocation()[0] - location[0] == location[1] - h.getLocation()[1] && h.getLocation()[0] - location[0] > 0) {
                int a = h.getLocation()[0] - location[0];
                if (a < ans){
                    ans = a;
                    dir  = "downleft";
                }
            }else if (location[0] < m && location[1] < n && h.getLocation()[0] - location[0] == h.getLocation()[1] - location[1] && h.getLocation()[0] - location[0] > 0) {
                int a = h.getLocation()[0] - location[0];
                if (a < ans){
                    ans = a;
                    dir  = "downright";
                }
            }
        }
        String dis = String.valueOf(ans);
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
        int ans = 0;
        int[] loc = {location[0] , location[1]};
        switch (dir) {
            case "up":
                while (ans == 0 && loc[0] > 1) {
                    loc[0]--;
                    if (body.findHost(loc))
                        ans = ((location[0] - loc[0]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            case "down":
                while (ans == 0 && loc[0] < m) {
                    loc[0]++;
                    if (body.findHost(loc))
                        ans = ((loc[0] - location[0]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            case "left":
                while (ans == 0 && loc[1] > 1) {
                    loc[1]--;
                    if (body.findHost(loc))
                        ans = ((location[1] - loc[1]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            case "right":
                while (ans == 0 && loc[1] < n) {
                    loc[1]++;
                    if (body.findHost(loc))
                        ans = ((loc[1] - location[1]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            case "upleft":
                while (ans == 0 && loc[0] > 1 && loc[1] > 1) {
                    loc[0]--;
                    loc[1]--;
                    if (body.findHost(loc))
                        ans = ((location[0] - loc[0]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            case "upright":
                while (ans == 0 && loc[0] > 1 && loc[1] < n) {
                    loc[0]--;
                    loc[1]++;
                    if (body.findHost(loc))
                        ans = ((loc[1] - location[1]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            case "downleft":
                while (ans == 0 && loc[0] < m && loc[1] > 1) {
                    loc[0]++;
                    loc[1]--;
                    if (body.findHost(loc))
                        ans = ((location[1] - loc[1]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            case "downright":
                while (ans == 0 && loc[0] < m && loc[1] < n) {
                    loc[0]++;
                    loc[1]++;
                    if (body.findHost(loc))
                        ans = ((loc[0] - location[0]) * 10) + body.findOrganByLocation(loc).getType();
                }
                break;
            default:
                System.out.println("Wrong direction!");
                break;
        }
        System.out.println(ans);
        return ans;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }
}
