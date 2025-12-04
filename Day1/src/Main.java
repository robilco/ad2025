import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            final List<String> lines = readInputFile();
            List<Move> moves = toMoves(lines);
            
            int start = 50;
            int zeros = 0;
            System.out.println("The dial starts by pointing at " + start);
            for (Move move : moves) {
                int[] adjustments = adjustNumber(start, move);
                start = adjustments[0];
                zeros += adjustments[1];
                String txt = "";
                if (adjustments[1] > 0) {
                    txt = "; during this rotation, it points at 0 " + adjustments[1] + " times";
                }
                System.out.println("The dial is rotated: " + move + " to point at " + start +"" + txt );
                if (start == 0) {
                    zeros++;
                }
            }
            System.out.println("Total zero count is " + zeros);

            //password method 0x434C49434B
            //System.out.println("The password is " + Integer.toHexString(zeros));
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the contents of the input.dat file into a list of Strings.
     * Each line in the file becomes a separate String in the list.
     *
     * @return List of Strings containing the file contents
     * @throws IOException if the file cannot be read
     */
    public static List<String> readInputFile() throws IOException {
        return Files.readAllLines(Paths.get("src/input.dat"));
    }

    public static List<Move> toMoves(List<String> lines) {
        return lines.stream().map(Move::new).toList();

    }

    /**
     * Adjusts a number by adding or subtracting places with wrapping between 0 and 99.
     * If the number goes below 0, it wraps to 99.
     * If the number goes above 99, it wraps to 0.
     *
     * @param startingNumber the initial number (0-99)
     * @param move The move
     * @return the adjusted number with wrapping (0-99)
     */
    public static int[] adjustNumber(int startingNumber, Move move) {
        int moves = move.steps % 100;
        int passesZero = move.steps / 100;
        int result = move.isLeft ? startingNumber - moves : startingNumber + moves;
        // Use modulo to wrap the number between 0 and 99

        if (result > 100) {
            passesZero++;
        }
        result = result % 100;
        // Handle negative numbers by adding 100
        if (result < 0) {
            if (startingNumber != 0) {
                passesZero++;
            }
            result += 100;
        }

        return new int[]{result, passesZero};
    }
}