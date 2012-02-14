import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DukeParser extends Parser {

	File myFile;
	SAXBuilder builder;
	DateTimeFormatter fmt = DateTimeFormat.forPattern("M/d/yyyy");
	
	public DukeParser(String filename) {
		myFile = new File(filename);
		builder = new SAXBuilder();
	}
	
	public boolean isThisType() {
		try {
			Document document = (Document) builder.build(myFile);
			Element rootNode = document.getRootElement();
			return (rootNode.getName().equals("dataroot"));		
		} catch (IOException io) {
			return false;
		} catch (JDOMException jdomex) {
			return false;
		}
	}

	public ArrayList<Node> parseCalender() {
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		try {
            Document doc = builder.build(myFile);
            Element root = doc.getRootElement();
            List calendars = root.getChildren("Calendar");
            //out.println("Duke has "+ calendars.size() +" scheduled games");
            
            for (Object c : calendars) {         	
            	Element calendar = (Element) c;        	
            	nodes.add(new Node(getStartTime(calendar), getEndTime(calendar), getTitle(calendar), getDescription(calendar)));         	
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return nodes;  
	}

	private DateTime getStartTime(Element calendar) {
		String str = calendar.getChild("StartDate").getText();
		return fmt.parseDateTime(str);
	}
	
	private DateTime getEndTime(Element calendar) {
		String str = calendar.getChild("EndDate").getText();
		return fmt.parseDateTime(str);
	}
	
	private String getTitle(Element calendar) {
		return calendar.getChild("Subject").getText();
	}
	
	private String getDescription(Element calendar) {
		return calendar.getChild("Description").getText();
	}
}
