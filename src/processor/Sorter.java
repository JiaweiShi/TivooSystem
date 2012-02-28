package processor;

import java.util.*;

import model.Node;

public abstract class Sorter extends Processor {
	
	public List<Node> process(List<Node> nodes, String...keyWords){
		List<Node> result = new ArrayList<Node>(nodes);
		Collections.sort(result, getComparator());
		return result;
	}

	abstract public String getString();
	
	abstract public Comparator<Node> getComparator();

}
