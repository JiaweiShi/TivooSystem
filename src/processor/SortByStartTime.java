package processor;


import java.util.Comparator;

import model.Node;
import model.StartTimeComparator;

public class SortByStartTime extends Sorter {

	public String getString()
	{
	    return "SortByStartTime";
	}

	public Comparator<Node> getComparator() {
		return new StartTimeComparator();
	}
}
