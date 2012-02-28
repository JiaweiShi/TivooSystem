package processor;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;


import model.Node;

public class FilterNotContainKeywords extends FilterByKeywords{

	@Override
	public List<Node> process(List<Node> nodes, String... keyWords) {
		List<Node> filteredNodes = super.process(nodes, keyWords);
		List<Node> reverseFilter = new ArrayList<Node>();
		for (Node node : nodes)
		{
			if(!filteredNodes.contains(node))
				reverseFilter.add(node);
		}
		return reverseFilter;
		// throw new ParserException();
	}

//	private List<String> storeArrayToList(String... keyWords) {
//		List<String> list = new ArrayList<String>();
//		for (String a : keyWords) {
//			list.add(a);
//		}
//		return list;
//
//	}
//
//	public boolean notContainKeyWord(String input, List<String> list) {
//		for (String s : list) {
//			if (input.contains(s))
//				return true;
//		}
//		return false;
//	}
	public String getString()
	{
	    return "FilterNotContainKeywords";
	}

}
