package processor.output;

import java.util.ArrayList;
import java.util.List;

import model.Node;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import processor.Processor;



public class FilterByDay extends Processor {
	public List<Node> process(List<Node> nodes, String dayDate)
	{
		List<Node> filteredNodes = new ArrayList<Node>();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("M/d/yyy");
		DateTime day = fmt.parseDateTime(dayDate);
		for(Node node: nodes)
		{
			if(node.getStart().dayOfYear().equals(day.dayOfYear()) || node.getEnd().dayOfYear().equals(day.dayOfYear()))
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
	    return "FilterByDay";
	}
}
