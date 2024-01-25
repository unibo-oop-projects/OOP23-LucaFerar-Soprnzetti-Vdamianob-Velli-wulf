package javawulf.model;

import javawulf.model.player.Player;

public interface Item extends GameElement {

    void applyEffect(Player p);
}
