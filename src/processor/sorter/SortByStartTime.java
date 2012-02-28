package processor.sorter;


import java.util.Comparator;

import processor.comparator.StartTimeComparator;

import model.Node;

public class SortByStartTime extends Sorter {

	public String getString()
	{
	    return "SortByStartTime";
	}

	public Comparator<Node> getComparator() {
		return new StartTimeComparator();
	}
}
