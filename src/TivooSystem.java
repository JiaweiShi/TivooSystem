import java.util.*;


public class TivooSystem {

	ArrayList<Node> nodes;
	
	
	public void loadFile(String filename) {
		Parser p = new GoogleParser(filename);
		if(p.isThisType())
			nodes = p.parseCalender();
		
		p = new DukeParser(filename);
		if (p.isThisType())
			nodes = p.parseCalender();
			
	}
	
	
	public void filterByKeyword(String keyword) {
		
		
		
	}
	
	
	public void filterByTime(String date) {
		
		
		
		
	}
	
	
	public void outputToHtml(String detailsFile, String summaryFile) {
		
		
		
		
	}
	
}
