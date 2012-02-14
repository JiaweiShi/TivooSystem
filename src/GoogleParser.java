import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;

public class GoogleParser extends Parser {

	File myFile;
	SAXBuilder builder;
	HashMap<String, Integer> monthToNumber;

	public GoogleParser(String filename) {
		myFile = new File(filename);
		builder = new SAXBuilder();
		setMap();
	}

	public boolean isThisType() {
		try {
			Document document = (Document) builder.build(myFile);
			Element rootNode = document.getRootElement();
			return (rootNode.getName().equals("feed"));
		} catch (IOException io) {
			return false;
		} catch (JDOMException jdomex) {
			return false;
		}
	}

	public ArrayList<Node> parseCalender() {
		ArrayList<Node> task = new ArrayList<Node>();

		try {
			Document document = (Document) builder.build(myFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren();

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				if (node.getName().equals("entry")) {
					List childrenList = node.getChildren();
					DateTime start = getStartTime(childrenList);
					DateTime end = getEndTime(childrenList);

					String title = getTitleElement(childrenList).getText();
					String description = getDescriptionElement(childrenList)
							.getText();
					task.add(new Node(start, end, title, description));
				}
			}

			return task;

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}

		return task;
	}

	private DateTime getStartTime(List list) {
		String startTimeInfo = getStartTimeElement(list).getText();
		int year = stringToInteger(startTimeInfo.substring(0, 4));
		int month = stringToInteger(startTimeInfo.substring(5, 7));
		int day = stringToInteger(startTimeInfo.substring(8, 10));
		int hour = stringToInteger(startTimeInfo.substring(11, 13));
		int minute = stringToInteger(startTimeInfo.substring(14, 16));

		return new DateTime(year, month, day, hour, minute);
	}

	private DateTime getEndTime(List list) {
		String endTimeInfo = getEndTimeElement(list).getText();
		String[] info = endTimeInfo.split(" ");

		if (info[0].equals("When:") && info[4].length() < 5) {
			int year = stringToInteger(info[4]);

			int month = monthToNumber(info[2]);
			int day = stringToInteger(info[3]
					.substring(0, info[3].length() - 1));

			String[] element = getTimeElemet(info[5]);
			int hour = getHour(element[0], element[2]);
			int minute = getMinute(element[1]);

			return new DateTime(year, month, day, hour, minute);
		}

		else if (info[0].equals("When:") && info[4].length() >= 5) {
			int year = stringToInteger(info[4].substring(0, 4));
			int month = monthToNumber(info[2]);
			int day = stringToInteger(info[3]
					.substring(0, info[3].length() - 1));

			return new DateTime(year, month, day, 0, 0);
		} else {
			String date = info[3];

			int year = stringToInteger(date.substring(0, 4));
			int month = stringToInteger(date.substring(5, 7));
			int day = stringToInteger(date.substring(8, 10));
			String time = info[4];
			int hour = stringToInteger(time.substring(0, 2));
			int minute = stringToInteger(time.substring(3, 5));
			return new DateTime(year, month, day, hour, minute);
		}

	}

	private int stringToInteger(String input) {
		int result = 0;
		int mul = 1;
		for (int i = input.length() - 1; i >= 0; i--) {
			int value = Integer.parseInt(input.substring(i, i + 1));
			result = result + mul * value;
			mul = mul * 10;
		}
		return result;
	}

	private int monthToNumber(String month) {
		return monthToNumber.get(month);
	}

	private void setMap() {
		monthToNumber = new HashMap<String, Integer>();
		monthToNumber.put("Jan", 1);
		monthToNumber.put("Feb", 2);
		monthToNumber.put("Mar", 3);
		monthToNumber.put("Apr", 4);
		monthToNumber.put("May", 5);
		monthToNumber.put("Jun", 6);
		monthToNumber.put("Jul", 7);
		monthToNumber.put("Aug", 8);
		monthToNumber.put("Sep", 9);
		monthToNumber.put("Oct", 10);
		monthToNumber.put("Nov", 11);
		monthToNumber.put("Dec", 12);
	}

	/**
	 * This method return an array that represents the hour, minute and am/pm of
	 * time. hour is stored in the first element minute is stored in the second
	 * element am/pm is stored in the third element
	 * 
	 * @param input
	 * @return
	 */
	private String[] getTimeElemet(String input) {
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

	private Element getStartTimeElement(List list) {
		for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			if (node.getName().equals("published"))
				return node;
		}
		return null;
	}

	private Element getEndTimeElement(List list) {
		for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			if (node.getName().equals("summary"))
				return node;
		}
		return null;
	}

	private Element getTitleElement(List list) {
		for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			if (node.getName().equals("title"))
				return node;
		}
		return null;
	}

	private Element getDescriptionElement(List list) {
		for (int i = 0; i < list.size(); i++) {
			Element node = (Element) list.get(i);
			if (node.getName().equals("summary"))
				return node;
		}
		return null;
	}

}
