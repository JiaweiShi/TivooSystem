import org.jdom.Element;
import org.joda.time.DateTime;


public class DukeParser extends Parser {
	
	public DukeParser() {
		super();
	}
	
	public String getFeedName() {
		return "dataroot";
	}

	public String getChildName() {
		return "Calendar";
	}

	public DateTime getStartTime(Element calendar) {
		String str = calendar.getChild("StartDate").getText() + " " + calendar.getChild("StartTime").getText();
		return fmt.parseDateTime(str);
	}
	
	public DateTime getEndTime(Element calendar) {
		String str = calendar.getChild("EndDate").getText() + " " + calendar.getChild("EndTime").getText();
		return fmt.parseDateTime(str);
	}
	
	public String getTitle(Element calendar) {
		return calendar.getChild("Subject").getText();
	}
	
	public String getDescription(Element calendar) {
		return calendar.getChild("Description").getText();
	}
}
