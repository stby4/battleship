package battleship.objects;

/**
 * NotMatchingPasswordsException
 * @author Hinrich Kaestner, Tom Ohme
 */
@SuppressWarnings("SameParameterValue")
public class NotMatchingPasswordsException extends Exception
{
    /**
     * Creates a new not matching passwords exception.
     */
    public NotMatchingPasswordsException()
    {
        super();
    }

    /**
     * Creates a new not matching passwords exception with message
     *
     * @param s error message
     */
    public NotMatchingPasswordsException( String s )
    {
        super( s );
    }
}

