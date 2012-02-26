package model;
import java.util.*;

import parser.*;

import output.ConflictsHTMLWriter;
import output.DayHTMLWriter;
import output.HTMLWriter;
import output.ListHTMLWriter;
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
	private Map<String, HTMLWriter> processorToHTMLWriter; 

	public TivooSystem() {
		nodes = new ArrayList<Node>();
		parsers.add(new GoogleParser());
		parsers.add(new DukeParser());
		parsers.add(new DukecalParser());
		parsers.add(new NFLParser());
		parsers.add(new TVParser());
		processorToHTMLWriter = new HashMap<String, HTMLWriter>();
		HTMLWriter day = new DayHTMLWriter();
		HTMLWriter week = new WeekHTMLWriter();
		HTMLWriter month = new MonthHTMLWriter();
		HTMLWriter conflict = new ConflictsHTMLWriter();
		HTMLWriter list = new ListHTMLWriter();
		
		processorToHTMLWriter.put("SortByStartTime", list);
		processorToHTMLWriter.put("SortByEndTime", list);
		processorToHTMLWriter.put("SortByTitle", list);
		processorToHTMLWriter.put("FilterByKeywords",list );
		processorToHTMLWriter.put("FilterNotContainKeywords", list);
		processorToHTMLWriter.put("Reverse" , list);
		processorToHTMLWriter.put("FilterByDate", list);
		processorToHTMLWriter.put("FilterByMonth", month);
		processorToHTMLWriter.put("FilterByDay", day);
		processorToHTMLWriter.put("FilterByWeek", week);
		processorToHTMLWriter.put("Conflicting", conflict);
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
	    HTMLWriter writer;
	    if(processor == null) 
	        writer = new ListHTMLWriter();
	    else{
	        writer = processorToHTMLWriter.get(processor.getString());
	    }
		writer.makeFile(detailsFile, summaryFile, (ArrayList<Node>) nodes);	
	}
	
	private void addToList(List<Node> list){
		for(Node n: list){
			nodes.add(n);
		}
	}
}