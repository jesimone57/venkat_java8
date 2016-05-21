import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jsimone on 12/14/15.
 * <p>
 * solution is here:    https://en.wikipedia.org/wiki/Eight_queens_puzzle
 * <p>
 * This is how the wiki page solutions map to the output of this code:
 * <p>
 * wiki solution 1 = 13572064 -> solution 4
 * wiki solution 2 = 06357142 -> solution 6
 * wiki solution 3 = 06471352 -> solution 1
 * wiki solution 4 = 30471625 -> solution 12
 * wiki solution 5 = 40731625 -> solution 10
 * wiki solution 6 = 20647135 -> solution 11
 * wiki solution 7 = 40357162 -> solution 3
 * wiki solution 8 = 60275314 -> solution 7
 * wiki solution 9 = 40752613 -> solution 9
 * wiki solution 10 = 46031752 -> solution 5
 * wiki solution 11 = 52073164 -> solution 2
 * wiki solution 12 = 42061753 -> solution 8
 */
public class NonAttackingQueens {

    public static void main(String[] args) {
        List<String> results = new ArrayList<>();
        permutation("", "01234567", results);

        System.out.println("There are " + results.size() + " solutions.");
        System.out.println(results);


//        System.out.println(results.contains(reverse("04752613")));
//        List<String> uniqueResults = results.stream()
//                .filter(r -> ! results.contains(reverse(r)))
//                .collect(Collectors.toList());
//        System.out.println("There are " + uniqueResults.size() + " solutions.");


//        String solution = "04752613";
//        System.out.println(solution);
//        System.out.println(reverse(solution));
//        System.out.println(flip(solution));
//        System.out.println(reverse(flip(solution)));
//
//        System.out.println(rotate(solution));
//        System.out.println(reverse(rotate(solution)));
//        System.out.println(flip(rotate(solution)));
//        System.out.println(reverse(flip(rotate(solution))));

        Map<String, Set<String>> map = pruneResults(results);
        System.out.println("\nThere are " + map.size() + " unique solutions " +
                "by taking into account all possible rotations and reflections of the board.\n");
        int i = 0;
        for (String key : map.keySet()) {
            System.out.println("Unique Solution " + (++i) + ":  " + key + "  other rotations and refliections: " + map.get(key));
            displayBoard(key);
        }
    }

    private static Map<String, Set<String>> pruneResults(List<String> results) {
        Map<String, Set<String>> pruned = new HashMap<>();
        Set<String> alreadyAccountedFor = new HashSet<>();
        Set<String> similar = null;
        for (String s : results) {
            if (!pruned.containsKey(s)) {
                similar = new TreeSet<>();
            } else {
                similar = pruned.get(s);
            }
            if (!alreadyAccountedFor.contains(s)) {
                similar.add(reverse(s));
                similar.add(flip(s));
                similar.add(reverse(flip(s)));
                similar.add(rotate(s));
                similar.add(reverse(rotate(s)));
                similar.add(flip(rotate(s)));
                similar.add(reverse(flip(rotate(s))));
                pruned.put(s, similar);

                alreadyAccountedFor.add(s);
                alreadyAccountedFor.addAll(similar);
            }
        }
        return pruned;
    }

    private static void displayRow(int n) {
        String[] args = new String[8];
        for (int i = 0; i < 8; i++) {
            args[i] = (i == n ? "Q" : " ");
        }
        System.out.format("| %s | %s | %s | %s | %s | %s | %s | %s |\n", (Object[])args);
        System.out.println("|---|---|---|---|---|---|---|---|");
    }

    private static void displayBoard(String s) {
        System.out.println("|---|---|---|---|---|---|---|---|");
        for (Character c : s.toCharArray()) {
            displayRow(Integer.valueOf(String.valueOf(c)));
        }
        System.out.println("\n");
    }

    private static String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private static String rotate(String s) {
        String[] result = new String[8];
        int col = 0;
        for (Character c : s.toCharArray()) {
            Integer row = Character.getNumericValue(c);
            result[row] = Integer.toString(col);
            col++;
        }
        return Arrays.toString(result).join("", result);
    }

    private static String flip(String s) {
        String result = "";
        for (Character c : s.toCharArray()) {
            Integer i = Character.getNumericValue(c);
            result = result + (7 - i);
        }
        return result;
    }

    private static boolean isNonAttackingOnDiagonal(String s) {
        boolean result = true;
        for (int i = 0; i < s.length(); i++) {
            String s2 = String.valueOf(s.charAt(i));
            int i2 = Integer.valueOf(s2);
            for (int j = 0; j < s.length(); j++) {
                String s3 = String.valueOf(s.charAt(j));
                int j3 = Integer.valueOf(s3);
                if (i != j) {
                    if (Math.abs(i - j) == Math.abs(i2 - j3)) {
                        result = false;
                        break;
                    }
                }
            }
            if (result == false) {
                break;
            }
        }
        return result;
    }

    private static void permutation(String prefix, String str, List<String> results) {
        int n = str.length();
        if (n == 0) {
            if (isNonAttackingOnDiagonal(prefix)) {
                results.add(prefix);
            }
        } else {
            for (int i = 0; i < n; i++) {
                //System.out.println("going in with prefix="+prefix + str.charAt(i)+"     and list="+str.substring(0, i) + str.substring(i + 1, n));
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), results);
            }
        }
    }

    /**
     * Permutes a list
     * @param prefix
     * @param list
     */
    private static void permutation(List<String> prefix, List<String> list) {
        int n = list.size();
        if (n == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                List<String> newList = new ArrayList();
                newList.addAll(list.subList(0, i));
                newList.addAll(list.subList(i + 1, n));

                List<String> prefixList = new ArrayList();
                prefixList.addAll(prefix);
                prefixList.add(list.get(i));
                //System.out.println("going in with prefix="+prefixList+"     and list="+newList);
                permutation(prefixList, newList);
            }
        }
    }
}
