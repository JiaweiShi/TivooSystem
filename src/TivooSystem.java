import java.util.*;


public class TivooSystem {

	ArrayList<Node> nodes;
	
	public TivooSystem(){
		nodes = new ArrayList<Node>();
	}
	
	public void loadFile(String filename) {
		Parser p = new GoogleParser(filename);
		if(p.isThisType())
			nodes = p.parseCalender();
		
		p = new DukeParser(filename);
		if (p.isThisType())
			nodes = p.parseCalender();
			
	}
	
	
	public void filterByKeyword(String keyword) {
		Processor p = new Processor();
		nodes = p.filterByKeyword(nodes, keyword);
	}
	
	
	public void filterByTime(String begDate, String endDate) {
		Processor p = new Processor();
		nodes = p.filterByDate(nodes, begDate, endDate);
	}
	
	
	public void outputToHtml(String detailsFile, String summaryFile) {
		SummaryPageHTMLWriter writer = new SummaryPageHTMLWriter();
		writer.makeFile(detailsFile, summaryFile, nodes);	
	}
	
	public static void main(String[] args)
	{
		
	}
	
}
