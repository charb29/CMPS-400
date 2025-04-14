package Assignment7;

import java.util.*;

public class Assignment7 {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 5, 1);

        TreeNode shortestPathTree = ShortestPath.Dijkstra(graph, 0);
        System.out.println("\nResult:");
        printTree(shortestPathTree);
        System.out.println("\n");
    }

    public static class ShortestPath {
        public static TreeNode Dijkstra(Graph graph, int source) {
            int[] distances = new int[graph.vertices];
            int[] parent = new int[graph.vertices];

            Arrays.fill(distances, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            parent[source] = 0;

            Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            queue.offer(new int[]{source, 0});

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int u = current[0];
                int d = current[1];
    
                if (d > distances[u]) continue;

                for (Edge edge : graph.adjacencyList.get(u)) {
                    int v = edge.target;
                    int weight = edge.weight;
                    if (distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                        parent[v] = u;
                        queue.offer(new int[]{v, distances[v]});
                    }
                }
            }

            Map<Integer, TreeNode> nodeMap = new HashMap<>();
            for (int i = 0; i < graph.vertices; i++) {
                nodeMap.put(i, new TreeNode(i));
            }
    
            TreeNode root = nodeMap.get(source);
            for (int i = 0; i < graph.vertices; i++) {
                if (i != source && parent[i] != -1) {
                    nodeMap.get(parent[i]).addNode(nodeMap.get(i));
                }
            }

            return root;
        }
    }

    public static void printTree(TreeNode node) {
        System.out.print(node.value + " ");
        for (TreeNode child : node.children) {
            printTree(child);
        }
    }

    public static class Graph {
        int vertices;
        List<List<Edge>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int target, int weight) {
            adjacencyList.get(source).add(new Edge(target, weight));
        }
    }

    private static class Edge {
        int target;
        int weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    private static class TreeNode {
        int value;
        List<TreeNode> children;

        public TreeNode(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public void addNode(TreeNode node) {
            children.add(node);
        }
    }
}