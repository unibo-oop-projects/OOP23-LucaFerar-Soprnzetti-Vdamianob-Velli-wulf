package javawulf.model;

public interface Score {
    public enum Multiplier{
        DEFAULT(1),
        DOUBLE(2);

        public final int value;

        private Multiplier(int value){
            this.value = value;
        }
        
        public int getValue(){
            return value;
        }
    }
    public int getPoints();

    public int getMultiplier();

    public void setMultiplier(Multiplier multiplier);

    public void addPoints(int points);
}
