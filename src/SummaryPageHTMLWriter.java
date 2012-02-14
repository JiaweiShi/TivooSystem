import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.joda.time.DateTime;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.P;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Ul;


public class SummaryPageHTMLWriter {
    
    private static final TreeMap<Integer, String> dayMap = new TreeMap<Integer, String>();
    static      
    {
            dayMap.put(0, "Monday");
            dayMap.put(1, "Tuesday");
            dayMap.put(2, "Wednesday");
            dayMap.put(3, "Thursday");
            dayMap.put(4, "Friday");
            dayMap.put(5, "Saturday");
            dayMap.put(6, "Sunday");
    }
    

    
    public void makeFile(String detailsFile, String summaryFile, ArrayList<Node> events)
    {
        Html html = new Html();
        Head head = new Head();   
        
        html.appendChild( head );
        
        Title title = new Title();
        title.appendChild( new Text("Summary Page") );
        head.appendChild( title );
        
        Body body = new Body();
        
        html.appendChild( body );
        int count = 0;
        
        Ul[] dayList = new Ul[7];
        for(int i = 0; i<7; i++)
        {
            dayList[i] = new Ul();
        }
        
        
        
        for(Node event: events)
        {
            String detailPageLocation = detailsFile+"/" + count +".html";
            makeDetailsPage(event, detailPageLocation);
            count++;
            int day = event.getStart().getDayOfWeek();
            addEventToList(dayList[day-1], event, detailPageLocation);  
            
        }
        
        for(int i=0; i<dayList.length;  i++)
        {
            H1 dayHeader = new H1();
            body.appendChild(dayHeader);
            dayHeader.appendText(dayMap.get(i));
            body.appendChild(dayList[i]);
        }
        
        
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(summaryFile));
            out.write(html.write());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addEventToList(Ul list, Node event, String detailPageLocation)
    {
        Li eventListElement = new  Li();
        list.appendChild(eventListElement);
        A eventLink = new A();
        eventListElement.appendChild(eventLink);
        eventLink.setHref(detailPageLocation);
        eventLink.appendText(event.getTitle());
        
    }
    
    public void makeDetailsPage(Node event, String detailsFile)
    {
        Html html = new Html();
        Head head = new Head();   
        
        html.appendChild( head );
        
        Title title = new Title();
        title.appendChild( new Text(event.getTitle()+" Page") );
        head.appendChild( title );
        
        Body body = new Body();          
        html.appendChild( body );
        
        H3 heading = new H3();
        body.appendChild(heading);
        heading.appendText(event.getTitle());
        
        P eventInfo = new P();
        body.appendChild(eventInfo);
        eventInfo.appendText("Start time: "+ event.getStart().toString("MM/dd/yy hh:mm"));
        eventInfo.appendChild(new Br());
        eventInfo.appendText("End time: "+event.getEnd().toString("MM/dd/yy hh:mm"));
        eventInfo.appendChild(new Br());
        eventInfo.appendText("Description: "+event.getDescription());
  
        try 
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(detailsFile));
            out.write(html.write());
            out.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        Node n = new Node( new DateTime(2012, 2, 14, 9, 45), new DateTime(2012, 2, 14, 10, 0), "Lemur walk", "walk with lemurs");
        SummaryPageHTMLWriter a = new SummaryPageHTMLWriter();
        ArrayList<Node> list = new ArrayList<Node>();
        list.add(n);
        a.makeFile("output/details_dir", "summary.html", list);
    }

}
