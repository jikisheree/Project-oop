package CARIN.Parser;

public class Statement{
    Expr expression;
    String con;

    public Statement(String con,Expr expr){
        this.con = con;
        this.expression = expr;
    }
    public void eval() {
        Boolean check;
        check = expression.eval() > 0;
        if (con.equals("if")) {
            if (check) {

            }
        }
    }
}
