package javawulf.model.item;

import javawulf.model.Coordinate;

public class ItemFactoryImpl implements ItemFactory{

    @Override
    public AmuletPiece createAmuletPiece(Coordinate position) {
        return new AmuletPiece(position);
    }

    @Override
    public Cure createCure(Coordinate position) {
        return new Cure(position);
    }

    @Override
    public CureMax createCureMax(Coordinate position) {
        return new CureMax(position);
    }

    @Override
    public ExtraHeart createExtraHeart(Coordinate position) {
        return new ExtraHeart(position);
    }

    @Override
    public GreatSword createGreatSword(Coordinate position) {
        return new GreatSword(position);
    }

    @Override
    public Minimap createMinimap(Coordinate position) {
        return new Minimap(position);
    }

    @Override
    public Shield createShield(Coordinate position) {
        return new Shield(position);
    }
    
}
