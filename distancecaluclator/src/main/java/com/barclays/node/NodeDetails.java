package com.barclays.node;

public class NodeDetails implements Comparable<NodeDetails>{

	private String id;
	private String nodeName;
	private Integer distance =  0;
	private NodeDetails previous;
	
	public NodeDetails(String node) {
		this.id = node;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public NodeDetails getPrevious() {
		return previous;
	}
	public void setPrevious(NodeDetails previous) {
		this.previous = previous;
	}
	public int compareTo(NodeDetails otherNodeDetails) {
		return distance.compareTo(otherNodeDetails.distance);
	}
	
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }

        if (obj == null || !(obj instanceof NodeDetails)){
            return false;
        }

        NodeDetails other = (NodeDetails) obj;

        return (this.id.equals(other.id));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}