package javawulf.model.enemy;

import javawulf.model.Coordinate;

public class EnemyFactory {

    public enum EnemyType {
        PAWN, 
        GUARD
    }
    
    public Enemy orderEnemy(EnemyType type, Coordinate position, Integer speed, int points){
        Enemy enemy = createEnemy(type, position, speed, points);
        return enemy;
    }

    private Enemy createEnemy(EnemyType type, Coordinate position, Integer speed, int points) {
        switch (type) {
            case PAWN:
                return new Pawn(position, speed, points);
            case GUARD:
                return new Guard(position, speed, points);
            default:
                throw new IllegalArgumentException("Invalid enemy type");
        }
    }

}
