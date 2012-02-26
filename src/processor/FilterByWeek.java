package processor;

import java.util.ArrayList;
import java.util.List;

import model.Node;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



public class FilterByWeek extends Processor {
	public List<Node> process(List<Node> nodes, String weekDate)
	{
		List<Node> filteredNodes = new ArrayList<Node>();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("M/d/yyy");
		DateTime first = fmt.parseDateTime(weekDate);
		DateTime last = first.plusDays(6);
		System.out.println();
		for(Node node: nodes)
		{
			System.out.println();
			if((node.getStart().isAfter(first) && node.getEnd().isBefore(last)) || 
					(node.getStart().isBefore(first) && node.getEnd().isAfter(first)) ||
					(node.getStart().isBefore(last) && node.getEnd().isAfter(last)))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}

	@Override
	public List<Node> process(List<Node> nodes, String... keyWords) {
		if(keyWords.length == 1)
			return process(nodes, keyWords[0]);
		return null;
	}
}
