package javawulf.model.item;

import javawulf.model.Coordinate;

public interface ItemFactory {

    public AmuletPiece createAmuletPiece(Coordinate position);

    public Cure createCure(Coordinate position);

    public CureMax createCureMax(Coordinate position);

    public ExtraHeart createExtraHeart(Coordinate position);

    public GreatSword createGreatSword(Coordinate position);

    public Minimap createMinimap(Coordinate position);

    public Shield createShield(Coordinate position);

}
