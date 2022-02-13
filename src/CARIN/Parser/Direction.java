package CARIN.Parser;

public class Direction implements Expr {
    String dir;
    public Direction(String dir){
        this.dir = dir;
    }

    @Override
    public int eval() {
        if(dir == "up") return 11;
        else if(dir == "upright") return 12;
        else if (dir == "right") return 13;
        else if (dir == "downright") return 14;
        else if (dir == "down") return 15;
        else if (dir == "downleft") return 16;
        else if (dir == "left") return 17;
        else if (dir == "upleft") return 18;
        else return 0;
    }
}
