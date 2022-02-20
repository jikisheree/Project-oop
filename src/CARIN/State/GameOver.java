package CARIN.State;

import CARIN.Game;

public class GameOver extends State{

    Game game;

    public GameOver(Game game){
        this.game = game;
    }
    @Override
    public void update() {
        game.running = false;
    }

    @Override
    public void render() {

    }
}
