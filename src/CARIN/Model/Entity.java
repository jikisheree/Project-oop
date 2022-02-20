package CARIN.Model;

public abstract class Entity {

    protected float x, y;

    public Entity(){
//        this.x = x;
//        this.y = y;
    }
    public abstract void update();
    public abstract void render();
}
