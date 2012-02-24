package parser;

import java.util.ArrayList;
import java.util.HashMap;

import org.jdom.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class NFLParser extends Parser{
	private DateTimeFormatter fmtNFL = DateTimeFormat.forPattern("yyyy-M-d H:mm:ss");
	
	public NFLParser(){
		
	}
	
	public String getFeedName() {
		return "document";
	}

	public String getChildName() {
		return "row";
	}
	
	public DateTime getStartTime(Element row){
		String info = row.getChildText("Col8");
		return fmtNFL.parseDateTime(info);
	}
	
	public DateTime getEndTime(Element row){
		String info = row.getChildText("Col9");
		return fmtNFL.parseDateTime(info);
	}
	
	public String getTitle(Element row) {
		return row.getChildText("Col1");
	}
	
	public String getDescription(Element row) {
		return row.getChildText("Col15");
	}
	
}
