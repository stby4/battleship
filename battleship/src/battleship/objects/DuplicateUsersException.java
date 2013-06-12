package battleship.objects;

/**
 * duplicateUsersException
 * @author H. Kaestner
 */
@SuppressWarnings("SameParameterValue")
public class DuplicateUsersException extends Exception
{
    public DuplicateUsersException()
    {
        super();
    }
    public DuplicateUsersException( String s )
    {
        super( s );
    }
}

