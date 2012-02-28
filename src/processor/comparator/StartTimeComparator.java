package processor.comparator;

import java.util.Comparator;

import model.Node;

public class StartTimeComparator implements Comparator<Node> { 



    public int compare (Node left, Node right)
    {
         if(left.getStart().isBefore(right.getStart()))
             return -1;
         if(left.getStart().isAfter(right.getStart()))
             return 1;
         return 0;
    }

}
