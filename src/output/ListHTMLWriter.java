package output;

import java.util.ArrayList;
import java.util.List;

import model.Node;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Ul;

public class ListHTMLWriter extends HTMLWriter{
    public void makeBody(Body body, List<?> events, String detailsFile)
    {
        List<Node> eventList = (List<Node>) events;
        int count = 0;        
        Ul list = new Ul();        
        for(Node event: eventList)
        {
            String detailPageLocation = detailsFile+"/" + count +".html";
            makeDetailsPage(event, detailPageLocation);
            count++;
           // int day = event.getStart().getDayOfWeek();
            addEventToList(list, event, detailPageLocation);  
            
        }
        
        body.appendChild(list);
        
        

    }

}
