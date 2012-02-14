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
			
	}
	
	
	public void filterByKeyword(String keyword) {
		
		
		
	}
	
	
	public void filterByTime(String date) {
		
		
		
		
	}
	
	
	public void outputToHtml(String detailsFile, String summaryFile) {
		SummaryPageHTMLWriter writer = new SummaryPageHTMLWriter();
		writer.makeFile(detailsFile, summaryFile, nodes);	
	}
	
}
