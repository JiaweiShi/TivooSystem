import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;

import com.sun.jmx.snmp.tasks.Task;

public class GoogleParser implements Parser {

	File myFile;
	SAXBuilder builder;

	public GoogleParser(String filename) {
		myFile = new File(filename);
		builder = new SAXBuilder();
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
			List list = rootNode.getChildren("entry");

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				
				}
			
			return null;

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}

		return task;
	}
	
	private DateTime getStartTime(Element node){
		String startTimeInfo = node.getChildText("published");
		int year = stringToInteger(startTimeInfo.substring(0, 4));
		int month = stringToInteger(startTimeInfo.substring(5,7));
		int day = stringToInteger(startTimeInfo.substring(8,10));
		int hour = stringToInteger(startTimeInfo.substring(11,13));
		int minute = stringToInteger(startTimeInfo.substring(14,16));
		
		return new DateTime(year,month,day,hour,minute);
	}
	
	private DateTime getEndTime(Element node){
		String endTimeInfo = node.getChildText("summary");
		String [] info = endTimeInfo.split(" ");
		return null;
	}
	
	private int stringToInteger(String input){
		int result = 0;
		int mul = 1;
		for(int i=input.length()-1; i>=0; i--){
			int value =  Integer.parseInt(input.substring(i,i+1));
			result = result + mul * value;
			mul = mul * 10;
		}
		return result;
	}
	
	private int monthToInteger(String month){
		HashMap<String,Integer> monthToNumber = new HashMap<String,Integer>();
		return 0;
		
	}
}


