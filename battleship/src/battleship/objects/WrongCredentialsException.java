package battleship.objects;

/**
 * NotMatchingPasswordsException
 * @author H. Kaestner
 */
@SuppressWarnings("SameParameterValue")
public class WrongCredentialsException extends Exception
{
    /**
     * Creates a new wrong credentials exception
     */
    public WrongCredentialsException()
    {
        super();
    }

    /**
     * Creates a new wrong credentials exception with a message
     *
     * @param s error message
     */
    public WrongCredentialsException( String s )
    {
        super( s );
    }
}

