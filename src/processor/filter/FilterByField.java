package processor.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import processor.Processor;


import model.Node;

public class FilterByField extends Processor {

	public List<Node> process(String option, List<Node> nodes,
			List<String> keyWords) {
		keyWords.remove(option);
		List<Node> filteredNodes = new ArrayList<Node>();
		for (Node node : nodes) {
			if (containActor(node, keyWords, option))
				filteredNodes.add(node);
		}
		if(filteredNodes.isEmpty()){
			System.out.println("Field Does not Exist!! List Not Filtered! >.<");
			filteredNodes = nodes;
		}
		return filteredNodes;
	}

	@Override
	public List<Node> process(List<Node> nodes, String... actors) {
		if (actors.length >= 2)
			return process(actors[0], nodes, new ArrayList<String>(Arrays.asList(actors)));
		return null;
	}

//	private List<String> storeArrayToList(String... keyWords) {
//		List<String> list = new ArrayList<String>();
//		for (String a : keyWords) {
//			list.add(a);
//		}
//		return list;
//	}

	private boolean containActor(Node node, List<String> actorNames,
			String option) {
		List<?> actorList = node.getMapInformation().get(option);
		if(actorList == null) return false;
//		System.out.println(actorList);
		for (String actor : actorNames) {
			if (!actorList.contains(actor))
				return false;
		}
		return true;
	}

	public String getString() {
		return "FilterByField";
	}

}
