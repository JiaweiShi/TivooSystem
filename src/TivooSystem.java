import java.util.*;


public class TivooSystem {

	ArrayList<Node> nodes;
	ArrayList<Parser> parsers = new ArrayList<Parser>();
	Processor processor;

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

	public void filter(String filterType, String...keywords) {
		//options for filterType = "FilterByDate", "FilterByKeywords" for now
		processor = new ProcessorFactory().getProcessor(filterType);
		nodes = processor.process(nodes, keywords);
	}


	public void outputToHtml(String detailsFile, String summaryFile) {
		SummaryPageHTMLWriter writer = new SummaryPageHTMLWriter();
		writer.makeFile(detailsFile, summaryFile, nodes);	
	}
}