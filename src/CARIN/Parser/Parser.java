package CARIN.Parser;

import CARIN.Model.Host;

public class Parser {

    private Tokenizer tkz;
    private Expr AST;
    private Host host;

    public Parser(String src, Host host){
        this.tkz = new Tokenizer(src);
        AST = parse();
        this.host = host;
    }

    public Expr parse() throws SyntaxError{
        Expr result = parse();
        if(tkz.peek().equals(""))
            return result;
        else
            throw new SyntaxError();
    }

    public boolean isNumber(String s) throws NumberFormatException{
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    // Statement → Command | BlockStatement | IfStatement | WhileStatement
    public Statement parseStatement() throws SyntaxError{
        Statement s = parseCommand();
            if(tkz.peek("if")) s = parseIf();
            else if(tkz.peek("while")) s = parseWhile();
            else if(tkz.peek("{")) s = parseBlock();
        return s;
    }

    // BlockStatement → { Statement* }
    public Statement parseBlock() throws SyntaxError{
        tkz.consume("{");
        Statement statement = parseStatement();
        tkz.consume("}");
        return statement;
    }
    // IfStatement → if ( Expression ) then Statement else Statement
    public Statement parseIf() throws SyntaxError{
        tkz.consume("if");
        tkz.consume("(");
        Expr condition = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Statement then = parseStatement();
        tkz.consume("else");
        Statement el = parseStatement();
        return new Statement("if", condition, then, el);
    }
    // WhileStatement → while ( Expression ) Statement
    public Statement parseWhile() throws SyntaxError{
        tkz.consume("while");
        tkz.consume("(");
        Expr condition = parseExpression();
        tkz.consume(")");
        Statement then = parseStatement();
        return new Statement("while", condition, then);
    }
    // Command → AssignmentStatement | ActionCommand
    // AssignmentStatement → <identifier> = Expression
    public Statement parseCommand() throws SyntaxError{
        Statement s;
        String identifier = null;
        if(tkz.peek("move") || tkz.peek("shoot")){
            s = parseAction();
        }else {
            identifier = tkz.consume();
            s = new Statement(identifier, parseExpression());
        }
        return s;
    }
    // ActionCommand → MoveCommand | AttackCommand
    // MoveCommand → move Direction
    // AttackCommand → shoot Direction
    public Statement parseAction() throws SyntaxError{
        Statement action = null;
            if(tkz.peek("move")){
                tkz.consume();
                String dir = parseDirection();
                action = new Statement("move", dir);
            }else if(tkz.peek("shoot")){
                tkz.consume();
                String dir = parseDirection();
                action = new Statement("shoot", dir);
            }
            return action;
    }
    // Direction → left | right | up | down | upleft | upright | downleft | downright
    public String parseDirection() throws SyntaxError{
        String dir = tkz.consume();
        return dir;
    }
    // Expression → Expression + Term | Expression - Term | Term
    public Expr parseExpression() throws SyntaxError{
        Expr e = parseTerm();

        while (tkz.peek("+") || tkz.peek("-")){
            if (tkz.peek("+")){
                tkz.consume();
                e = new Arithmetic(e,"+",parseTerm());
            }else{
                tkz.consume();
                e = new Arithmetic(e, "-", parseTerm());
            }
        }
        return e;
    }
    // Term → Term * Factor | Term / Factor | Term % Factor | Factor
    public Expr parseTerm() throws SyntaxError{
        Expr e = parseFactor();
        while(tkz.peek("*") || tkz.peek("/") || tkz.peek("%")){
            if(tkz.peek("*")){
                tkz.consume();
                e = new Arithmetic(e,"*",parseFactor());
            }else if(tkz.peek("/")){
                tkz.consume();
                e = new Arithmetic(e,"/",parseFactor());
            }else{
                tkz.consume();
                e = new Arithmetic(e,"%",parseFactor());
            }
        }
        return  e;
    }
    // Factor → Power ^ Factor | Power
    public Expr parseFactor() throws SyntaxError{
        Expr e = parsePower();
        while(tkz.peek("^")){
                e = new Arithmetic(e,"^",parsePower());
        }
        return  e;
    }
    // Power → <number> | <identifier> | ( Expression ) | SensorExpression
    public Expr parsePower() throws SyntaxError{
        Expr e = parseSensor();
        while(tkz.peek("(") || isNumber(tkz.peek())) {
            if (isNumber(tkz.peek())) {
                return new Number(Integer.parseInt(tkz.consume()));
            } else {
                tkz.consume("(");
                e = parseExpression();
                tkz.consume(")");
                return e;
            }
        }
        return e;
    }
    // SensorExpression → virus | antibody | nearby Direction
    public Expr parseSensor() throws SyntaxError{
        if(tkz.peek("virus")){
            tkz.consume();
            return new SensorExp("virus",host);
        }else if(tkz.peek("antibody")) {
            tkz.consume();
            return new SensorExp("antibody", host);
        }else if(tkz.peek("nearby")){
            tkz.consume();
            String dir = parseDirection();
            return new SensorExp(dir, host);
        }else throw new SyntaxError(tkz.consume());
    }

    public int eval(){
        return AST.eval();
    }

}
