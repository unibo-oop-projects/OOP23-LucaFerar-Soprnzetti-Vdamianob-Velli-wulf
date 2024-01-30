package javawulf.model;

import javawulf.model.player.Player;

public interface Collectable extends GameElement {

    public void collect(Player p);

    public void applyEffect(Player p);
}
