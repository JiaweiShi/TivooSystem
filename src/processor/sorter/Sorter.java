package processor.sorter;

import java.util.*;

import processor.Processor;

import model.Node;

public abstract class Sorter extends Processor {
	
	public List<Node> process(List<Node> nodes, String...keyWords){
		List<Node> result = new ArrayList<Node>(nodes);
		Comparator<Node> c = getComparator();
		if(keyWords != null && keyWords.length >= 1 && Boolean.parseBoolean(keyWords[0]))
			c = Collections.reverseOrder(c);
		Collections.sort(result, c);
		return result;
	}

	abstract public String getString();
	
	abstract public Comparator<Node> getComparator();

}
