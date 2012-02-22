package processor;

import java.util.ArrayList;

import model.Node;

public abstract class Processor {
	public abstract ArrayList<Node> process(ArrayList<Node> nodes, String...keyWords);
}
