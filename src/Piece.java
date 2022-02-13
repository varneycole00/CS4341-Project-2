import java.util.Objects;

public class Piece {

    private PieceType type; //Type of piece
    private int Width; //Width of piece
    private int Strength; //Strength to have pieces placed above
    private int Cost; //Cost to use

    /**
     * Creates a new piece with set properties
     *
     * @param Numtype  Type of piece (Door, Wall, Lookout)
     * @param width    Width of piece
     * @param strength Strength of piece
     * @param cost     Cost of piece
     */
    public Piece(String Numtype, int width, int strength, int cost) {
        switch (Numtype) { //Sets the piece to the corresponding PieceType
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

    /**
     * @return Cost of piece
     */
    public int getCost() {
        return Cost;
    }

    /**
     * @return Strength of piece
     */
    public int getStrength() {
        return Strength;
    }

    /**
     * @return Width of piece
     */
    public int getWidth() {
        return Width;
    }

    /**
     * @return Type of piece
     */
    public PieceType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Piece{" + type.name() + " " + Width + " " + Strength + " " + Cost + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Width == piece.Width && Strength == piece.Strength && Cost == piece.Cost && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, Width, Strength, Cost);
    }
}
