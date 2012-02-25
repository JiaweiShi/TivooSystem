package model;

import java.util.Comparator;

public class EndTimeComparator implements Comparator<Node> { 



    public int compare (Node left, Node right)
    {
         if(left.getEnd().isBefore(right.getEnd()))
             return -1;
         if(left.getEnd().isAfter(right.getEnd()))
             return 1;
         return 0;
    }

}
