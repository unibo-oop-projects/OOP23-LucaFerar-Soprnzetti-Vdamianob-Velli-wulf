package javawulf.model.item;

import javawulf.model.GameElement;
import javawulf.model.player.Player;

public interface Item extends GameElement {

    void applyEffect(Player p);
}
