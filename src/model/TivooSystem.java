package model;
import java.util.*;

import output.*;
import parser.*;
import processor.*;

public class TivooSystem {

	private ArrayList<Node> nodes;
	private List<Parser> parsers = new ArrayList<Parser>();
	private Processor processor;

	public TivooSystem(){
		nodes = new ArrayList<Node>();
		parsers.add(new GoogleParser());
		parsers.add(new DukeParser());
		parsers.add(new TVParser());
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
		HTMLWriter writer = new SummaryPageHTMLWriter();
		writer.makeFile(detailsFile, summaryFile, nodes);	
	}
}