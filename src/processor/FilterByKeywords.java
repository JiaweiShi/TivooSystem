package processor;

import java.util.ArrayList;
import java.util.List;

import model.Node;



public class FilterByKeywords extends Processor {
	
	public List<Node> process(List<Node> nodes, List<String> keyWords)
	{
		List<Node> filteredNodes = new ArrayList<Node>();
		for(Node node : nodes)
		{
			String str = node.getTitle();
			
			if(containKeyWord(str,keyWords))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}

	@Override
	public List<Node> process(List<Node> nodes, String... keyWords) {
		if(keyWords.length >= 1){
			List<String> list = storeArrayToList(keyWords);
			return process(nodes, list);
		}
			
		return null;
		//throw new ParserException();
	}
	
	private List<String> storeArrayToList(String...keyWords){
		List<String> list = new ArrayList<String>();
		for(String a: keyWords){
			list.add(a);
		}
		return list;
		
	}
	
	private boolean containKeyWord(String input, List<String> list){
		for(String s: list){
			if(!input.contains(s))
				return false;
		}
		return true;
	}
}
