import java.awt.*;

public class YoloDude extends Critter {

    private static String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    private static Color[] colors = {Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.GREEN};
    private static Action[] actions = {Action.RIGHT, Action.LEFT, Action.INFECT, Action.HOP};

    public Action getMove(CritterInfo info) {
        return actions[(int)(Math.random() * actions.length)];
    }

    public Color getColor() {
        return colors[(int)(Math.random() * colors.length)];
    }

    public String toString() {
        return letters[(int)(Math.random() * letters.length)];
    }
}
