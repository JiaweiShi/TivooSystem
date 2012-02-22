package processor;

import java.util.ArrayList;

import model.Node;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



public class FilterByDate extends Processor {
	public ArrayList<Node> process(ArrayList<Node> nodes, String begDate, String endDate)
	{
		ArrayList<Node> filteredNodes = new ArrayList<Node>();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("M/d/yyy");
		DateTime beg = fmt.parseDateTime(begDate);
		DateTime end = fmt.parseDateTime(endDate);
		for(Node node: nodes)
		{
			if(node.getStart().isAfter(beg) && node.getEnd().isBefore(end))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}

	@Override
	public ArrayList<Node> process(ArrayList<Node> nodes, String... keyWords) {
		if(keyWords.length == 2)
			return process(nodes, keyWords[0], keyWords[1]);
		return null;
	}
}
