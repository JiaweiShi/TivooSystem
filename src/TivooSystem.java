import java.util.*;


public class TivooSystem {

	ArrayList<Node> nodes;
	Processor processor;
	
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
	
	
	public void filter(String filterType, String...keywords) {
		//options for filterType = "FilterByDate", "FilterByKeywords" for now
		processor = new ProcessorFactory().getProcessor(filterType);
		nodes = processor.process(nodes, keywords);
	}
	
	
	public void outputToHtml(String detailsFile, String summaryFile) {
		SummaryPageHTMLWriter writer = new SummaryPageHTMLWriter();
		writer.makeFile(detailsFile, summaryFile, nodes);	
	}
	
	public static void main(String[] args)
	{
		
	}
	
}
