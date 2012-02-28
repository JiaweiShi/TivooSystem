import model.TivooSystem;


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
		//t.loadFile("googlecal.xml");
		t.loadFile("DukeBasketBall.xml");
//		t.loadFile("dukecal.xml");
		//t.loadFile("NFL.xml");
//		t.loadFile("tele.xml");
		t.filter("FilterNotContainKeywords", "CS","10");
		//t.filter("FilterByKeywords", "State");
		//t.filter("FilterNotContainKeywords", "State");
		//t.filter("FilterByDate", "7/1/2011", "3/4/2012");
//		t.filter("SortByTitle");
		//t.filter("FilterByDay", "11/11/2011");
		//t.filter("FilterByWeek", "10/31/2011");
		//t.filter("FilterByMonth", "9/2011");
//		t.filter("FilterByField", "actor", "Jodie Foster");
		//t.reverse();
//		t.filter("Conflicting");
		t.outputToHtml("details","summary.html");

	}

}