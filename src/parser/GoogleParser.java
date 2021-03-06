package parser;

import java.util.*;
import org.jdom.Element;
import org.joda.time.DateTime;

public class GoogleParser extends Parser {
	
	HashMap<String, Integer> monthToNumber;

	private static final HashMap<String, Integer> monthToInteger = new HashMap<String, Integer>();
	static {
		monthToInteger.put("Jan", 1);
		monthToInteger.put("Feb", 2);
		monthToInteger.put("Mar", 3);
		monthToInteger.put("Apr", 4);
		monthToInteger.put("May", 5);
		monthToInteger.put("Jun", 6);
		monthToInteger.put("Jul", 7);
		monthToInteger.put("Aug", 8);
		monthToInteger.put("Sep", 9);
		monthToInteger.put("Oct", 10);
		monthToInteger.put("Nov", 11);
		monthToInteger.put("Dec", 12);
	}

	public GoogleParser() {
		super();
	}

	public String getFeedName() {
		return "feed";
	}
	
	public String getChildName(){
		return "entry";
	}
	
	public DateTime getStartTime(Element entry){
		List<?> childrenList = entry.getChildren();
		Element element = getContentElement(childrenList);
		String timeInfo = getTimeInfo(element);
		return startTime(timeInfo);
	}
	
	public DateTime getEndTime(Element entry){
		List<?> childrenList = entry.getChildren();
		Element element = getContentElement(childrenList);
		String timeInfo = getTimeInfo(element);
		DateTime startTime = startTime(timeInfo);
		return endTime(timeInfo,startTime);
	}
	
	public String getTitle(Element entry){
		List<?> childrenList = entry.getChildren();
		return getTitleElement(childrenList).getText();
	}
	
	public String getDescription(Element entry){
		List<?> childrenList = entry.getChildren();
		return getContentElement(childrenList).getText();
	}
	
//	public HashMap<String, ArrayList<String>> getMap(Element entry){
//		return null;
//	}

	private String getTimeInfo(Element element) {
		String info = element.getText();
		String[] split = info.split("<br />");

		if (split[0].substring(0, 4).equals("When") && split[0].length() > 25)
			return split[0].substring(10, split[0].length() - 5);
		else if (split[0].substring(0, 4).equals("When")
				&& split[0].length() <= 25)
			return split[0].substring(10);
		else if (split[0].equals("Recurring Event"))
			return split[1].substring(14, split[1].length() - 4) + " "
					+ split[2];
		else
			return null;
	}
	

	private int getYearInfo(String input) {
		if (input.startsWith("2"))
			return stringToInteger(input.substring(0, 4));
		else {
			String[] info = input.split(" ");
			return stringToInteger(info[2]);
		}
	}

	private int getMonthInfo(String input) {
		if (input.startsWith("2"))
			return stringToInteger(input.substring(5, 7));
		else {
			String[] info = input.split(" ");
			String monthInString = info[0];
			return monthToInteger.get(monthInString);
		}
	}

	private int getDayInfo(String input) {
		if (input.startsWith("2"))
			return stringToInteger(input.substring(8, 10));
		else {
			String[] info = input.split(" ");
			return stringToInteger(info[1].substring(0, info[1].length() - 1));
		}
	}

	private int getStartHourInfo(String input) {
		if (input.startsWith("2")) {
			String[] info = input.split(" ");
			return stringToInteger(info[1].substring(0, 2));
		} else if (input.length() > 15) {
			String[] info = input.split(" ");
			String[] timeElement = getTimeElement(info[3]);
			return getHour(timeElement[0], timeElement[2]);
		} else {
			return 0;
		}
	}

	private int getStartMinuteInfo(String input) {
		if (input.startsWith("2")) {
			String[] info = input.split(" ");
			return stringToInteger(info[1].substring(3, 5));
		} else if (input.length() > 15) {
			String[] info = input.split(" ");
			String[] timeElement = getTimeElement(info[3]);
			return getMinute(timeElement[1]);
		} else
			return 0;
	}

	private DateTime startTime(String timeInfo) {
		int year = getYearInfo(timeInfo);
		int month = getMonthInfo(timeInfo);
		int day = getDayInfo(timeInfo);
		int startHour = getStartHourInfo(timeInfo);
		int startMinute = getStartMinuteInfo(timeInfo);
		return new DateTime(year, month, day, startHour, startMinute);

	}

	private DateTime endTime(String input, DateTime startTime) {
		if (input.startsWith("2")) {
			String[] info = input.split(" ");
			int durationInSecond = stringToInteger(info[4].substring(0, 4));
			int durationInMinute = durationInSecond/60;
			DateTime endTime = startTime.plusMinutes(durationInMinute);
			return endTime;
		} else if (input.length() > 15) {
			String[] info = input.split(" ");
			String[] timeElement = getTimeElement(info[5]);
			int endHour = getHour(timeElement[0], timeElement[2]);
			int endMinute = getMinute(timeElement[1]);
			return new DateTime(startTime.getYear(),
					startTime.getMonthOfYear(), startTime.getDayOfMonth(),
					endHour, endMinute);
		} else {
			DateTime end = startTime.plusDays(1);
			return end;
		}
	}


	private String[] getTimeElement(String input) {
		String[] element = new String[3];
		int lenOfInput = input.length();
		if (lenOfInput <= 4) {
			element[0] = input.substring(0, lenOfInput - 2);
			element[1] = "0";
			element[2] = input.substring(lenOfInput - 2);
		} else {
			element[0] = input.substring(0, lenOfInput - 5);
			element[1] = input.substring(lenOfInput - 4, lenOfInput - 2);
			element[2] = input.substring(lenOfInput - 2);
		}
		return element;
	}

	private int getMinute(String input) {
		return stringToInteger(input);
	}

	private int getHour(String hour, String tag) {
		if (tag.equals("am") && hour.equals("12"))
			return 0;
		if (tag.equals("am") && !hour.equals("12"))
			return stringToInteger(hour);
		if (tag.equals("pm") && hour.equals("12"))
			return 12;
		if (tag.equals("pm") && !hour.equals("12"))
			return stringToInteger(hour) + 12;
		return 0;
	}

	private Element getTitleElement(List<?> list) {
		for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			if (node.getName().equals("title"))
				return node;
		}
		return null;
	}

	private Element getContentElement(List<?> list) {
		for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			if (node.getName().equals("content"))
				return node;
		}
		return null;
	}

}
