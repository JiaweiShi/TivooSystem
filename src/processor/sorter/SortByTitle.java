package processor.sorter;


import java.util.Comparator;

import processor.comparator.TitleComparator;

import model.Node;

public class SortByTitle extends Sorter {

	public String getString()
	{
	    return "SortByTitle";
	}

	public Comparator<Node> getComparator() {
		return new TitleComparator();
	}
}
