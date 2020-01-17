import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Orca extends Critter {

    private boolean isBlack;
    private int turn = 0;
    private String[] stringOptions = {"A", "a"};
    private boolean turnNextTurn = false;

    public Orca() {
        isBlack = Math.random() < 0.5;
    }

    // always infects the critter in front
    // otherwise it will turn to face a surrounding critter
    // if the critter isn’t facing it already
    // otherwise it will hop
    // if it hits a wall, it will turn around
    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER) return Action.INFECT;
        else if (info.getLeft() == Neighbor.OTHER) {
            // if the critter to my left isn't looking at me, look at them so I
            // can infect them next turn
            if (!sideCritterIsFacingMe(info, true)) return Action.LEFT;
        }
        else if (info.getRight() == Neighbor.OTHER) {
            // if the critter to my right isn't looking at me, look at them so I
            // can infect them next turn
            if (!sideCritterIsFacingMe(info, false)) return Action.RIGHT;
        }
        else if (turnNextTurn) {
            turnNextTurn = false;
            return Action.RIGHT;
        }
        else if (info.getFront() == Neighbor.EMPTY) return Action.HOP;
        else if (info.getFront() == Neighbor.WALL) {
            // turn right for 2 turns if it hits a wall
            turnNextTurn = true;
            return Action.RIGHT;
        }
        // if nothing else happens, just turn right
        return Action.RIGHT;
    }

    // returns weather or not the creature to the side is facing me already
    private boolean sideCritterIsFacingMe(CritterInfo info, boolean critterIsLeft) {

        Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        // make directions into a List so I can use getIndex()
        ArrayList<Direction> dirList = new ArrayList<>(Arrays.asList(directions));

        Direction myDir = info.getDirection();

        // the index of my direction in the directions array
        int myDirIndex = dirList.indexOf(myDir);

        // the index of the direction the other critter
        // would need to be facing to be looking at me
        int facingMeDirIndex = myDirIndex;

        Direction neighborDir;
        if (critterIsLeft) {
            neighborDir = info.getLeftDirection();
            facingMeDirIndex += 1;
        }
        else {
            neighborDir = info.getRightDirection();
            facingMeDirIndex += 3;
        }

        // since the array is N E S W, the direction to the
        // right is always + 1 and the left is - 1 AKA + 3 (if you make it wrap around)
        // % 4 insures it won't try to get an index out of range and
        // instead wraps around to the other side
        Direction facingMeDir = directions[(facingMeDirIndex % 4)];

        // to be facing me, the critter must be looking the
        // direction opposite the direction it is to me
        // if it is to my left, it must be facing right
        return facingMeDir == neighborDir;
    }

    // black or white starting randomly and
    // going back and fourth each turn
    public Color getColor() {
        isBlack = !isBlack;

        if (isBlack) return Color.BLACK;
        return Color.WHITE;
    }

    // switches between “a” and “A” each turn because it
    // kind of looks like a fin going in and out of the water
    public String toString() {
        turn++;
        return stringOptions[turn % 2];
    }
}
