package processor;

import java.util.*;

public class ProcessorFactory {
	public Processor getProcessor(String name, Map<String, String> map)
	{
		try {
			//String fullName = "processor."+name;
			Class<?> processor = Class.forName(map.get(name)+name);
			return (Processor) processor.newInstance();
		} catch(ClassNotFoundException e) {
			System.out.println("ClassNotFound"); //add this to our exception class and handle these
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
