package CARIN.Parser;

import CARIN.Model.Host;

import java.util.HashMap;
import java.util.Map;

public class Command implements Program {

    String type;
    String direction;
    Expr expression;
    Host host;
    Map<String, Expr> identifier;

    public Command(String type, String direction, Host host){
        this.type = type;
        this.direction = direction;
        this.host = host;
    }
    public Command(String type, Expr expr, HashMap<String, Expr> identifier){
        this.type = type;
        this.expression = expr;
    }

    @Override
    public void eval() {
        if(type.equals("move")){
            host.move(direction);
//            System.out.println("move");
        }else if(type.equals("shoot")){
            host.shoot(direction);
//            System.out.println("shoot");
        }else{
            // assignment statement
            identifier.put(type, expression);
        }
    }
}
