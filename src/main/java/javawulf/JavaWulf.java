package javawulf;

import javawulf.controller.GameLoop;
import javawulf.controller.GameLoopImpl;

public class JavaWulf {
    public static void main(String[] args) {
        GameLoop loop = new GameLoopImpl();
        loop.startGameLoopThread();
    }
}
