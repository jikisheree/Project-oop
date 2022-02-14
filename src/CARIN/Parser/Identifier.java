package CARIN.Parser;

import java.util.HashMap;

public class Identifier implements Expr{
    String identifier;
    HashMap<String,Expr> idenKeep;

    public Identifier(String identifier, HashMap<String,Expr> idenKeep){
        this.identifier = identifier;
        this.idenKeep = idenKeep;
    }
    @Override
    public int eval() {
        return idenKeep.get(identifier).eval();
    }
}
