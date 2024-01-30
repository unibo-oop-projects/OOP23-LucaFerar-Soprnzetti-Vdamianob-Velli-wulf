package javawulf.model.enemy;

import javawulf.model.Coordinate;

public class EnemyFactoryImpl implements EnemyFactory{
    
    @Override
    public Pawn createPawn(Coordinate position) {
        return new Pawn(position);
    }

    @Override
    public Guard createGuard(Coordinate position) {
        return new Guard(position);
    }

}
