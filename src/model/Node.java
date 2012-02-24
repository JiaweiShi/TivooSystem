package model;

import java.util.*;

import org.joda.time.DateTime;


public class Node implements Comparable<Node>{

	private DateTime startDt;
	private String title;
	private DateTime endDt;
	private String description;
	private String type;
	private HashMap<String, ArrayList<String>> map;
	
	public Node(DateTime start, DateTime end, String t,String d, String n, HashMap m) {
		startDt = start;
		endDt = end;
		description = d;
		title = t;
		map = m;
		type = n;
	}
	
	public boolean containKeyWord(String keyword){
		return title.contains(keyword);
	}
	
	public int compareTo(Node node){
		if(endDt.isBefore(node.endDt))  return -1;
		else if(endDt.isAfter(node.endDt))   return 1;
		else return 0; 
	}	
	
	public DateTime getStart()
	{
	    return startDt;
	}
	
	public DateTime getEnd()
	{
	    return endDt;
	}
	
	public String getTitle()
	{
	   return title;
	}
	
	public String getDescription()
	{
	    return description;
	}
	
	public String getType(){
		return type;
	}
	
	public HashMap<String,ArrayList<String>> getMapInformation(){
		return map;
	}
}
