
public class Main {
	
	public static void main(String[] args){
		
		TivooSystem t = new TivooSystem();
		t.loadFile("googlecal.xml");
		t.outputToHtml("details","summary.html");
			
	}

}
