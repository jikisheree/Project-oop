package CARIN.Parser;

public class Parser {

    private Tokenizer tkz;
    private Expr AST;

    public Parser(String src){
        this.tkz = new Tokenizer(src);
        AST = parse();
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
    public Expr parseStatement() throws SyntaxError{
        Expr e = parseCommand();
        while(tkz.peek("if")||tkz.peek("while")||tkz.peek("{")){
            if(tkz.peek("if")){
                tkz.consume();
                e = parseIf();
            }else if(tkz.peek("while")){
                tkz.consume();
                e = parseWhile();
            }else{

                e = parseBlock();
            }
        }
        return e;
    }
    // Command → AssignmentStatement | ActionCommand
    // AssignmentStatement → <identifier> = Expression
    public Expr parseCommand() throws SyntaxError{
        return null;
    }
    // ActionCommand → MoveCommand | AttackCommand
    // MoveCommand → move Direction
    // AttackCommand → shoot Direction
    public Expr parseAction() throws SyntaxError{
        return null;
    }
    // Direction → left | right | up | down | upleft | upright | downleft | downright
    public Expr parseDirection() throws SyntaxError{
//        while (tkz.peek("left")||tkz.peek("right")||tkz.peek("up")||tkz.peek("down")
//        ||tkz.peek("upleft")||tkz.peek("upright")||tkz.peek("downleft")||tkz.peek("downright")){
//            if(tkz.peek("left"))
//        }
        return null;
    }
    // BlockStatement → { Statement* }
    public Expr parseBlock() throws SyntaxError{
        return null;
    }
    // IfStatement → if ( Expression ) then Statement else Statement
    public Expr parseIf() throws SyntaxError{
        Expr e;
        String con = tkz.consume();
        tkz.consume("(");
        e = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Statement s = new Statement(con, e);
        return null;
    }
    // WhileStatement → while ( Expression ) Statement
    public Expr parseWhile() throws SyntaxError{
        return null;
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
        return null;
    }

    public int eval(){
        return AST.eval();
    }

}
