package battleship.objects;

/**
 * duplicateUsersException
 * @author Hinrich Kaestner, Tom Ohme
 */
@SuppressWarnings("SameParameterValue")
public class DuplicateUsersException extends Exception
{
    /**
     * Creates a new duplicate users exception
     */
    public DuplicateUsersException()
    {
        super();
    }

    /**
     * Creates a new duplicate users exception with a message
     *
     * @param s error message
     */
    public DuplicateUsersException( String s )
    {
        super( s );
    }
}

