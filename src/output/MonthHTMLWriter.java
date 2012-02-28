package output;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.joda.time.DateTime;
import model.Node;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H2;
import com.hp.gagawa.java.elements.P;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.hp.gagawa.java.elements.Ul;

public class MonthHTMLWriter extends HTMLWriter {

    
    private static final TreeMap<Integer, Integer> monthMap = new TreeMap<Integer, Integer>();
    static      
    {
            monthMap.put(1, 31);
            monthMap.put(2, 29);
            monthMap.put(3, 31);
            monthMap.put(4, 30);
            monthMap.put(5, 31);
            monthMap.put(6, 30);
            monthMap.put(7, 31);
            monthMap.put(8, 31);
            monthMap.put(9, 30);
            monthMap.put(10, 31);
            monthMap.put(11, 30);
            monthMap.put(12, 31);
            
            
    }
    @Override
    public void makeBody(Body body, List<?> events, String detailsFile)
    {
        if(events.isEmpty())
        {
            body.appendText("no events");
            return;
        }
        List<Node> eventList = (List<Node>) events;
        Table calendar = new Table();
        calendar.setBorder("2");
        Tr[] rows = new Tr[7];
        for(int i=0; i<7; i++)
        {
            Tr row = new Tr();
            row.setAttribute("valign", "top");
            calendar.appendChild(row);
            rows[i] = row;
        }
        for(String day : days)
        {
            rows[0].appendChild((new Td()).appendText(day) );    
        }
        List<Td> dayList = new ArrayList<Td>();
        for (int i=1; i<7; i++)
        {
            for (int j=0; j<7 ; j++)
            {
                Td day = new Td();
                rows[i].appendChild(day);
                dayList.add(day);
            }
        }
        
        int month = eventList.get(0).getStart().getMonthOfYear();
        int year = eventList.get(0).getStart().getYear();
        DateTime first = new DateTime(year, month, 1, 0, 0);
        int day = first.getDayOfWeek();
        int offset = day-2;
        int calendarSpot;
        List<ArrayList<Node>> eventsByDay = new ArrayList<ArrayList<Node>>();
        for(int i = 0 ; i<dayList.size(); i++)
        {
            eventsByDay.add(i, new ArrayList<Node>());
        }
        for(Node event: eventList)
        {   
            calendarSpot = event.getStart().getDayOfMonth()+offset;
            eventsByDay.get(calendarSpot).add(event);
        }
        int count = 0;
        for(int i = 0; i<dayList.size() ; i++)
        {
            Td calendarElement = dayList.get(i);
            if( i<=offset || i>= (monthMap.get(month)+offset+1))
            {
                calendarElement.appendText("-");
                continue;
            }
            H2 header = new H2();
            header.appendText((i-offset)+"");
            calendarElement.appendChild(header);
            
            Ul list = new Ul();
            for(int j = 0; j <eventsByDay.get(i).size(); j++)
            {
                Node event = eventsByDay.get(i).get(j);
                String detailPageLocation = detailsFile+"/" + count +".html";
                makeDetailsPage(event, detailPageLocation);
                addEventToList(list, event, detailPageLocation );
                count++;
            }
            calendarElement.appendChild(list);
            
        }
        H1 header = new H1();
        header.appendText(eventList.get(0).getStart().monthOfYear().getAsText());
        body.appendChild(header);
        body.appendChild(calendar);

    }

}
