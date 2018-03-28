package com.barclays.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.barclays.node.NodeDetails;
import com.barclays.rules.NodeGraph;

/**
 * This class is used for calculating the distance from source to destination nodes , by processing the nodes and flight information.
 * 
 */
public class BaggageCaluclator 
{

	private static Map<String,String> flightsNodeMap = new HashMap<String,String>();
	private static List<String> bagEntryDetailsList = new ArrayList<String>();

	public static void main( String[] args )
	{
		NodeGraph nodeGraph = NodeDetailsHandler.process();
		processFlightNodeDetails();
		
		bagEntryDetailsList.add("0001:Concourse_A_Ticketing:UA12");
		bagEntryDetailsList.add("0002:A5:UA17");
		bagEntryDetailsList.add("0003:A2:UA10");
		bagEntryDetailsList.add("0004:A8:UA18");
		bagEntryDetailsList.add("0005:A7:ARRIVAL");
		bagEntryDetailsList.add("0006:A8:UA20");
		
		 StringBuffer output = new StringBuffer();
		 
		for(String baggageDetail : bagEntryDetailsList){
			String baggageDetailArray[] = baggageDetail.split(":");
			String sourceNodeName = baggageDetailArray[1];
			String destNodeName = flightsNodeMap.get(baggageDetailArray[2]);
			 
			 output.append(baggageDetailArray[0] + " ");
			 NodeDetails sourceNode = new NodeDetails(sourceNodeName);
	         NodeDetails targetNode = new NodeDetails(destNodeName);
	         
	         List<NodeDetails> shortestPath = nodeGraph.findShortestPath(sourceNode, targetNode);

	            if (!shortestPath.isEmpty()) {
	                output.append(sourceNodeName + " ");
	                NodeDetails prevNode = shortestPath.get(0);
	                output.append(prevNode.getId() + " ");

	                for (int i = 1; i < shortestPath.size(); i++) {
	                	NodeDetails current = shortestPath.get(i);
	                    prevNode = current;
	                    output.append(current.getId() + " ");
	                }
	                output.append(": " + prevNode.getDistance());
	                output.append(System.lineSeparator());
	            } else { 
	                output.append("No association exits between source and destination");
	                output.append(System.lineSeparator());
	            }
		}
		System.out.println(output.toString());
	}

	private static void processFlightNodeDetails(){

		List<String> flightDetailsList = new ArrayList<String>();

		flightDetailsList.add("UA10:A1");
		flightDetailsList.add("UA11:A1");
		flightDetailsList.add("UA12:A1");
		flightDetailsList.add("UA13:A2");
		flightDetailsList.add("UA14:A2");
		flightDetailsList.add("UA15:A2");
		flightDetailsList.add("UA16:A3");
		flightDetailsList.add("UA17:A4");
		flightDetailsList.add("UA18:A5");
		flightDetailsList.add("ARRIVAL:BaggageClaim");

		for(String flightDetail : flightDetailsList){
			String flightDetailArray[] = flightDetail.split(":");
			flightsNodeMap.put(flightDetailArray[0],flightDetailArray[1]);
		}
	}
	
	
	
}