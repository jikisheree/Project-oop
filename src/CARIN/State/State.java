package CARIN.State;

public abstract class State {
    private static State currentState = null;
    public static void setState(State current){
        currentState = current;
    }
    public static State getState(){
        return currentState;
    }

    public abstract void update();
    public abstract void render();

}
