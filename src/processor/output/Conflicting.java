package processor.output;

import java.util.ArrayList;
import java.util.List;

import model.Node;

import org.joda.time.DateTime;

import processor.Processor;



public class Conflicting extends Processor {
	public List<ArrayList<Node>> process(List<Node> nodes)
	{
		List<ArrayList<Node>> retList = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < nodes.size(); i++) {
			ArrayList<Node> nodeList = new ArrayList<Node>();
			nodeList.add(nodes.get(i));
			DateTime begin = nodes.get(i).getStart();
			DateTime end = nodes.get(i).getEnd();
			for (int j = i+1; j < nodes.size(); j++) {
				if (nodes.get(j).getStart().isAfter(begin) && nodes.get(j).getEnd().isBefore(end)) {
					nodeList.add(nodes.get(j));
				}
			}
			if (nodeList.size()>1)
				retList.add(nodeList);
		}
		return retList;
	}

	@Override
	public List<ArrayList<Node>> process(List<Node> nodes, String... keyWords) {
		return process(nodes);
	}
	
	public String getString()
	{
	    return "Conflicting";
	}
}
