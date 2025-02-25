package com.ahu.coding.beast.training.phase_tree;

import java.util.*;

/**
 * @author jianzhang
 * 2025/02/24/下午4:17
 * <p>
 * 定义一个二维数组N*M（其中2<=N<=10;2<=M<=10），如5 × 5数组下所示：
 * int  maze[5][5]   =   {
 * 0,   1,   0,   0,   0,
 * 0,   1,   0,   1,   0,
 * 0,   0,   0,   0,   0,
 * 0,   1,   1,   1,   0,
 * 0,   0,   0,   1,   0,
 * };
 * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的最短路线。入口点为[0,0],既第一空格是可以走的路。
 * <p>
 * 算法要求最后输出依次走过的索引坐标
 */
public class Question38 {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };


        List<String> path1 = new ArrayList<>();
        dfsTrace(maze, 0, 0, new boolean[maze.length][maze[0].length], path1);
        System.out.println(path1);


        List<String> path2 = bfsTrace(maze);
        System.out.println(path2);

    }


    /**
     * DFS
     */
    private static boolean dfsTrace(int[][] maze, int x, int y, boolean[][] visited, List<String> path) {

        int N = maze.length;
        int M = maze[0].length;

        if (x < 0 || x >= N || y < 0 || y >= M || visited[x][y] || maze[x][y] == 1) {
            return false;
        }

        if (x == N - 1 && y == M - 1) {
            path.addLast(x + "," + y);
            return true;
        }

        path.addLast(x + "," + y);
        visited[x][y] = true;

        boolean hasPath = false;
        for (int[] direction : DIRECTIONS) {
            int nextX = direction[0] + x;
            int nextY = direction[1] + y;
            boolean trace = dfsTrace(maze, nextX, nextY, visited, path);
            if (trace) {
                hasPath = true;
                break;
            }
        }

        if (!hasPath) {
            path.removeLast();
            visited[x][y] = false;
        }
        return hasPath;
    }


    /**
     * 在 BFS 中，一旦一个节点被访问，就不会再次访问，因此不需要 visited 数组来防止重复访问。
     */
    private static List<String> bfsTrace(int[][] maze) {
        int N = maze.length;
        int M = maze[0].length;

        Deque<Node> queue = new LinkedList<>();
        // key 为子  value 为父
        Map<Node, Node> parentMap = new HashMap<>();
        // 加入起点
        queue.addLast(new Node(0, 0));


        while (!queue.isEmpty()) {
            Node cur = queue.pollFirst();
            if (cur.x == N - 1 && cur.y == M - 1) {
                return reconstructPath(parentMap, cur);
            }

            for (int[] direction : DIRECTIONS) {
                int nextX = direction[0] + cur.x;
                int nextY = direction[1] + cur.y;

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || maze[nextX][nextY] == 1) {
                    continue;
                }

                Node next = new Node(nextX, nextY);
                queue.addLast(next);

                parentMap.put(next, cur);
            }
        }

        return null;
    }

    private static List<String> reconstructPath(Map<Node, Node> parentMap, Node target) {
        List<String> path = new ArrayList<>();
        Node node = target;

        while (node != null) {
            // 从路径终点逐级找父节点并加到对列头部去
            path.addFirst(node.x + "," + node.y);
            if (node.x == 0 && node.y == 0) {
                break;
            }
            node = parentMap.get(node);
        }
        return path;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


}
