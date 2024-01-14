package graphs;


import java.util.*;

/**
 * We are interested in solving a maze represented
 * by a matrix of integers 0-1 of size nxm.
 * This matrix is a two-dimensional array.
 * An entry equal to '1' means that there
 * is a wall and therefore this position is not accessible,
 * while '0' means that the position is free.
 * We ask you to write a Java code to discover
 * the shortest path between two coordinates
 * on this matrix from (x1, y1) to (x2, y2).
 * The moves can only be vertical (up/down) or horizontal (left/right)
 * (not diagonal), one step at a time.
 * The result of the path is an Iterable of
 * coordinates from the origin to the destination.
 * These coordinates are represented by integers
 * between 0 and n * m-1, where an integer 'a'
 * represents the position x =a/m and y=a%m.
 * If the start or end position is a wall
 * or if there is no path, an empty Iterable must be returned.
 * The same applies if there is no path
 * between the origin and the destination.
 */
public class Maze {
    public static Iterable<Integer> shortestPath(int[][] maze, int x1, int y1, int x2, int y2) {
        // TODO
        // Since we want the shortest path, we'll look at BFS
        // It guaranties to find the shortest path, at the cost of memory efficiency
        if (maze[x1][y1] == 1 || maze[x2][y2] == 1) return Collections.emptyList();

        Queue<Integer> q = new LinkedList<>();

        int n = maze.length;
        int m = maze[0].length;

        int start = ind(x1, y1, m);
        int end = ind(x2, y2, m);
        q.add(start);

        // visited
        boolean[][] visited = new boolean[n][m];
        visited[x1][y1] = true;

        // predecessor array to keep track of the path
        int[] predecessors = new int[n * m];
        Arrays.fill(predecessors, -1);


        while (!q.isEmpty()) {
            System.out.println(q);

            int curr = q.poll();
            int x = row(curr, m);
            int y = col(curr, m);

            visited[x][y] = true;

            if (x == x2 && y == y2) {
                System.out.println("Found");
//                return backtrack(predecessors, start, end, m);
                break;
            }

            // left and right : y - 1 and y + 1
            if (y - 1 >= 0 && maze[x][y - 1] == 0 && !visited[x][y - 1]) {
                int neighbor = ind(x, y-1, m);
                q.add(neighbor);
                predecessors[neighbor] = curr;
                visited[x][y - 1] = true;
            }
            if (y + 1 < m && maze[x][y + 1] == 0 && !visited[x][y + 1]) {
                int neighbor = ind(x,y + 1, m);
                q.add(neighbor);
                predecessors[neighbor] = curr;
                visited[x][y + 1] = true;
            }

            // up and down : x - 1 and x + 1
            if (x - 1 >= 0 && maze[x - 1][y] == 0 && !visited[x - 1][y]) {
                int neighbor = ind(x - 1, y, m);
                q.add(neighbor);
                predecessors[neighbor] = curr;
                visited[x - 1][y] = true;
            }
            if (x + 1 < n && maze[x + 1][y] == 0 && !visited[x + 1][y]) {
                int neighbor = ind(x + 1, y, m);
                q.add(neighbor);
                predecessors[neighbor] = curr;
                visited[x + 1][y] = true;
            }
        }

        return backtrack(predecessors, start, end, m);
    }

    private static Iterable<Integer> backtrack(int[] predecessor, int start, int end, int m)
    {
        List<Integer> path = new ArrayList<>();
        int curr = end;
        while (curr != start) {
            if (curr == -1) return Collections.emptyList();
            path.add(curr);
            curr = predecessor[curr];
        }
        path.add(start);
        Collections.reverse(path);
        System.out.println(path);
        return path;
    }

    public static int ind(int x, int y, int lg) {
        return x * lg + y;
    }

    public static int row(int pos, int mCols) {
        return pos / mCols;
    }

    public static int col(int pos, int mCols) {
        return pos % mCols;
    }


    public static void main(String[] args) {

        int[][] maze1 = new int[][]{{0, 0, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0}, {1, 1, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0}};

        int x1 = 0;
        int y1 = 0;
        int x2 = 6;
        int y2 = 0;
        Iterable<Integer> path = shortestPath(maze1, x1, y1, x2, y2);
        System.out.println(path);

    }
}
