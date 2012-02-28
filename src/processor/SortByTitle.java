package processor;


import java.util.Comparator;

import model.Node;
import model.TitleComparator;

public class SortByTitle extends Sorter {

	public String getString()
	{
	    return "SortByTitle";
	}

	public Comparator<Node> getComparator() {
		return new TitleComparator();
	}
}
