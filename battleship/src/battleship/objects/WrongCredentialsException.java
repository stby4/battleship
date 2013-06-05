package battleship.objects;

/**
 * NotMatchingPasswordsException
 * @author H. Kaestner
 */
public class WrongCredentialsException extends Exception
{
    public WrongCredentialsException()
    {
        super();
    }
    public WrongCredentialsException( String s )
    {
        super( s );
    }
}
