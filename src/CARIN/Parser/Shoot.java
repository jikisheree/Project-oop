package CARIN.Parser;

public class Shoot implements Expr {
    Expr expression;
    String con;

    public Shoot(String con,Expr expr){
        this.con = con;
        this.expression = expr;
    }

    @Override
    public int eval() {
        return 0;
    }
}
