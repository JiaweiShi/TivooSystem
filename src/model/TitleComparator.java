package model;
import java.util.Comparator;

public class TitleComparator implements Comparator<Node> { 



        public int compare (Node left, Node right)
        {
            return left.getTitle().compareTo(right.getTitle());
                        
        }

}
