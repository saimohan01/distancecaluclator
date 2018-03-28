package com.barclays.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.barclays.node.NodeAssociations;
import com.barclays.node.NodeDetails;

public class NodeGraph {

	   //adjacency list representation of graph
    private Map<NodeDetails, List<NodeAssociations>> neighbors = new HashMap<>();

    private Set<NodeDetails> nodes = new HashSet<>();

    private Set<NodeAssociations> links = new HashSet<>();

    /**
     * Add a node to the graph.
     *
     * @param node
     */
    public void addNode(NodeDetails node) {
        if (!neighbors.containsKey(node)) {
            neighbors.put(node, new ArrayList<NodeAssociations>());
            nodes.add(node);
        }
    }

    /**
     * Check if link exists between two nodes.
     *
     * @param from
     * @param to
     * @return
     */
    public boolean isLink(NodeDetails from, NodeDetails to) {
        List<NodeAssociations> links = neighbors.get(from);
        if (links != null && !links.isEmpty()) {
            for (NodeAssociations NodeAssociations : links) {
                if (NodeAssociations.getTo().equals(to)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add a link to the Graph.
     *
     * @param from
     * @param to
     * @param cost
     */
    public void addLink(NodeDetails from, NodeDetails to, int cost) {
        addNode(from);
        addNode(to);

        if (!isLink(from, to)) {
            NodeAssociations link = new NodeAssociations(from, to, cost);
            neighbors.get(from).add(link);
            links.add(link);
        }
    }

   
    public NodeAssociations getLink(NodeDetails source, NodeDetails target) {
        List<NodeAssociations> links = neighbors.get(source);
        for (NodeAssociations link : links) {
            if (link.getTo().equals(target)) {
                return link;
            }
        }
        return null;
    }


   
    public Iterable<NodeDetails> getNodes() {
        return nodes;
    }

   
    public int getOrder() {
        return neighbors.size();
    }

   
    public Iterable<NodeAssociations> getLinks() {
        return links;
    }

   
    public int getSize() {
        return links.size();
    }

   
    public boolean containsNode(NodeDetails NodeDetails) {
        return neighbors.containsKey(NodeDetails);
    }

   
    public boolean containsLink(NodeAssociations NodeAssociations) {
        return links.contains(NodeAssociations);
    }

    /**
     * Dijkstra's shortest path implementation
     * http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     *
     * @param source
     * @param target
     * @return
     */
    public List<NodeDetails> findShortestPath(NodeDetails source, NodeDetails target) {
        List<NodeDetails> shortestPath = new ArrayList<>();

        source.setDistance(0);

        PriorityQueue<NodeDetails> vertexQueue = new PriorityQueue<>();

        for (NodeDetails vertex : nodes) {
            if (!vertex.equals(source)) {
                vertex.setDistance(Integer.MAX_VALUE);
                vertex.setPrevious(null);
            } else {
                vertex = source;
            }
            vertexQueue.add(vertex);
        }

        while (!vertexQueue.isEmpty()) {
            NodeDetails u = vertexQueue.poll();

            if (u.equals(target)) {
                while (u.getPrevious() != null) {
                    shortestPath.add(u);
                    u = u.getPrevious();
                }
                break;
            }

            vertexQueue.remove(u);

            List<NodeAssociations> edges = neighbors.get(u);

            for (NodeAssociations edge : edges) {
                NodeDetails v = edge.getTo();

                int weight = edge.getCost();
                int distanceThroughU = u.getDistance() + weight;

                if (distanceThroughU < v.getDistance()) {
                    v.setDistance(distanceThroughU);
                    v.setPrevious(u);
                    vertexQueue.remove(v);
                    vertexQueue.add(v);
                }
            }
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }

   
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (NodeDetails node : neighbors.keySet()) {
            sb.append("\n    " + node.getId() + " -> " + neighbors.get(node));
        }
        return sb.toString();
    }

    public Map<NodeDetails, List<NodeAssociations>> getNeighbors() {
        return neighbors;
    }

}
