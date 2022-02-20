package CARIN;

import CARIN.State.GameState;
import CARIN.State.State;

public class Game implements Runnable{
    private Thread thread;
    private boolean running = false;
    public State gameState;
    public State menuState;

    public Game(){

    }
    private void init(){
        // initialization
        gameState = new GameState();
        State.setState(gameState);
    }
    private void update(){
        // run evaluation
    }
    private void render(){
        gameState.render();
    }
    @Override
    public void run() {
        init();

        int fps = 60;
        long timePerUpdate = 1000000000 / fps;
        long delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int updates = 0;

        while(running){
            now = System.nanoTime();
            delta += (now-lastTime) / timePerUpdate;
            timer += now-lastTime;
            lastTime = now;

            if(delta >= 1) {
                update();
                render();
                updates++;
                delta--;
            }
            if(timer >= 1000000000){
                System.out.println("Updates and frames: "+updates);
                timer = 0;
                updates = 0;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() throws InterruptedException {
        if(!running) return;
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
