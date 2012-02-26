package model;
import java.util.*;

import parser.*;

import output.ConflictsHTMLWriter;
import output.DayHTMLWriter;
import output.HTMLWriter;
import output.MonthHTMLWriter;
import output.SummaryPageHTMLWriter;
import output.WeekHTMLWriter;

import parser.DukeParser;
import parser.DukecalParser;
import parser.GoogleParser;
import parser.NFLParser;
import parser.Parser;
import processor.Processor;
import processor.ProcessorFactory;

public class TivooSystem {

	private List<Node> nodes;
	private List<Parser> parsers = new ArrayList<Parser>();
	private Processor processor;

	public TivooSystem() {
		nodes = new ArrayList<Node>();
		parsers.add(new GoogleParser());
		parsers.add(new DukeParser());
		parsers.add(new DukecalParser());
		parsers.add(new NFLParser());
		parsers.add(new TVParser());
	}

	public void loadFile(String filename) {
		for (Parser p : parsers) {
			if (p.isThisType(filename)) {
				//nodes = p.parseCalender(filename);
				addToList(p.parseCalender(filename));
				break;
			}
		}			
	}

	public void filter(String filterType, String...keywords) {
		//options for filterType = "FilterByDate", "FilterByKeywords" for now
		processor = new ProcessorFactory().getProcessor(filterType);
		nodes = (ArrayList<Node>) processor.process(nodes, keywords);
	}
	
	public void sort(String sortType){
		processor = new ProcessorFactory().getProcessor(sortType);
		nodes = (ArrayList<Node>) processor.process(nodes, sortType);
	}
	
	public void reverse(){
		processor = new ProcessorFactory().getProcessor("Reverse");
		nodes = (ArrayList<Node>) processor.process(nodes, "Reverse");
	}


	public void outputToHtml(String detailsFile, String summaryFile) {
		HTMLWriter writer = new WeekHTMLWriter();
		writer.makeFile(detailsFile, summaryFile, (ArrayList<Node>) nodes);	
	}
	
	private void addToList(List<Node> list){
		for(Node n: list){
			nodes.add(n);
		}
	}
}