package CARIN.Model;

public class HostImp implements Host{

    int health, attackDamage, gain;
    int[] location = new int[2];
    String geneticCode;

    @Override
    public void shoot(String location) {

    }

    @Override
    public void move(String newLocation) {
        String dir = newLocation.toLowerCase();
        if(dir.equals("up")){
            location[0]-=1;
        }else if (dir.equals("down")){
            location[0]+=1;
        }else if (dir.equals("left")){
            location[1]-=1;
        }else if (dir.equals("right")){
            location[1]+=1;
        }else if (dir.equals("upleft")){
            location[0]-=1;
            location[1]-=1;
        }else if(dir.equals("upright")){
            location[0]-=1;
            location[1]+=1;
        }else if (dir.equals("downleft")){
            location[0]+=1;
            location[1]-=1;
        }else if (dir.equals("downright")){
            location[0]+=1;
            location[1]+=1;
        }
    }

    public void move(int[] newLocation){

    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int damage) {
        health-=damage;
    }

    // Parser needed here (protected declaration)
}