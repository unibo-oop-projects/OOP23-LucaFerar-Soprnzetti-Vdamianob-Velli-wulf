package javawulf.controller;

import javawulf.model.map.Map;

public interface GameLoop {
    void startGameLoopThread();
    Map getMap();
}