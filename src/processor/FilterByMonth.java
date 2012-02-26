package processor;

import java.util.ArrayList;
import java.util.List;

import model.Node;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



public class FilterByMonth extends Processor {
	public List<Node> process(List<Node> nodes, String dayDate)
	{
		List<Node> filteredNodes = new ArrayList<Node>();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("M/yyy");
		DateTime month = fmt.parseDateTime(dayDate);
		for(Node node: nodes)
		{
			if(node.getStart().monthOfYear().equals(month.monthOfYear()) || node.getEnd().monthOfYear().equals(month.monthOfYear()))
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
