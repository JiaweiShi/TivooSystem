package processor.output;

import java.util.ArrayList;
import java.util.List;

import model.Node;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import processor.Processor;



public class FilterByWeek extends Processor {
	public List<Node> process(List<Node> nodes, String weekDate)
	{
		List<Node> filteredNodes = new ArrayList<Node>();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("M/d/yyy");
		DateTime first = fmt.parseDateTime(weekDate);
		DateTime last = first.plusDays(6);
		for(Node node: nodes)
		{
			if((node.getStart().isAfter(first) && node.getEnd().isBefore(last)) || 
					(node.getStart().isBefore(first) && node.getEnd().isAfter(first)) ||
					(node.getStart().isBefore(last) && node.getEnd().isAfter(last)))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}

	@Override
	public List<Node> process(List<Node> nodes, String... keyWords) {
		if(keyWords != null && keyWords.length == 1)
			return process(nodes, keyWords[0]);
		return null;
	}
	
	public String getString()
	{
	    return "FilterByWeek";
	}
}
