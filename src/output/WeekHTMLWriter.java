package output;

import java.util.List;

import model.Node;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.hp.gagawa.java.elements.Ul;

public class WeekHTMLWriter extends HTMLWriter{

    @Override
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
        Table week = new Table();
        week.setBorder("1");
        Tr row = new Tr();
        week.appendChild(row);
        for(int i=0; i<dayList.length;  i++)
        {
            Td column = new Td();
            H1 dayHeader = new H1();
            column.appendChild(dayHeader);
            dayHeader.appendText(days[i]);
            column.appendChild(dayList[i]);
            row.appendChild(column);
        }
        body.appendChild(week);
    }

}
