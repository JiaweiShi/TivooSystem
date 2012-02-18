
import java.util.*;


public class TivooSystem {

	ArrayList<Node> nodes;
	
	public TivooSystem(){
		nodes = new ArrayList<Node>();
	}
	
	public void loadFile(String filename) {
		Parser p = new GoogleParser();
		if(p.isThisType(filename))
			nodes = p.parseCalender(filename);
		
		p = new DukeParser();
		if (p.isThisType(filename))
			nodes = p.parseCalender(filename);
			
	}
	
	
	public void filterByKeyword(String keyword) {
		Processor p = new Processor();
		ArrayList<Node> nodeForFilter = new ArrayList<Node>(nodes);
		nodes = p.filterByKeyword(nodeForFilter, keyword);
	}
	
	
	public void filterByTime(String begDate,String endDate) {
		Processor p = new Processor();
		ArrayList<Node> nodeForFilter = new ArrayList<Node>(nodes);
		nodes = p.filterByDate(nodeForFilter, begDate, endDate);
	}
	
	
	public void outputToHtml(String detailsFile, String summaryFile) {
		SummaryPageHTMLWriter writer = new SummaryPageHTMLWriter();
		writer.makeFile(detailsFile, summaryFile, nodes);	
	}
	
}
