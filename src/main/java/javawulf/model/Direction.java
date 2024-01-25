package javawulf.model;

public enum Direction {
    UP(0.0,-1.0),
    DOWN(0.0,1.0),
    LEFT(-1.0,0.0),
    RIGHT(1.0,0.0),
    UP_LEFT(-Math.cos(Math.PI/4), -Math.sin(Math.PI/4)),
    UP_RIGHT(Math.cos(Math.PI/4), -Math.sin(Math.PI/4)), 
    DOWN_LEFT(-Math.cos(Math.PI/4), Math.sin(Math.PI/4)), 
    DOWN_RIGHT(Math.cos(Math.PI/4), Math.sin(Math.PI/4));

    private final double x;
    private final double y;

    Direction(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
}
