package parser;

import org.jdom.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class TVParser extends Parser {
	
	private DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmmss Z");
	
	public TVParser() {
		super();
	}
	
	public String getFeedName() {
		return "tv";
	}

	public String getChildName() {
		return "programme";
	}

	public DateTime getStartTime(Element calendar) {
		String str = calendar.getAttributeValue("start");
		return fmt.parseDateTime(str);
	}
	
	public DateTime getEndTime(Element calendar) {
		String str = calendar.getAttributeValue("stop");
		return fmt.parseDateTime(str);
	}
	
	public String getTitle(Element calendar) {
		return calendar.getChild("title").getText();
	}
	
	public String getDescription(Element calendar) {
		if (calendar.getChild("desc") != null)
			return calendar.getChild("desc").getText();
		return calendar.getChild("category").getText();
	}
}
