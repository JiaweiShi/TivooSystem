package processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import model.Node;

public class FilterByKeywords extends Processor {

	public List<Node> process(List<Node> nodes, List<String> keyWords) {
		List<Node> filteredNodes = new ArrayList<Node>();
		for (Node node : nodes) {
			boolean found = false;
			HashMap<String, ArrayList<String>> map = node.getMapInformation();
			if (map != null) {
				for (List<String> list : map.values()) {
					//System.out.println(list);
					for (String str : list) {
						//System.out.println(str);
						if (containKeyWord(str, keyWords)) {
							found = true;
							filteredNodes.add(node);
							break;
						}
					}
					if (found)
						break;
				}
			}
		}
		return filteredNodes;
		
//		List<Node> filteredNodes = new ArrayList<Node>();
//		for (Node node : nodes) {
//			String str = node.getTitle();
//
//			if (containKeyWord(str, keyWords))
//				filteredNodes.add(node);
//		}
//		return filteredNodes;
		
	}

	public List<Node> process(List<Node> nodes, String... keyWords) {
		if (keyWords.length >= 1) {
			List<String> list = new ArrayList<String>(Arrays.asList(keyWords));
			return process(nodes, list);
		}

		return null;
		// throw new ParserException();
	}

	// private List<String> storeArrayToList(String...keyWords){
	// List<String> list = new ArrayList<String>();
	// for(String a: keyWords){
	// list.add(a);
	// }
	// return list;
	//
	// }

	public boolean containKeyWord(String input, List<String> list) {
		for (String s : list) {
			if (!input.contains(s))
				return false;
		}
		return true;
	}

	public String getString() {
		return "FilterByKeywords";
	}
}
