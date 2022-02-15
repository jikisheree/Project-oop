package CARIN.Parser;

import java.util.List;

public class Statement implements Program{
    String type;
    Expr condition;
    Program then;
    Program el;
    List<Program> statement;
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
    // block constructor
    public Statement(String type,Program block, List<Program> statement){
        this.type = type;
        then = block;
        this.statement = statement;
    }
    public Statement(String type){
        this.type = type;
    }

    public void eval() {
        Boolean check = null;
        if(condition!=null)
            check = condition.eval() > 0;
        switch (type) {
            case "if":
                if (Boolean.TRUE.equals(check)) then.eval();
                else el.eval();
                break;
            case "while":
                while (Boolean.TRUE.equals(check)) {
                    then.eval();
                }
                break;
            case "block":
                statement.add(then);
                break;
            default:
                break;
        }
    }
}
