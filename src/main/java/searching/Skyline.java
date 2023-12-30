package searching;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * In this exercise, you must compute the skyline defined by a set of buildings.
 * When viewed from far away, the buildings only appear as if they were on a two-dimensionnal line.
 * Hence, they can be defined by three integers: The start of the building (left side), the height
 * of the building and the end of the building (right side).
 * For example, a building defined by (2, 5, 4) would look like
 *
 *   xxx
 *   xxx
 *   xxx
 *   xxx
 *   xxx
 * ________
 *
 * Obviously in practice buildings are not on a line, so they can overlap. If we add a new, smaller,
 * building in front of the previous one, defined by (3, 3, 6), then the view looks like:
 *
 *   xxx
 *   xxx
 *   xyyyy
 *   xyyyy
 *   xyyyy
 * ________
 *
 * The skyline is then define as the line that follows the highest building at any given points.
 * Visually, for the above example, it gives:
 *
 *   sss
 *
 *      ss
 *
 *
 * ________
 *
 *
 * We ask you to compute, given a set of building, their skyline.
 */
public class Skyline {


    /**
     *   The buildings are defined with triplets (left, height, right).
     *         int[][] buildings = {{1, 11, 5}, {2, 6, 7}, {3, 13, 9}, {12, 7, 16}, {14, 3, 25}, {19, 18, 22}, {23, 13, 29}, {24, 4, 28}};
     *
     *         [{1,11},{3,13},{9,0},{12,7},{16,3},{19,18},{22,3},{23,13},{29,0}]
     *
     * @param buildings
     * @return the skyline in the form of a list of "key points [x, height]".
     *          A key point is the left endpoint of a horizontal line segment.
     *          The key points are sorted by their x-coordinate in the list.
     */
    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> heights = new ArrayList<>(buildings.length * 2);

        // Break down buildings into vertexes
        // Starting points have positive heights; Ending points have negative heights
        for (int[] b : buildings) {
            heights.add(new int[]{b[0], -b[1]});
            heights.add(new int[]{b[2], b[1]});
        }

        heights.sort((x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1]; // compare by heights
            }
            return x[0] - y[0];
        });

        List<int[]> skyline = new ArrayList<>();
        PriorityQueue<Integer> heightsPQ = new PriorityQueue<>(Comparator.reverseOrder());
        heightsPQ.add(0);
        int prevHeight = 0;

        for (int[] h : heights) {
            if (h[1] < 0) {
                heightsPQ.add(-h[1]);
            } else {
                heightsPQ.remove(h[1]);
            }

            Integer currentHighestHeight = heightsPQ.peek();
            if (currentHighestHeight != null && prevHeight != currentHighestHeight) {
                skyline.add(new int[]{h[0], currentHighestHeight});
                prevHeight = currentHighestHeight;
            }
        }

        return skyline;
    }

    public static void main(String[] args) {
        int[][] buildings = {{1, 11, 5}, {2, 6, 7}, {3, 13, 9}, {12, 7, 16}, {14, 3, 25}, {19, 18, 22}, {23, 13, 29}, {24, 4, 28}};
        List<int[]> skyline = getSkyline(buildings);
        for (int[] point : skyline) {
            System.out.println(Arrays.toString(point));
        }
    }
}
