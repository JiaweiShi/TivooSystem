package processor;

import java.util.ArrayList;
import java.util.Collections;

import model.EndTimeComparator;
import model.Node;


public class SortByEndTime extends Processor{
	
	public ArrayList<Node> process(ArrayList<Node> nodes, String...keyWords){
		ArrayList<Node> result = new ArrayList<Node>(nodes);
		Collections.sort(result, new EndTimeComparator());
		return result;
	}

}
