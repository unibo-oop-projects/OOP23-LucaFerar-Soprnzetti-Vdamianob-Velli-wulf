package javawulf.model.enemy;

import javawulf.model.Coordinate;

public interface EnemyFactory {

    public Pawn createPawn(Coordinate position);

    public Guard createGuard(Coordinate position);

}
