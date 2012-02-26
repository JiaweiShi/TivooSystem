package processor;

import java.util.List;

import model.Node;

public abstract class Processor {
	public abstract List<?> process(List<Node> nodes, String...keyWords);
	public abstract String getString();
}
