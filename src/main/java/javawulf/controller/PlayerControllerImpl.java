package javawulf.controller;

import java.util.Optional;

import javawulf.model.Direction;
import javawulf.model.player.Player;

/**
 * Implementation of PlayerController.
 */
public class PlayerControllerImpl implements PlayerController {

    private Player player;

    /**
     * @param player The instance of Player coming from the Model.
     */
    public PlayerControllerImpl(Player player) {
        this.player = player;
    }

    @Override
    public void updatePlayerStatus(boolean up, boolean down, boolean left, boolean right, boolean attack) {
        Optional<Direction> movementDirection = Optional.empty();
        if (!((up && down) || (left && right))) {
            if (up) {
                if (right) {
                    movementDirection = Optional.of(Direction.UP_RIGHT);
                } else if (left) {
                    movementDirection = Optional.of(Direction.UP_LEFT);
                } else {
                    movementDirection = Optional.of(Direction.UP);
                }
            } else if (down) {
                if (right) {
                    movementDirection = Optional.of(Direction.DOWN_RIGHT);
                } else if (left) {
                    movementDirection = Optional.of(Direction.DOWN_LEFT);
                } else {
                    movementDirection = Optional.of(Direction.DOWN);
                }
            } else if (right) {
                movementDirection = Optional.of(Direction.RIGHT);
            } else if (left) {
                movementDirection = Optional.of(Direction.LEFT);
            }

            if (movementDirection.isPresent()) {
                this.player.move(movementDirection.get());
            }
        }
        if (attack) {
            this.player.attack();
        }
    }
    
}
