package javawulf.controller;

import javawulf.model.map.Map;
import javawulf.model.player.Player;

/**
 * GameLoop is an important Controller element: determines the game Time.
 * Number of ticks per seconds are determined by FPS value.
 * In each tick, GameLoop update the state of all game elements: objects, enemies and player.
 * After updating the game element states, GameLoop ask View to redraw graphics.  
 * 
 */
public interface GameLoop {
    /**
     * How many ticks per second the game state and the View representation will be refredhed.
     */
    int FPS = 60;
    /**
     * GameLoop thread will be start only after this method is invoked.
     */
    void startGameLoopThread();
    
    /**
     * @return game Map.
     */
    Map getMap();

    /**
     * @return The Player character
     */
    Player getPlayer();
}