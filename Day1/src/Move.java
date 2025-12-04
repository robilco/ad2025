public class Move {

    boolean isLeft;
    int steps;

    public Move(String move) {
        isLeft = move.charAt(0) == 'L';
        steps = Integer.parseInt(move.substring(1));
    }

    @Override
    public String toString() {
        return (isLeft ? "L" : "R") + steps;
    }
}
