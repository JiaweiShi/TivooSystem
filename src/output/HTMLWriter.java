package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.Node;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.P;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Ul;


public abstract class HTMLWriter {
    
    public abstract void makeBody(Body body, List<Node> events, String detailsFile);
    
    public void makeFile(String detailsFile, String summaryFile, List<Node> events)
    {
        Html html = setupHtml("Output File");
        Body body = new Body();
        html.appendChild(body);        
        makeBody(body, events, detailsFile);
        createFile(summaryFile, html);
    }
    
    public void makeDetailsPage(Node event, String detailsFile)
    {
        Html html = setupHtml(event.getTitle()+ " Page");        
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
        
        createFile(detailsFile, html);


    }
    
    private Html setupHtml(String pageName)
    {
        Html html = new Html();
        Head head = new Head();        
        html.appendChild( head );        
        Title title = new Title();
        title.appendChild( new Text(pageName) );
        head.appendChild( title );
        
        return html;
    }
    
    private void createFile(String fileName, Html html)
    {
        try 
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(html.write());
            out.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void addEventToList(Ul list, Node event, String detailPageLocation)
    {
        Li eventListElement = new  Li();
        list.appendChild(eventListElement);
        A eventLink = new A();
        eventListElement.appendChild(eventLink);
        P times = new P();
        times.appendText(event.getStart().toString("hh:mm")+" - " + event.getEnd().toString("hh:mm"));
        eventListElement.appendChild(times);       
        eventLink.setHref(detailPageLocation);
        eventLink.appendText(event.getTitle());
        
    }

}
