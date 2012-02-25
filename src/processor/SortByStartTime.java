package processor;

import java.util.ArrayList;
import java.util.Collections;

import model.Node;
import model.StartTimeComparator;

public class SortByStartTime extends Processor{
	
	public ArrayList<Node> process(ArrayList<Node> nodes, String...keyWords){
		ArrayList<Node> result = new ArrayList<Node>(nodes);
		Collections.sort(result, new StartTimeComparator());
		return result;
	}

}
