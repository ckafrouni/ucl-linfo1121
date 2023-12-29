package sorting;

import java.util.Arrays;

/**
 * Let a be an array of integers.
 * In this exercise we are interested in finding
 * the two entries i and j such that a[i] + a[j] is the closest from a target x.
 * In other words, there are no entries k,l such that |x - (a[i] + a[j])| > |x - (a[k] + a[l])|.
 * Note that we can have i = j.
 * Your program must return the values a[i] and a[j].
 *
 * For example let a = [5, 10, 1, 75, 150, 151, 155, 18, 75, 50, 30]
 *
 * then for x = 20, your program must return the array [10, 10],
 *      for x = 153 you must return [1, 151] and
 *      for x = 170 you must return [18, 151]
 */
public class ClosestPair {

    /**
     * Finds the pair of integers which sums up to the closest value of x
     *
     * @param a the array in which the value are looked for
     * @param x the target value for the sum
     */
    public static int[] closestPair(int[] a, int x) {
        Arrays.sort(a);

        int nOp = 0;

        int i = 0;
        int j = a.length - 1;

        int[] closestPair = {a[i], a[j]};
        int closestDiff = Math.abs(a[i] + a[j] - x);

        while (i <= j) {
            nOp++;

            int sum = a[i] + a[j];
            int diff = Math.abs(sum - x);

            if (diff < closestDiff) {
                closestDiff = diff;

                closestPair[0] = a[i];
                closestPair[1] = a[j];
            }

            if (diff == 0) break;

            if (sum > x) {
                j--;
            } else if (sum < x) {
                i++;
            }
        }

//        System.out.println("a = " + Arrays.toString(a));
//        System.out.println("size = " + a.length);
//        System.out.println("nOp = " + nOp);
//        System.out.println("pair = " + Arrays.toString(closestPair));
//        System.out.println("-");
        return closestPair;
    }
}
