
import org.joda.time.DateTime;


public class Node implements Comparable<Node>{

	DateTime startDt;
	String title;
	DateTime endDt;
	String description;
	
	public Node(DateTime start, DateTime end, String t,String d) {
		startDt = start;
		endDt = end;
		description = d;
		title = t;
	}
	
	public boolean containKeyWord(String keyword){
		return title.contains(keyword);
	}
	
	public int compareTo(Node node){
		if(endDt.isBefore(node.endDt))  return -1;
		else if(endDt.isAfter(node.endDt))   return 1;
		else return 0; 
	}	
	
	public DateTime getStart()
	{
	    return startDt;
	}
	
	public DateTime getEnd()
	{
	    return endDt;
	}
	
	public String getTitle()
	{
	   return title;
	}
	
	public String getDescription()
	{
	    return description;
	}
}
