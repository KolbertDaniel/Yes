import java.awt.*;

public class Giant extends Critter{

    private static String[] modes = new String[] {"fee","fie","foe","fum"};
    private int mode = 0;

    public Giant() {}

    public Action getMove(CritterInfo info) {
        mode = (mode+1)%24;
        Neighbor front = info.getFront();

        if (front == Neighbor.OTHER) return Action.INFECT;
        else if (front == Neighbor.EMPTY) return Action.HOP;
        return Action.RIGHT;
    }

    // Giant is grey
    public Color getColor() {
        return Color.GRAY;
    }

    // alternate appearance depending on the mode of the current
    public String toString() {
        return modes[mode/6];
    }
}