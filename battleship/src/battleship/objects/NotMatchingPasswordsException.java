package battleship.objects;

/**
 * NotMatchingPasswordsException
 * @author H. Kaestner
 */
@SuppressWarnings("SameParameterValue")
public class NotMatchingPasswordsException extends Exception
{
    public NotMatchingPasswordsException()
    {
        super();
    }
    public NotMatchingPasswordsException( String s )
    {
        super( s );
    }
}

