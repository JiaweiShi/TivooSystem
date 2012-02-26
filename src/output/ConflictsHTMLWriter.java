package output;


import java.util.List;
import model.Node;

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H2;
import com.hp.gagawa.java.elements.Ul;

public class ConflictsHTMLWriter extends HTMLWriter {
      
    @Override
    public void makeBody(Body body, List<?> events, String detailsFile)
    {
        List<List<Node>> conflicts = (List<List<Node>>) events;
        H1 header = new H1();
        header.appendText("Conflicts in Schedule:" );
        body.appendChild(header);

        int countEvents = 0;
        
        for(int i=0; i<conflicts.size() ; i++)
        {
            H2 subHeading = new H2();
            subHeading.appendText("Conflict "+(i+1)+":");
            body.appendChild(subHeading);
            List<Node> conflict = conflicts.get(i);
            Ul conflictList = new Ul();
            for(Node event: conflict)
            {
                String detailPageLocation = detailsFile+"/" + countEvents +".html";
                makeDetailsPage(event, detailPageLocation);
                addEventToList(conflictList, event, detailPageLocation);
                countEvents ++;
            }
            body.appendChild(conflictList);           
            
        }
       
    }


}
