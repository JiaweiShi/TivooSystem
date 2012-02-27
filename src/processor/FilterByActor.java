package processor;

import java.util.ArrayList;
import java.util.List;

import model.Node;

public class FilterByActor extends Processor{
	
	public List<Node> process(List<Node> nodes, List<String> keyWords)
	{
		List<Node> filteredNodes = new ArrayList<Node>();
		for(Node node : nodes)
		{	
			if(containActor(node,keyWords))
				filteredNodes.add(node);
		}
		return filteredNodes;
	}

	@Override
	public List<Node> process(List<Node> nodes, String... actors) {
		if(actors.length >=1)
			return process(nodes, storeArrayToList(actors));
		return null;
	}
	
	private List<String> storeArrayToList(String...keyWords){
		List<String> list = new ArrayList<String>();
		for(String a: keyWords){
			list.add(a);
		}
		return list;	
	}
	
	private boolean containActor(Node node, List<String> actorNames){
		if(!node.getType().equals("tv") || node.getMapInformation().get("actor") ==null)
			return false;
		else{
			List actorList = node.getMapInformation().get("actor");
			for(String actor : actorNames){
				if(!actorList.contains(actor))
					return false;
			}
			return true;
		}
	}
	
	public String getString(){
		return "FilterByActor";
	}

}
