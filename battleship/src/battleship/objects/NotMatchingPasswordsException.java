package battleship.objects;

/**
 * NotMatchingPasswordsException
 * @author H. Kaestner
 */
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

