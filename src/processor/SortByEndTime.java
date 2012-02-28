package processor;


import java.util.Comparator;

import model.Node;
import model.EndTimeComparator;

public class SortByEndTime extends Sorter {

	public String getString()
	{
	    return "SortByEndTime";
	}

	public Comparator<Node> getComparator() {
		return new EndTimeComparator();
	}
}
