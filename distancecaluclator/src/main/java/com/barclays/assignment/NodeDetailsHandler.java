package com.barclays.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barclays.node.NodeDetails;
import com.barclays.rules.NodeGraph;

/**
 * This class is used for processing node details list and arranging in required manner. 
 * 
 */
public class NodeDetailsHandler {

	private static List<String> nodesDetailsList = new ArrayList<String>();
	private static Map<String, NodeDetails> gateNodeMap = new HashMap<>();

	static
	{
		nodesDetailsList.add("Concourse_A_Ticketing:A5:5");
		nodesDetailsList.add("A5:BaggageClaim:5");
		nodesDetailsList.add("A5:A1:6");
		nodesDetailsList.add("A5:A10:4");
		nodesDetailsList.add("A1:A2:1");
		nodesDetailsList.add("A2:A3:1");
		nodesDetailsList.add("A3:A4:1");
		nodesDetailsList.add("A10:A9:1");
		nodesDetailsList.add("A9:A8:1");
		nodesDetailsList.add("A8:A7:1");
		nodesDetailsList.add("A7:A6:1");
	}


	public static NodeGraph process()
	{ 
		
		NodeGraph nodeGraph = new NodeGraph();
		for(String nodesDetail : nodesDetailsList)
		{
			String nodesArray[] = nodesDetail.split(":");
			int cost = Integer.parseInt(nodesArray[2]);
			 //add the bi-directional link in the barclays
			nodeGraph.addLink(createNode(nodesArray[0], gateNodeMap), createNode(nodesArray[1], gateNodeMap), cost);
			nodeGraph.addLink(createNode(nodesArray[1], gateNodeMap), createNode(nodesArray[0], gateNodeMap), cost);
			
		}
		
		return nodeGraph;
	}
	
    private static NodeDetails createNode(String node, Map<String, NodeDetails> nodeMap) {
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }
        NodeDetails nodeDetails = new NodeDetails(node);
        nodeMap.put(node, nodeDetails);
        return nodeDetails;
    }
}
