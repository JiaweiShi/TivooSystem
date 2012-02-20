import java.lang.reflect.*;


public class ProcessorFactory {
	public Processor getProcessor(String name)
	{
		try{
			Class processor = Class.forName(name);
			return (Processor) processor.newInstance();
		}catch(ClassNotFoundException e)
		{
			System.out.println("ClassNotFound"); //add this to our exception class and handle these
			return null;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
