enum Type {
    DOOR,
    WALL,
    LOOKOUT
}

public class Piece {

    Type type;
    int Width;
    int Strength;
    int Cost;

    public Piece(Type type, int width, int strength, int cost) {
        this.type = type;
        Width = width;
        Strength = strength;
        Cost = cost;
    }

    public int getCost() {
        return Cost;
    }

    public int getStrength() {
        return Strength;
    }

    public int getWidth() {
        return Width;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public void setStrength(int strength) {
        Strength = strength;
    }

    public void setCost(int cost) {
        Cost = cost;
    }
}
