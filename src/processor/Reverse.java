package processor;

import java.util.ArrayList;

import model.Node;

public class Reverse extends Processor{
	
	public  ArrayList<Node> process(ArrayList<Node> nodes, String...keyWords){
		ArrayList<Node> result = new ArrayList<Node>();
		for(int i=nodes.size()-1; i>=0; i--){
			result.add(nodes.get(i));
		}
		return result;
	}

}
