package CARIN.Parser;

public class Move implements Expr{
    Expr expression;
    String con;

    public Move(String con,Expr expr){
        this.con = con;
        this.expression = expr;
    }

    @Override
    public int eval() {
        return 0;
    }
}
