package CARIN.Parser;

public class Statement implements Program{
    String type;
    Expr condition;
    Program then;
    Program el;
    // if constructor
    public Statement(String type,Expr con,Program then, Program el){
        this.type = type;
        this.condition = con;
        this.then = then;
        this.el = el;
    }
    // while constructor
    public Statement(String type,Expr con,Program then){
        this.type = type;
        this.condition = con;
        this.then = then;
    }
    public Statement(String type){
        this.type = type;
    }

    public void eval() {
        Boolean check = null;
        if(condition!=null)
            check = condition.eval() > 0;
        if (type.equals("if")) {
            if (check) then.eval();
            else el.eval();
        }else if(type.equals("while")){
            while (check){
                then.eval();
            }
        }else{}
    }
}
