package processor;
import java.lang.reflect.*;



public class ProcessorFactory {
	public Processor getProcessor(String name)
	{
		try {
			String fullName = "processor."+name;
			Class processor = Class.forName(fullName);
			return (Processor) processor.newInstance();
		} catch(ClassNotFoundException e) {
			System.out.println("ClassNotFound"); //add this to our exception class and handle these
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
