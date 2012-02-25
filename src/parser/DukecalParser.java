package parser;


import org.jdom.Element;
import org.joda.time.DateTime;


public class DukecalParser extends Parser{
	
	public DukecalParser(){
		
	}
	
	public String getFeedName(){
		return "events";
	}
	
	public String getChildName(){
		return "event";
	}
	
	public DateTime getStartTime(Element event){
		Element startTime = event.getChild("start");
		return getTimeInfo(startTime);
	}
	
	public DateTime getEndTime(Element event){
		Element endTime = event.getChild("end");
		return getTimeInfo(endTime);
	}
	
	public String getTitle(Element event){
		return event.getChildText("summary");
	}
	
	public String getDescription(Element event){
		return event.getChildText("description");
	}
	
	private DateTime getTimeInfo(Element element){
		int year = stringToInteger(element.getChild("fourdigityear").getText());
		int month = stringToInteger(element.getChild("twodigitmonth").getText());
		int day = stringToInteger(element.getChild("twodigitday").getText());	
		int hour = stringToInteger(element.getChild("twodigithour24").getText());
		int minute = stringToInteger(element.getChild("twodigitminute").getText());
		return new DateTime(year,month,day,hour,minute);
	}

}
