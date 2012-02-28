package processor.sorter;


import java.util.Comparator;

import processor.comparator.EndTimeComparator;

import model.Node;

public class SortByEndTime extends Sorter {

	public String getString()
	{
	    return "SortByEndTime";
	}

	public Comparator<Node> getComparator() {
		return new EndTimeComparator();
	}
}
