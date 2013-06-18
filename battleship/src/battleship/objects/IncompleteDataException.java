package battleship.objects;

/**
 * duplicateUsersException
 * @author Hinrich Kaestner, Tom Ohme
 */
@SuppressWarnings("SameParameterValue")
public class IncompleteDataException extends Exception
{
    /**
     * Creates a new incomplete data exception.
     */
    public IncompleteDataException()
    {
        super();
    }

    /**
     * Creates a new incomplete date exception with a message.
     * @param s error message
     */
    public IncompleteDataException( String s )
    {
        super( s );
    }
}

