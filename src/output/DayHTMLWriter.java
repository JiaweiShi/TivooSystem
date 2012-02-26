package output;

import java.util.List;

import org.joda.time.DateTime;

import model.Node;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Ul;

public class DayHTMLWriter extends HTMLWriter{

    @Override
    public void makeBody(Body body, List<?> events, String detailsFile)
    {
        List<Node> eventList = (List<Node>) events;
        if(eventList.isEmpty())
        {
            body.appendText("No events");
            return;
        }
        H1 header = new H1();
        header.appendText(eventList.get(0).getStart().toString("MM/dd/yy"));
        body.appendChild(header);
        Ul list = new Ul();
        int count =0;
        for(Node event: eventList)
        {
            String detailPageLocation = detailsFile+"/" + count +".html";
            makeDetailsPage(event, detailPageLocation);
            count++;
            addEventToList(list, event, detailPageLocation); 
        }
        
        body.appendChild(list);
        
        
    }

}
