package parser;
import model.Node;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import parser.ParserException.Type;



public abstract class Parser {
	
	private SAXBuilder builder;
	private DateTimeFormatter fmt = DateTimeFormat.forPattern("M/d/yyyy hh:mm:ss a");
	
	public Parser() {
		builder = new SAXBuilder();
	}
	
	public boolean isThisType(String filename) {
		try {
			Document document = (Document) builder.build(new File(filename));
			Element rootNode = document.getRootElement();
			return (rootNode.getName().equals(getFeedName()));		
		} catch (IOException io) {
			return false;
		} catch (JDOMException jdomex) {
			return false;
		}
	}
	
	public abstract String getFeedName();
	
	public ArrayList<Node> parseCalender(String filename) {
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		try {
            Document doc = builder.build(new File(filename));
            Element root = doc.getRootElement();
            //List calendars = root.getChildren(getChildName());
            List oddCalendars = root.getChildren();
            List<Element>calendars = filter(oddCalendars,getChildName());
            if (calendars.size()==0) {
            	throw new ParserException("File has no child tags", ParserException.Type.EMPTY_FILE);
            }
            //out.println("Duke has "+ calendars.size() +" scheduled games");
            
            for (Object c : calendars) {         	
            	Element calendar = (Element) c;        	
            	nodes.add(new Node(getStartTime(calendar), getEndTime(calendar), getTitle(calendar), getDescription(calendar), getFeedName(), getMap(calendar)));         	
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return nodes;  
	}

	public abstract String getChildName();
	
	public abstract DateTime getStartTime(Element calendar);
	
	public abstract DateTime getEndTime(Element calendar);
	
	public abstract String getTitle(Element calendar);
	
	public abstract String getDescription(Element calendar);
	
	public abstract HashMap<String, ArrayList<String>> getMap(Element calendar);
	
	private List<Element> filter(List<Element> input,String key){
		List<Element> result = new ArrayList<Element>();
		for(Element n: input){
			if(n.getName().equals(key))
				result.add(n);
		}
		return result;
	}
	
	protected DateTimeFormatter getFmt() {
		return fmt;
	}
	
	protected int stringToInteger(String input) {
		int result = 0;
		int mul = 1;
		for (int i = input.length() - 1; i >= 0; i--) {
			int value = Integer.parseInt(input.substring(i, i + 1));
			result = result + mul * value;
			mul = mul * 10;
		}
		return result;
	}

}
