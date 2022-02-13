package CARIN.Parser;

public class Statement{
    String type;
    Expr condition;
    Statement then;
    Statement el;
    String direction;
    Expr expression;

    public Statement(String type,Expr con,Statement then, Statement el){
        this.type = type;
        this.condition = con;
        this.then = then;
        this.el = el;
    }
    public Statement(String type,Expr con,Statement then){
        this.type = type;
        this.condition = con;
        this.then = then;
    }
    public Statement(String type, String direction){
        this.type = type;
        this.direction = direction;
    }
    public Statement(String type, Expr expr){
        this.type = type;
        this.expression = expr;
    }
    public void eval() {
        Boolean check;
        check = condition.eval() > 0;
        if (condition.equals("if")) {
            if (check) then.eval();
            else el.eval();
        }else if(condition.equals("while")){
            while (check){
                then.eval();
            }
        }
    }
}
