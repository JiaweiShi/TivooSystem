package processor;

import java.util.ArrayList;

import model.Node;



public class FilterByKeywords extends Processor {
	
	public ArrayList<Node> process(ArrayList<Node> nodes, String keyWord)
	{
		ArrayList<Node> filteredNodes = new ArrayList<Node>();
		for(Node node : nodes)
		{
			String str = node.getTitle();
			if(str.contains(keyWord))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}

	@Override
	public ArrayList<Node> process(ArrayList<Node> nodes, String... keyWords) {
		if(keyWords.length == 1)
			return process(nodes, keyWords[0]);
		return null;
		//throw new ParserException();
	}
}
