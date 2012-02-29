package processor.filter;

import java.util.ArrayList;
import java.util.List;

import model.Node;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import processor.Processor;




public class FilterByDate extends Processor {
	public List<Node> process(List<Node> nodes, String begDate, String endDate)
	{
		List<Node> filteredNodes = new ArrayList<Node>();
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
	public List<Node> process(List<Node> nodes, String... keyWords) {
		if(keyWords != null && keyWords.length == 2)
			return process(nodes, keyWords[0], keyWords[1]);
		return null;
	}
	
	public String getString()
	{
	    return "FilterByDate";
	}
}
