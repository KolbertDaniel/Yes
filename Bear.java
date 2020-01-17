import java.awt.*;

public class Bear extends Critter {

    private boolean isPolar;
    private boolean pointLeft = true; // Weather or not the slash should point left or right

    public Bear(boolean polar) {
        isPolar = polar;
    }

    // always infect if an enemy is in front
    // otherwise hop if possible
    // otherwise turn left
    public Action getMove(CritterInfo info) {
        Neighbor front = info.getFront();

        if (front == Neighbor.OTHER) return Action.INFECT;
        else if (front == Neighbor.EMPTY) return Action.HOP;
        return Action.LEFT;
    }

    // white for a polar bear
    // black otherwise
    public Color getColor() {
        if (isPolar) return Color.WHITE;
        return Color.BLACK;
    }

    // alternate on each different move between a slash character and a backslash character
    public String toString() {
        // updates the direction for the next turn
        pointLeft = !pointLeft;

        if (pointLeft) return "\\";
        return "/";
    }
}