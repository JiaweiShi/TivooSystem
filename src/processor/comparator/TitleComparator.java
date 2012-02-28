package processor.comparator;
import java.util.Comparator;

import model.Node;

public class TitleComparator implements Comparator<Node> { 



        public int compare (Node left, Node right)
        {
            return left.getTitle().compareTo(right.getTitle());
                        
        }

}
