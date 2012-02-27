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

public abstract class Parser {

	private SAXBuilder builder;
	private DateTimeFormatter fmt = DateTimeFormat
			.forPattern("M/d/yyyy hh:mm:ss a");

	public Parser() {
		builder = new SAXBuilder();
	}

	public abstract String getFeedName();

	public abstract String getChildName();

	public abstract DateTime getStartTime(Element calendar);

	public abstract DateTime getEndTime(Element calendar);

	public abstract String getTitle(Element calendar);

	public abstract String getDescription(Element calendar);

	// public abstract HashMap<String, ArrayList<String>> getMap(Element
	// calendar);

	public boolean isThisType(String filename) {
		try {
			Document document = (Document) builder.build(new File(filename));
			Element rootNode = document.getRootElement();
			// System.out.println("Name is" + rootNode.getName());
			return (rootNode.getName().equals(getFeedName()));
		} catch (IOException io) {
			io.printStackTrace();
			return false;
		} catch (JDOMException jdomex) {
			jdomex.printStackTrace();
			return false;
		}
	}

	public List<Node> parseCalender(String filename) {

		List<Node> nodes = new ArrayList<Node>();

		try {
			Document doc = builder.build(new File(filename));
			Element root = doc.getRootElement();
			// List calendars = root.getChildren(getChildName());
			List oddCalendars = root.getChildren();
			List<Element> calendars = filter(oddCalendars, getChildName());
			if (calendars.size() == 0) {
				throw new ParserException("File has no child tags",
						ParserException.Type.EMPTY_FILE);
			}
			// out.println("Duke has "+ calendars.size() +" scheduled games");

			for (Object c : calendars) {
				Element calendar = (Element) c;
				nodes.add(new Node(getStartTime(calendar),
						getEndTime(calendar), getTitle(calendar),
						getDescription(calendar), getFeedName(),
						getMap(calendar)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodes;
	}

	private List<Element> filter(List<Element> input, String key) {
		List<Element> result = new ArrayList<Element>();
		for (Element n : input) {
			if (n.getName().equals(key))
				result.add(n);
			// System.out.println(n.getName());
		}
		return result;
	}

	protected DateTimeFormatter getFmt() {
		return fmt;
	}

	public HashMap<String, ArrayList<String>> getMap(Element calendar) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		Queue<Element> myQueue = new LinkedList<Element>();
		List<?> elements = calendar.getContent();
		for (Object o : elements) {
			if (o instanceof Element) {
				Element e = (Element) o;
				myQueue.add(e);
			}
		}
		while (!myQueue.isEmpty()) {
			Element temp = myQueue.poll();
			List<?> list = temp.getChildren();
			if (list.size() == 0) {
				popMap(map, temp);
			} else {
				for (Object obj : list) {
					Element ele = (Element) obj;
					myQueue.add(ele);
				}
			}
		}
		// checkMap(map);
		return map;
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

	protected void checkMap(HashMap<String, ArrayList<String>> map) {
		for (String key : map.keySet()) {
			System.out.println(key);
			ArrayList<String> list = map.get(key);
			for (int i = 0; i < list.size(); i++) {
				System.out.println("  " + list.get(i));
			}
		}
	}

	protected void popMap(HashMap<String, ArrayList<String>> map, Element e) {
		String name = e.getName();
		if (name.equals("value"))
			name = "rating";
		if (!map.containsKey(name)) {
			map.put(name, new ArrayList<String>());
		}
		map.get(name).add(e.getText());
	}

}
