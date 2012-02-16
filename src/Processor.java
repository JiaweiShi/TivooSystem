import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class Processor {
	public ArrayList<Node> filterByKeyword(ArrayList<Node> nodes, String keyWord)
	{
		ArrayList<Node> filteredNodes = new ArrayList<Node>();
		for(Node node : nodes)
		{
			String str = node.getDescription() + node.getTitle();
			if(str.contains(keyWord))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}
	
	public ArrayList<Node> filterByDate(ArrayList<Node> nodes, String begDate, String endDate)
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
}
