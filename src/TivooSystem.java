
import java.util.*;


public class TivooSystem {

	ArrayList<Node> nodes;
	ArrayList<Parser> parsers = new ArrayList<Parser>();
	
	public TivooSystem(){
		nodes = new ArrayList<Node>();
		parsers.add(new GoogleParser());
		parsers.add(new DukeParser());
	}
	
	public void loadFile(String filename) {
		for (Parser p : parsers) {
			if (p.isThisType(filename)) {
				nodes = p.parseCalender(filename);
				break;
			}
		}			
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
