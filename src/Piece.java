public class Piece {

    private PieceType type;
    private int Width;
    private int Strength;
    private int Cost;

    public Piece(String Numtype, int width, int strength, int cost) {
        switch (Numtype) {
            case "Door":
                type = PieceType.DOOR;
                break;
            case "Wall":
                type = PieceType.WALL;
                break;
            case "Lookout":
                type = PieceType.LOOKOUT;
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

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
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
        return "Piece{" + type.name() + " " + Width + " " + Strength + " " + Cost + "}";
    }
}
