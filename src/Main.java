
public class Main {
	/**
	 * 1. The begin date/end date are in format:
	 * mm/day/year
	 * 2. In this project, we just parse the google calendar 
	 * and Duke basketball calendar
	 * 3. The summary is stored in the summary.html
	 * Details are stored in the folder named details.
	 * @param args
	 */
	public static void main(String[] args){
		
		TivooSystem t = new TivooSystem();
		t.loadFile("DukeBasketBall.xml");
		//t.filterByKeyword("CS 110");
		t.filterByTime("9/1/2011", "10/1/2011");
		t.outputToHtml("details","summary.html");
			
	}

}
