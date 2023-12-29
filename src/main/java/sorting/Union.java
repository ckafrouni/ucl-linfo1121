package sorting;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pierre Schaus
 * <p>
 * Given an array of (closed) intervals, you are asked to implement the union operation.
 * This operation will return the minimal array of sorted intervals covering exactly the union
 * of the points covered by the input intervals.
 * For example, the union of intervals [7,9],[5,8],[2,4] is [2,4],[5,9].
 * The Interval class allowing to store the intervals is provided
 * to you.
 *
 */
public class Union {

    /**
     * A class representing an interval with two integers. Hence the interval is
     * [min, max].
     */
    public static class Interval implements Comparable<Union.Interval> {

        public final int min;
        public final int max;

        public Interval(int min, int max) {
            assert (min <= max);
            this.min = min;
            this.max = max;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            return ((Union.Interval) obj).min == min && ((Union.Interval) obj).max == max;
        }

        @Override
        public String toString() {
            return "[" + min + "," + max + "]";
        }

        @Override
        public int compareTo(Union.Interval o) {
            if (min < o.min) return -1;
            else if (min == o.min) return max - o.max;
            else return 1;
        }
    }

    /**
     * Returns the union of the intervals given in parameters.
     * This is the minimal array of (sorted) intervals covering
     * exactly the same points than the intervals in parameter.
     *
     * @param intervals the intervals to unite.
     */
    public static Interval[] union(Interval[] intervals) {
        // TODO
        if (intervals.length == 0) return new Interval[]{};

        Arrays.sort(intervals);
        List<Interval> result = new ArrayList<>();

        int min = intervals[0].min;
        int max = intervals[0].max;
        Interval current = new Interval(min, max);

        for (int i = 1; i < intervals.length; ++i) {
            if (overlap(current, intervals[i])) {
                System.out.println(current + " " + intervals[i]);
                current = combine(current, intervals[i]);
            } else {
                result.add(current);
                min = intervals[i].min;
                max = intervals[i].max;
                current = new Interval(min, max);
            }
        }

        result.add(current);

        return result.toArray(new Interval[0]);
    }

    private static boolean overlap(Interval a, Interval b) {
        return a.max >= b.min;
    }

    private static Interval combine(Interval a, Interval b) {
        assert (overlap(a, b) && a.compareTo(b) <= 0);
        return new Interval(a.min, Math.max(a.max, b.max));
    }
}
