package processor;

import java.util.List;

import model.Node;

public abstract class Processor {
	public abstract List<Node> process(List<Node> nodes, String...keyWords);
}
