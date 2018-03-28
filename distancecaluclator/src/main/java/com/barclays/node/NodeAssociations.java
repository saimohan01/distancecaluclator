package com.barclays.node;

import java.util.Objects;

public class NodeAssociations {

	private NodeDetails from;
	private NodeDetails to;
	private int cost;

	public NodeAssociations(NodeDetails from, NodeDetails to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	public NodeDetails getFrom() {
		return from;
	}

	public NodeDetails getTo() {
		return to;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || !(obj instanceof NodeAssociations)) {
			return false;
		}

		NodeAssociations other = (NodeAssociations) obj;

		return (this.from.equals(other.from) && this.to.equals(other.to));
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public String toString() {
		return "Association [" + from.getId() + "->" + to.getId() + " : " + cost + "]";
	}
}
