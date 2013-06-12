package battleship.objects;

/**
 * duplicateUsersException
 * @author H. Kaestner
 */
@SuppressWarnings("SameParameterValue")
public class IncompleteDataException extends Exception
{
    public IncompleteDataException()
    {
        super();
    }
    public IncompleteDataException( String s )
    {
        super( s );
    }
}

