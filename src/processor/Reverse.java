package processor;

import java.util.ArrayList;
import java.util.List;

import model.Node;

public class Reverse extends Processor{
	
	public  List<Node> process(List<Node> nodes, String...keyWords){
		List<Node> result = new ArrayList<Node>();
		for(int i=nodes.size()-1; i>=0; i--){
			result.add(nodes.get(i));
		}
		return result;
	}
	public String getString()
	{
	    return "Reverse";
	}
}
