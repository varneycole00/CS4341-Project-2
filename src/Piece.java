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

    public Piece(String Numtype, int width, int strength, int cost) {
        switch(Numtype){
            case "Door":
                type = Type.DOOR;
                break;
            case "Wall":
                type = Type.WALL;
                break;
            case "Lookout":
                type = Type.LOOKOUT;
                break;
        }
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

    @Override
    public String toString() {
        return "Piece{"+type.name() +" "+Width+" "+Strength+" "+Cost+"}";
    }
}
