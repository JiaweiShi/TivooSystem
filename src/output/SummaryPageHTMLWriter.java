package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import model.Node;

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


public class SummaryPageHTMLWriter extends HTMLWriter {
    


    
    public void makeBody(Body body, List<?> events, String detailsFile)
    {
        
        List<Node> eventsList = (List<Node>) events;
        int count = 0;
        
        Ul[] dayList = new Ul[7];
        for(int i = 0; i<7; i++)
        {
            dayList[i] = new Ul();
        }
        
        
        
        for(Node event: eventsList)
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
            dayHeader.appendText(days[i]);
            body.appendChild(dayList[i]);
        }
        
        

    }
    

   

}
