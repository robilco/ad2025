

public class Main {

    final static String DATA = "655-1102,2949-4331,885300-1098691,1867-2844,20-43," +
            "4382100-4484893,781681037-781860439,647601-734894,2-16,180-238," +
            "195135887-195258082,47-64,4392-6414,6470-10044,345-600," +
            "5353503564-5353567532,124142-198665,1151882036-1151931750," +
            "6666551471-6666743820,207368-302426,5457772-5654349,72969293-73018196," +
            "71-109,46428150-46507525,15955-26536,65620-107801,1255-1813,427058-455196," +
            "333968-391876,482446-514820,45504-61820,36235767-36468253,23249929-23312800," +
            "5210718-5346163,648632326-648673051,116-173,752508-837824,";

    public static void main(String[] args) {


        try {
            final String[] ranges = DATA.split(",");

            long sumInvalids = 0;
            for (String range : ranges) {
                final String[] split = range.split("-");
                final long min = Long.parseLong(split[0]);
                final long max = Long.parseLong(split[1]);

                for (long i = min; i <= max; i++) {
                    if (isInvalid2(i)) {
                        System.out.println(range+ " has invalid ID: " + i);
                        sumInvalids += i;
                    }
                }
            }

            System.out.println("Sum of ll invalid ID: " + sumInvalids);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isInvalid(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();

        // Check if length is even (required for a sequence repeated twice)
        if (len % 2 != 0) {
            return false;
        }

        // Split the string in half and compare
        int halfLen = len / 2;
        String firstHalf = idStr.substring(0, halfLen);
        String secondHalf = idStr.substring(halfLen);

        return firstHalf.equals(secondHalf);
    }

    private static boolean isInvalid2(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();

        // Try all possible pattern lengths from 1 to len/2
        // The pattern must repeat at least twice, so max pattern length is len/2
        for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
            // Check if the total length is divisible by the pattern length
            if (len % patternLen == 0) {
                String pattern = idStr.substring(0, patternLen);
                boolean isRepeating = true;

                // Check if the entire string is made up of this pattern repeated
                for (int i = patternLen; i < len; i += patternLen) {
                    String segment = idStr.substring(i, i + patternLen);
                    if (!segment.equals(pattern)) {
                        isRepeating = false;
                        break;
                    }
                }

                // If we found a repeating pattern, the ID is invalid
                if (isRepeating) {
                    return true;
                }
            }
        }

        return false;
    }

}