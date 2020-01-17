import java.awt.*;

public class Lion extends Critter {

    private int turnsWithColor = 0;
    private Color pickedColor;
    private static Color[] colorOptions = {Color.RED, Color.GREEN, Color.BLUE};

    public Lion() {}

    // always infect if an enemy is in front
    // otherwise if a wall is in front or to the right, then turn left
    // otherwise if a fellow Lion is in front, then turn right
    // otherwise hop
    public Action getMove(CritterInfo info) {
        Neighbor front = info.getFront();

        if (front == Neighbor.OTHER) return Action.INFECT;
        else if (front == Neighbor.WALL || info.getRight() == Neighbor.WALL) return Action.LEFT;
        else if (front == Neighbor.SAME) return Action.RIGHT;
        return Action.HOP;
    }

    // randomly picks one of three colors (red, green, blue)
    // and uses that color for three moves
    public Color getColor() {
        // updates to a random color every 3 turns
        if (turnsWithColor >= 3) {
            turnsWithColor = 0;
            pickedColor = colorOptions[(int)(Math.random() * 3)];
        }

        turnsWithColor++;
        return pickedColor;
    }

    public String toString() {
        return "L";
    }
}
