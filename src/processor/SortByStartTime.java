package processor;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Node;
import model.StartTimeComparator;

public class SortByStartTime extends Processor{
	
	public List<Node> process(List<Node> nodes, String...keyWords){
		List<Node> result = new ArrayList<Node>(nodes);
		Collections.sort(result, new StartTimeComparator());
		return result;
	}

	public String getString()
	{
	    return "SortByStartTime";
	}
}
