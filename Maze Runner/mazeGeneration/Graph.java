package mazeGeneration;

import java.util.*;

class Graph {
    private final int vertexCount;
    private final LinkedList<Edge>[] adjacencyList;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;

        adjacencyList = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addGraphEdges(int[][] vertexes) {
        Random random = new Random();
        addFirstRow(vertexes[0], random);
        addFirstColumn(vertexes, random);
        addInnerEdges(vertexes, random);
    }

    private void addFirstRow(int[] firstRow, Random random) {
        for (int j = 1; j < firstRow.length; j++) {
            addEdge(firstRow[j - 1], firstRow[j], random.nextInt(50));
        }
    }

    private void addEdge(int source, int destination, int weight) {
        adjacencyList[source].addFirst(new Edge(source, destination, weight));
        adjacencyList[destination].addFirst(new Edge(destination, source, weight));
    }

    private void addFirstColumn(int[][] vertexes, Random random) {
        for (int i = 1; i < vertexes.length; i++) {
            addEdge(vertexes[i - 1][0], vertexes[i][0], random.nextInt(50));
        }
    }

    private void addInnerEdges(int[][] vertexes, Random random) {
        for (int i = 1; i < vertexes.length; i++) {
            for (int j = 1; j < vertexes[i].length; j++) {
                addEdge(vertexes[i][j - 1], vertexes[i][j], random.nextInt(50));
                addEdge(vertexes[i - 1][j], vertexes[i][j], random.nextInt(50));
            }
        }
    }


    public TreeMap<Integer, Integer> getUsedEdgesByPrim() {
        int[] parents = getParentsOfNodesByPrim();
        return getAllUsedEdgesFromParents(parents);
    }

    private int[] getParentsOfNodesByPrim() {
        boolean[] inPriorityQueue = new boolean[vertexCount];
        int[] parents = new int[vertexCount];
        HeapNode[] heapNodes = new HeapNode[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            inPriorityQueue[i] = true;
            heapNodes[i] = new HeapNode(i, Integer.MAX_VALUE);
            parents[i] = -1;
        }

        heapNodes[0].key = 0;
        PriorityQueue<HeapNode> pq = new PriorityQueue<>(vertexCount, Comparator.comparingInt(o -> o.key));
        for (int i = 0; i < vertexCount; i++) {
            pq.offer(heapNodes[i]);
        }

        while (!pq.isEmpty()) {
            HeapNode node = pq.poll();
            int vertex = node.vertex;
            inPriorityQueue[vertex] = false;

            for (Edge edge : adjacencyList[vertex]) {
                if (inPriorityQueue[edge.destination]) {
                    int destination = edge.destination;
                    int newKey = edge.weight;

                    if (heapNodes[destination].key > newKey) {
                        decreaseKey(pq, newKey, destination);
                        parents[destination] = vertex;
                        heapNodes[destination].key = newKey;
                    }
                }
            }
        }

        return parents;
    }

    private void decreaseKey(PriorityQueue<HeapNode> queue, int newKey, int vertex) {
        for (HeapNode heapNode : queue) {
            if (heapNode.vertex == vertex) {
                queue.remove(heapNode);
                heapNode.key = newKey;
                queue.offer(heapNode);
                break;
            }
        }
    }

    private TreeMap<Integer, Integer> getAllUsedEdgesFromParents(int[] parents) {
        TreeMap<Integer, Integer> usedEdges = new TreeMap<>();
        for (int i = 0; i < parents.length; i++) {
            usedEdges.put(i, parents[i]);
        }

        return usedEdges;
    }
}
