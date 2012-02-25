package processor;

import java.util.ArrayList;

import model.Node;



public class FilterByKeywords extends Processor {
	
	public ArrayList<Node> process(ArrayList<Node> nodes, ArrayList<String> keyWords)
	{
		ArrayList<Node> filteredNodes = new ArrayList<Node>();
		for(Node node : nodes)
		{
			String str = node.getTitle();
			
			if(containKeyWord(str,keyWords))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}

	@Override
	public ArrayList<Node> process(ArrayList<Node> nodes, String... keyWords) {
		if(keyWords.length >= 1){
			ArrayList<String> list = storeArrayToList(keyWords);
			return process(nodes, list);
		}
			
		return null;
		//throw new ParserException();
	}
	
	private ArrayList<String> storeArrayToList(String...keyWords){
		ArrayList<String> list = new ArrayList<String>();
		for(String a: keyWords){
			list.add(a);
		}
		return list;
		
	}
	
	private boolean containKeyWord(String input, ArrayList<String> list){
		for(String s: list){
			if(!input.contains(s))
				return false;
		}
		return true;
	}
}
