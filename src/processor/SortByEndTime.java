package processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.EndTimeComparator;
import model.Node;


public class SortByEndTime extends Processor{
	
	public List<Node> process(List<Node> nodes, String...keyWords){
		List<Node> result = new ArrayList<Node>(nodes);
		Collections.sort(result, new EndTimeComparator());
		return result;
	}

}
