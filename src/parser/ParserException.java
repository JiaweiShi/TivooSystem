package parser;

@SuppressWarnings("serial")
public class ParserException extends RuntimeException {

	public static enum Type { EMPTY_FILE, BAD_SYNTAX };
    private Type myType;

    public ParserException (String message)
    {
        this(message, Type.BAD_SYNTAX);
    }
    
    public ParserException (String message, Type type)
    {
        super(message);
        myType = type;
    }
    
    public Type getType()
    {
    	return myType;
    }
	
	
	
	

}
